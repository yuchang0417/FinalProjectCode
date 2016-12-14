package DealData

/**
  * Created by siyingma on 12/9/16.
  */

import akka.actor.{Actor, ActorSystem, Props}

class TradeOperation extends Actor {
  val a = List(8.6052, 8.52, 8.094, 8.52, 8.6904)
  val maxP = a.reduceLeft(maxmin.findMax)
  val minP = a.reduceLeft(maxmin.findMin)

  def receive = {
    case maxP => println("sell")
    case minP => println("buy")
    case _  => println("-")

  }
}

object TradeSystem extends App {
  val a = List(8.6052, 8.52, 8.094, 8.52, 8.6904)
  val system = ActorSystem("TradeSystem")
  // default Actor constructor
  val TradeActor = system.actorOf(Props[TradeOperation], name = "TradeOperation")
  TradeActor ! a(0)
  TradeActor ! a(1)
  TradeActor ! a(2)
  TradeActor ! a(3)
  TradeActor ! a(4)
}
