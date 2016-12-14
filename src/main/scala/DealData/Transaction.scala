package DealData

/**
  * Created by Chang Yu on 2016/12/9.
  */

import scala.io.Source
import scala.util.Random.nextGaussian

object  Transaction
{
  val GaussianGenerator= Stream.continually(1+(nextGaussian()/4)).take(5).toList

  def countNumber(fileName: String) : Int = Source.fromFile(fileName).mkString.split("\",\"").toList.length

  def getTickerSymbol(fileName: String): Seq[String]=Source.fromFile(fileName).mkString.split("\",\"")

  def getBasicStockValue(symbol: String):String={
  val rawdata2 = scala.io.Source.fromURL("http://www.google.com/finance/info?q=" + symbol) //.mkString.split(",")(3).replaceAll("\"", "").replaceAll("[l: ]", "")
  val ssk=rawdata2.mkString.split(",")(3).replaceAll("\"", "").replaceAll("[l: ]", "")
  ssk
  }

  def getPrice(n: Int) = {
    val synbol = Source.fromFile("result").mkString.split("\",\"").toList(n)
    val price = scala.io.Source.fromURL("http://www.google.com/finance/info?q=" + synbol).mkString.split(",")(3).replaceAll("\"", "").replaceAll("[l: ]", "")
    price
  }

  def GeneratePriceList(i: Int) : List[Double] =Stream.continually(1+(nextGaussian()/4)).take(5).toList.map(_*(getPrice(i).toDouble))

}


object maxmin {
  def findMax = (x: Double, y: Double) => math.max(x, y)

  def findMin = (x: Double, y: Double) => x min y

  def findRate = (x:Double,y:Double) => (x-y)/y
}


object myTest extends App
{
   val st =  Transaction.countNumber("result")
   println(st)

   val bb = Transaction.getTickerSymbol("result")
   println(bb)

   val cc = Transaction.getBasicStockValue("AAAP")
  println(cc)

  val ss = Transaction.getPrice(22)

  println(ss)

  for ( i<-1 until Transaction.countNumber("result"))
    {
      println(Transaction.GeneratePriceList(i))
    }
}