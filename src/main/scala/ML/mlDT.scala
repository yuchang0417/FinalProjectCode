package ML

import dataLoad.CsvReader
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.tree.DecisionTree
import org.apache.spark.mllib.tree.model.DecisionTreeModel
import org.apache.spark.mllib.util.MLUtils


object mlDT extends App{
  
   val conf = new SparkConf().setAppName("mlDT")
                             .setMaster("local[4]")
                             
    val sc = new SparkContext(conf)
   
    // Load and parse the data file.
  //val data = CsvReader.LoadData("HistoricalQuotes (1).csv")    // MLUtils.loadLibSVMFile(sc, "testData/wordFreLine_test.txt")

  val reader = sc.textFile("testData/pefect0.csv")
  val data = reader.map{line=>val parts=line.split(",");LabeledPoint(parts(0).toDouble, Vectors.dense(parts.slice(1,6).map(x=>x.toDouble))) }
  println(data)


// Split the data into training and test sets (30% held out for testing)


val splits = data.randomSplit(Array(0.8, 0.2))
val (trainingData, testData) = (splits(0), splits(1))

print("labledpoint: " + data.toString)

// Train a DecisionTree model.
//  Empty categoricalFeaturesInfo indicates all features are continuous.
val numClasses = 6
val categoricalFeaturesInfo = Map[Int, Int]()
val impurity = "gini"
val maxDepth = 5
val maxBins = 30

print("start training model")
   
val model = DecisionTree.trainClassifier(trainingData, numClasses, categoricalFeaturesInfo,
  impurity, maxDepth, maxBins)

  
print("training complete")
// Evaluate model on test instances and compute test error
val labelAndPreds = testData.map { point =>
  val prediction = model.predict(point.features)
  (point.label, prediction)
}
   
   print("evaluate")
val testErr = labelAndPreds.filter(r => r._1 != r._2).count().toDouble / testData.count()
println("Test Error = " + testErr)
println("Learned classification tree model:\n" + model.toDebugString)

// Save and load model
model.save(sc, "target/temp/myDecisionTreeClassificationModel")
val sameModel = DecisionTreeModel.load(sc, "target/temp/myDecisionTreeClassificationModel")
}
