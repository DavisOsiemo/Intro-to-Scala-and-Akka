
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpMethods.POST
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import akka.util.ByteString

import scala.util.{Failure, Success}

object FetchSubscription {
  def main(args: Array[String]): Unit = {
    //Subscribe usert

    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()

    //The materializer is a factory for stream execution. Think Akka stream processing
    implicit val executionContext = system.dispatcher

    val url = "http://localhost:8080/sms/subscription"
    val subs = "/sms/subscription"

    //Fetch premium subscriptions
    //val sms = "/version1/messaging"


    //Headers
    val apiKey = "c6d88db1f5fdd5cb842cfbd61691ab15fa2d5356a570976d5031a61dd03b7f6b"
    val accept = headers.Accept(MediaTypes.`application/json`)

    //Body params
    val username  = "sandbox"
    val shortCode = "13324"
    val keyword   = "music"
    val lastReceivedId = "0"

    val body =  s"""{"username": $username, "shortCode":  $shortCode, "keyword": $keyword, "lastReceivedId": $lastReceivedId}"""
    val body2 = Map (
      "username" -> username,
      "shortCode" -> shortCode,
      "keyword" -> keyword,
      "lastReceivedId" -> lastReceivedId
    )
    val req = HttpRequest(
      POST,
      uri = url,
      entity = HttpEntity(ContentTypes.`application/json`, ByteString(body)),
      headers = List(
        headers.RawHeader("apikey", apiKey), accept)
    )
    Http().singleRequest(req)
      .onComplete {
        case Success(res) =>
          println(res)
          system.terminate()
        case Failure(exception) =>
          exception.printStackTrace()
          system.terminate()
      }
    //println("application started... ")
  }
}