package dataLoad

/**
  * Created by Chang Yu on 2016/12/7.
  */
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{SQLContext, types}
import org.apache.spark.sql.types._


object CsvReader {


  def LoadData(fileName:String):RDD[LabeledPoint]=
  {
    val conf = new SparkConf().setAppName("Analysis").setMaster("local[3]")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)


    val inputschema = StructType(Array(
      //  StructField("date",DateType,true),
      // StructField("appclose",DoubleType,true),
      //  StructField("appopen", DoubleType, true),
      //  StructField("hpqclose",DoubleType,true),
      StructField("hqpopen",DoubleType,true),
      //   StructField("msftclose",DoubleType,true),
      StructField("msftopen",DoubleType, true),
      //   StructField("vzclose",DoubleType, true),
      StructField("vzopen",DoubleType, true),
      //   StructField("bacclose",DoubleType, true),
      StructField("bacopen",DoubleType,true),
      //   StructField("Nasdaqclose", DoubleType, true),
      StructField("Nasdaqopen",DoubleType, true),

      StructField("classResult", IntegerType,true)

    ))

    val csvInput = sqlContext.read.format("com.databricks.spark.csv").option("header","true").schema(inputschema).load(fileName)

    val selectedData = csvInput.select("classResult","Nasdaqopen","bacopen", "msftopen","hqpopen","vzopen") .rdd


    for{
      rowdata<- selectedData

      rowseq = rowdata.toSeq

    } yield {LabeledPoint(rowseq.head.asInstanceOf[Double], Vectors.dense(convertToArray(rowseq.tail)))}
  }


  def convertToArray(seq: Seq[Any]): Array[Double] = {
    val seqDouble = for {
      item <- seq
      x = doubleCheck(item)
    } yield x
    seqDouble.toArray
  }

  def doubleCheck(n: Any): Double = {
    n match {
      case null => 0
      case _    => n.asInstanceOf[Double]
    }
  }

}