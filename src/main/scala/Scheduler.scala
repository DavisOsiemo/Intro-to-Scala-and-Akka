import akka.actor.{Actor, ActorSystem, Props}
import spray.json._

import scala.concurrent.duration._
import scala.io.Source
//import scala.xml._

object Scheduler extends App{

    val Tick = "tick"
    val Tock = "tock"
    class TickActor extends Actor {
      def receive = {
        case Tick => println("Tick")
        case Tock => println("Tock")
      }
    }

    val system = ActorSystem ("tick")
    val tickActor = system.actorOf(Props (classOf[TickActor]))
    import system.dispatcher

    val cancellable =
      system.scheduler.schedule(
        0 milliseconds,
        50 milliseconds,
        tickActor,
        Tick
      )
}
