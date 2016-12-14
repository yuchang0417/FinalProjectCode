import org.scalatest.{FlatSpec, Matchers}
import DealData._
/**
  * Created by Chang Yu on 2016/12/10.
  */
class ProSpec extends FlatSpec with Matchers {
  behavior of "maxmin"
  it should "success in finding maximum for List(8.6052, 8.52, 8.094, 8.52, 8.6904)"  in {
    val a = List(8.6052, 8.52, 8.094, 8.52, 8.6904)
    val test1 = a.reduceLeft(maxmin.findMax)

    test1 shouldBe 8.6904
  }
  it should "success in finding maximum for List(5.20, 5.20, 5.20, 5.20, 5.20)"  in {
    val aa = List(5.20, 5.20, 5.20, 5.20, 5.20)
    val test11 = aa.reduceLeft(maxmin.findMax)

    test11 shouldBe 5.20
  }

  it should "success in finding minimum List(8.6052, 8.52, 8.094, 8.52, 8.6904)" in {
    val a = List(8.6052, 8.52, 8.094, 8.52, 8.6904)
    val test2 = a.reduceLeft(maxmin.findMin)

    test2 shouldBe 8.094
  }
  it should "success in finding minimum for List(5.20, 5.20, 5.20, 5.20, 5.20)"  in {
    val aa = List(5.20, 5.20, 5.20, 5.20, 5.20)
    val test21 = aa.reduceLeft(maxmin.findMin)

    test21 shouldBe 5.20
  }

  it should "success in finding rate List(8.6052, 8.52, 8.094, 8.52, 8.6904)" in {
    val test3 = maxmin.findRate(8.6904,8.094)

    test3 shouldBe 0.07368421052631591
  }
  it should "success in finding rate for List(5.20, 5.20, 5.20, 5.20, 5.20)"  in {
    val aa = List(5.20, 5.20, 5.20, 5.20, 5.20)
    val test31 = maxmin.findRate(5.20,5.20)

    test31 shouldBe 0
  }
  behavior of "Transaction"

 it should " success in fiding the length of result file" in{
   val length = Transaction.countNumber("result")
    println(length)
    length shouldBe 7778
 }


}
