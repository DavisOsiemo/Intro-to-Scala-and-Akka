import java.net.URLEncoder

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model.HttpCharsets._
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import spray.json._
import DefaultJsonProtocol._

import scala.util.{Failure, Success}

object SandBoxAPICalls {
  def main(args: Array[String]): Unit = {

    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher

    val url = "https://api.sandbox.africastalking.com/version1/subscription/delete"
    val tokenUrl = "https://api.sandbox.africastalking.com/checkout/token/create"
    val apiKey = "acee0958fab9c87e2b859e13c45a43eb5753693e09a5e18820b5c397301ca8c1"
    val username = "sandbox"
    val keyword = "music"
    val shortCode = "13324"
    val phoneNumber = "+254729135212"
    //val token = "CkTkn_45f59305-48b9-4591-befe-fc879a4f0fc4"

    val createSub = HttpRequest (
      POST,
      uri = url,
      entity = akka.http.scaladsl.model.FormData (Map (
        "apiKey" -> "acee0958fab9c87e2b859e13c45a43eb5753693e09a5e18820b5c397301ca8c1",
        "username" -> "sandbox",
        "keyword" -> keyword,
        "shortCode" -> shortCode,
        "phoneNumber" -> "254703378250"
        //"checkoutToken" -> "CkTkn_a62dbd75-df80-4fa0-b4bc-b1a5c7b20556"
      )).toEntity(`UTF-8`),
      headers = List (headers.RawHeader ("apiKey", apiKey))
    )
    Http().singleRequest(createSub)
      .onComplete{
        case Success (res) => print(res)
        case Failure (_) => print("failed")
      }

    val fetchUrl = "http://api.sandbox.com/version1/subscription"
    val lastReceivedId = "0"
    val requiredData = Map (
      "username" -> "sandbox",
      "shortCode" -> shortCode,
      "keyword"-> keyword,
      "lastReceivedId" -> "0"
    )
      def buildEncodedQueryString(params: Map[String, Any]): String = {
      val encoded = for {
        (name, value) <- params if value != None
        encodedValue = value match {
          case Some(x) => URLEncoder.encode(x.toString, "UTF8")
          case x       => URLEncoder.encode(x.toString, "UTF8")
        }
      } yield name + "=" + encodedValue
      encoded.mkString("&")
    }
    val strn = buildEncodedQueryString(requiredData)
    val fetchSubs = HttpRequest(
      GET,
      uri = "https://api.sandbox.africastalking.com/version1/subscription?" + strn,
      headers = List(headers.RawHeader("apiKey", apiKey))
    )
    Http().singleRequest(fetchSubs)
      .onComplete{
        case Success(res) => println(res)
        case Failure (e) => println(s"Exception: $e")
      }
  }
}
