/**
  * Created by Chang Yu on 2016/12/8.
  */

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}

class mysecifiySpec extends FlatSpec with BeforeAndAfterAll with Matchers {
  var sc: SparkContext = _
  var sqlContext: SQLContext = _

  override protected def beforeAll(): Unit = {
    super.beforeAll()
    val conf = new SparkConf().setMaster("local[4]")
      .setAppName("word count")
      .set("spark.driver.allowMultipleContexts", "true")
    sc = new SparkContext(conf)
    sqlContext = new SQLContext(sc)
  }

  override protected def afterAll(): Unit = {
    try {
      sc.stop()
      sc = null
      sqlContext = null
    } finally {
      super.afterAll()
    }


  }





}