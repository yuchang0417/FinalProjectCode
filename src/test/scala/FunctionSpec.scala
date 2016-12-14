/**
  * Created by Chang Yu on 2016/10/7.
  */

import dataLoad.Function
import org.scalatest.{FlatSpec, Matchers}

import scala.util._

class FunctionSpec extends FlatSpec with Matchers{

  behavior of "map2"

  it should """match Success(1234) for parse "12" to int and parse "34" to int,with (a:Int,b:Int) => a.toString()+b.toString()"""  in
    {
      val a1 = "12"
      val a2 = "34"
      val t1 = Try(a1.toInt)
      val t2 = Try(a2.toInt)

      val test = Function.map2(t1, t2)((a:Int,b:Int) => a.toString()+b.toString())

      test should matchPattern {
        case Success("1234") =>
      }
    }
  behavior of "invert2"

  it should "work" in
    {
      val a:Int => Int => String = {a => b=> "abcde".substring(a, b)}

      Try(a(0)(2)) should matchPattern{
        case Success("ab") =>
      }

      val aux = Function.invert2(a)

      Try(aux(0)(2)) should matchPattern{
        case Failure(e) =>
      }
    }

  behavior of "invert3"

  it should "work" in {

    val a:Int => Int=> Int=> Int = {a => b=> c=> a*b+c}

    a(2)(3)(4) shouldBe 10

    val aux = Function.invert3(a)

    aux(2)(3)(4) shouldBe 14
  }

  behavior of "invert4"

  it should "work" in {

    val a:Int => Int=> Int=> Int=>Int = {a => b=> c=> d=> a*b+c*d}

    a(2)(3)(4)(5) shouldBe 26

    val aux = Function.invert3(a)

    aux(2)(3)(4)(5) shouldBe 22
  }

  behavior of "uncurried2"
  it should "work" in{

    def a:Int => Int=> Int=> Int = {a => b=> c=> a*b+c}

    def aux = Function.uncurried2(a)
    a.toString() shouldBe "<function1>"
    aux.toString() shouldBe "<function2>"
  }
}

