//import akka.actor.ActorSystem
//import akka.http.scaladsl.Http
//import akka.http.scaladsl.model.{ContentTypes, HttpCharsets, HttpEntity, HttpRequest}
//import akka.stream.ActorMaterializer
//import spray.json._
//import akka.http.scaladsl.model._
//import scala.util.{Failure, Success}
//
//object PaymentTest {
//  def main(args: Array[String]): Unit = {
//    implicit val system = ActorSystem  ()
//    implicit val materializer = ActorMaterializer
//    implicit val executionContext = system.dispatcher
//
//    val url = "http://localhost:8080/payment/mobile/b2c"
//    val apiKey = "044bd564e4f853548eedc3dc0efec2d99b6a294bb48249b34c27e6bbab26f4"
//    val username = "sandbox"
//    val productName = "muziki"
//    val phoneNumber = "+254729135212"
//    val recipients = Map (
//      "name" -> "davis",
//      "phoneNumber" -> "+254729135212",
//      "currencyCode" -> "KES",
//      "amount" -> "645.50"
//    )
//
//    val body = Map (
//      "username" -> username,
//      "productName" -> productName,
//      "recipients" -> recipients
//    )
//
//    val request = HttpRequest(
//      uri = url,
//      entity = HttpEntity (ContentTypes.`application/json`, body.toJson.toString()),
//      headers = List (headers.RawHeader("apiKey", apiKey))
//    )
//    Http().singleRequest (request)
//      .onComplete{
//        case Success (res) => println(res)
//        case Failure (e) => println(s"Request failed: $e")
//      }
//  }
//}
