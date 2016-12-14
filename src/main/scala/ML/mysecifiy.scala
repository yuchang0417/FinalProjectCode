package ML

import dataLoad.CsvReader
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.classification.{LogisticRegressionModel, LogisticRegressionWithLBFGS}
import org.apache.spark.mllib.evaluation.MulticlassMetrics
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
/**
  * Created by Chang Yu on 2016/12/1.
  */
object  mysecifiy extends App
{
  val source = "testData/pefect0.csv"
  val conf = new SparkConf().setAppName("word count").setMaster("local[4]")
  val sc = new SparkContext(conf)

  // Load training data in LIBSVM format.
// val data = MLUtils.loadLibSVMFile(sc, "testData/sample_libsvm_data.txt")
  val reader = sc.textFile(source)
 val data = reader.map{line=>val parts=line.split(",");LabeledPoint(parts(0).toDouble, Vectors.dense(parts.slice(1,6).map(x=>x.toDouble))) }
  println(data)

  //val data = CsvReader.LoadData("D:\\硕士一\\硕士一\\scala\\ppt\\TestOne\\testData\\pefect.csv")

  // Split data into training (60%) and test (40%).

  val splits = data.randomSplit(Array(0.8, 0.2), seed = 11L)
  val training = splits(0).cache()
  val test = splits(1)
  // Run training algorithm to build the model
  val model = new LogisticRegressionWithLBFGS()
    .setNumClasses(10)
    .run(training)

  // Compute raw scores on the test set.
  val predictionAndLabels = test.map { case LabeledPoint(label, features) =>
    val prediction = model.predict(features)
    (prediction, label)
  }




  // Get evaluation metrics.
  val metrics = new MulticlassMetrics(predictionAndLabels)
  val accuracy = metrics.precision
  println(s"Accuracy = $accuracy")
   val weight = metrics.confusionMatrix

  println(s"ConfusionMatrix = $weight")






  // Save and load model
  model.save(sc, "target/tmp/scalaLogisticRegressionWithLBFGSModel")
  val sameModel = LogisticRegressionModel.load(sc,
    "target/tmp/scalaLogisticRegressionWithLBFGSModel")



}
