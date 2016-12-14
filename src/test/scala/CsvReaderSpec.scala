/**
  * Created by Chang Yu on 2016/12/7.
  */
import dataLoad.CsvReader
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}

/**
  * Created by Chang Yu on 2016/12/7.
  */
class CsvReaderSpec extends FlatSpec with BeforeAndAfterAll with Matchers
{

  var sc: SparkContext = _
  var sqlContext: SQLContext = _

  override protected def beforeAll(): Unit = {
    super.beforeAll()
    val conf = new SparkConf().setMaster("local[2]")
      .setAppName("Insurance Claim")
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

  "doubleCheck(Any(23.11))" should "match case 23.11" in {
    val testVal: Any = 13.33
    val r = CsvReader.doubleCheck(testVal)
    r should matchPattern { case 13.33 => }
  }

  "convertToArray(Seq[Any])" should "match Array(1.2, 3.3, 4.9)" in {
    val testVal: Seq[Any] = List(1.2, 3.3, 4.9)
    val r = CsvReader.convertToArray(testVal)
    r should matchPattern { case Array(1.2, 3.3, 4.9) => }
  }

  it should "match Array(11.9,223.9,66.9,null)" in {
    val testVal: Seq[Any] = List(11.9,223.9,66.9, null)
    val r = CsvReader.convertToArray(testVal)
    r should matchPattern { case Array(11.9,223.9,66.9, 0.0) => }
  }


}