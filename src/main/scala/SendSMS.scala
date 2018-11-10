import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpCharsets.`UTF-8`
import akka.http.scaladsl.model.HttpMethods.POST
import akka.http.scaladsl.model.{ContentType, ContentTypes, HttpRequest, headers}
import akka.stream.ActorMaterializer
import scala.util.{Failure, Success}
import akka.util.ByteString

object SendSMS {
  def main(args: Array[String]): Unit = {

    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()

    //The materializer is a factory for stream execution. Think Akka stream processing

    implicit val executionContext = system.dispatcher

    val url = "https://api.sandbox.africastalking.com"
    val sms = "/version1/messaging"

    val url2 = "http://localhost:8080"
    val sms2 = "/sms/send"

    //Headers
    val apiKey = "c6d88db1f5fdd5cb842cfbd61691ab15fa2d5356a570976d5031a61dd03b7f6b"
    val username = "sandbox"

    //Body params
    val to = "+254703926767"
    val from = "13324"
    val message = "Hello from premium SMS"
    val bulkSMSMode = "1" //For bulk messages
    val keyword = ""
    val req = HttpRequest(
      POST,
      uri = url + sms,
      entity = akka.http.scaladsl.model.FormData(
        "username" -> username,
        "to" -> to,
        "from" -> from,
        "bulkSMSMode" -> "1",
        "message" -> message
      ).toEntity(`UTF-8`),
      headers = List(headers.RawHeader("apikey", apiKey))
    )
    //.withContentType(ContentTypes.`application/json`)
    Http().singleRequest(req)
      .onComplete {
        case Success(res) => println(res)
        case Failure(_) => sys.error("request failed")
      }
  }
}