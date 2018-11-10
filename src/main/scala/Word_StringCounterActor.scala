import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props


class HelloActor extends Actor {
  def receive = {
    case "hello" => println("Hi")
    case "buenos dias" => println("good day")
    case _ => println("Default")
  }
}

class GoodBye extends Actor {
  def receive= {
    case "bye" => println("bye")
    case "adios" => println("adios")
  }
}

object Main extends App {
  val system = ActorSystem ("k")
  //default actor constructor
  val helloActor = system.actorOf(Props [HelloActor], name = "helloactor")
  helloActor ! "hello"
  helloActor ! "buenos dias"


  val byeActor = system.actorOf(Props [GoodBye], name = "byeactor")
  byeActor ! "bye"
  byeActor ! "adios"
}