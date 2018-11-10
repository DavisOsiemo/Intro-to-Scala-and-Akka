import java.util.{Currency, UUID}

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import spray.json._
import DefaultJsonProtocol._
import akka.http.scaladsl.model.Uri.Query
import akka.http.scaladsl.model.HttpCharsets._

import scala.util.{Failure, Success}

object SubscriptionService {
  def main(args: Array[String]): Unit = {

    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher
    //The materializer is a factory for stream execution


    val url = "https://api.africastalking.com/version1/subscription/create"
    val tokenUrl = "https://api.africastalking.com/checkout/token/create"
    val localhostFetchUrl = "http://localhost:8080/sms/subscription"
    val fetchUrl = "http://api.africastalking.com/version1/subscription"
    //val url3 = "http://api.africastalking.com/version1/subscription/create"
    //val newSubs = "/subscription/create"

    val apiKey = "044bd564e4f853548eedc3dc0efec2d99b6a294bb48249b34c27e6bbab26f4"

    //Body params
    val username = "Nyagwachi"
    val shortCode = "22384"
    val keyword = "Sheng"
    val phoneNumber = "+254729135212"
    val token = "CkTkn_4e219885-2be3-4e9d-a0fd-291e05fca4e1"
    val lastReceivedId = "0"
    //val updateType = "addition"

  //  Request checkout token to create subs
//        val req = HttpRequest(
//          POST,
//          uri = tokenUrl,
//          entity = akka.http.scaladsl.model.FormData(Map(
//            "username" -> username,
//            "shortCode" -> shortCode,
//            "keyword" -> keyword,
//            "phoneNumber" -> "+254729135212",
//            "checkoutToken" -> token
//          )).toEntity(`UTF-8`),
//          headers = List(headers.RawHeader("apikey", apiKey))
//        )
//        Http().singleRequest(req)
//          .onComplete {
//            case Success(res) => println(res)
//            case Failure(_) => sys.error("request failed")
//          }

    //Create new subscription
//    val createSubs = HttpRequest (
//      POST,
//      uri = url,
//      entity = akka.http.scaladsl.model.FormData (Map (
//        "username" -> username,
//        "shortCode" -> shortCode,
//        "keyword" -> keyword,
//        "phoneNumber" -> "+254729135212",
//        "checkoutToken" -> "CkTkn_2af42079-71d9-4b4b-8614-3280532ff694"
//      )).toEntity(`UTF-8`),
//      headers = List(headers.RawHeader("apiKey", apiKey))
//    )
//    Http().singleRequest(createSubs)
//      .onComplete{
//        case Success (res) => println (res)
//        case Failure (_) => print("failed")
//      }

    case class ATCash (
      currencyCode: String,
      amount:BigDecimal
    )
    val req2data = Map(
      "transactionId" -> UUID.randomUUID().toString,
      "phoneNumber" -> "+254729135212",
      "shortCode" -> "13324",
      "keyword" -> "testKeyword",
      "updateType" -> "Create",
      "lastReceivedId" -> "0"
    ).toJson

    val req2 = HttpRequest(
      POST,
      uri = "http://localhost:8080/sms/subscription",
      entity = HttpEntity (ContentTypes.`application/json`, req2data.toString),
      headers = List(headers.RawHeader("apiKey", apiKey))
    )
    Http().singleRequest(req2)
      .onComplete {
        case Success(res2) => println(res2.entity)
        case Failure(e) => println(s"request failed: $e")
      }

    //Fetch premium subscriptions from Telco
    val fetchParams = Map ("username" -> username, "shortCode"-> shortCode, "keyword"-> keyword, "lastReceivedId"-> lastReceivedId)
    val fetchParams2 = Map ("username" -> "sandbox", "shortCode" -> "13324", "keyword"-> "music", "lastReceivedId"-> "0")
    val uri = Uri("http://api.sandbox.africastalking.com/version1/subscription")
     //val uri = Uri ("http://localhost:8080/sms/subscription")
     val url2 = "http://localhost:8080"
     val newSubs2 = "/sms/subscription"
     val url3 = "http://localhost:8080/sms/subscription"
//     val req3 = HttpRequest(
//       GET,
//       uri.withQuery(Query (fetchParams2)),
//       headers = List (headers.RawHeader("apiKey", apiKey))
//     )
//     Http().singleRequest(req3)
//       .onComplete{
//         case Success (res3) => println(res3)
//         case Failure (exception) => exception.printStackTrace()
//       }
       //Singletons won't take params

    val req3 = HttpRequest (
      GET,
      uri = "http://localhost:8083/fetch",
      entity = HttpEntity (ContentTypes.`text/html(UTF-8)`, "<html> <body>Hello world! </body></html>")
    )
    Http().singleRequest(req3)
      .onComplete{
        case Success (res3) => println(res3)
        case Failure (e) => println(s"Request failed: $e")
      }
  }
}