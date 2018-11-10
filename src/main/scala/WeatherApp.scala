//import Scheduler.system
//import akka.actor.{Actor, ActorSystem, Props}
//
//import scala.io.Source
//
//object WeatherApp {
//  def main(args: Array[String]): Unit = {
//    val msg = "hi"
//    class WeatherActor extends Actor {
//      override def receive: Receive = {
//        case msg => println(msg)
//      }
//    }
//
//    def getWeatherInfo (id: String): Unit = {
//      val url = "http://weather.yahooapis.com/forecastrss?w=" + id + "&u=c"
//      val response = Source.fromURL(url).mkString
//      println(response)
//    }
//    val system1 = ActorSystem ("weatherapp")
//    val weatherActor = system1.actorOf(Props(classOf[WeatherActor]))
//    val start = System.nanoTime
//    for (id <- 4118 to 4128) {
//      weatherActor ! getWeatherInfo(id.toString)
//    }
//    val end = System.nanoTime
//    println("Time: " + (end - start) / 1000000)
//  }
//}
