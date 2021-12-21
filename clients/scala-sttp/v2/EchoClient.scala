package testservice.client.v2

import scala.concurrent._
import org.slf4j._
import com.softwaremill.sttp._
import testservice.client.ParamsTypesBindings._
import testservice.client.Jsoner

trait IEchoClient {
  import IEchoClient._
  def echoBody(body: Message): Future[EchoBodyResponse]
}

object IEchoClient {
  sealed trait EchoBodyResponse
  object EchoBodyResponse {
    case class Ok(body: Message) extends EchoBodyResponse
  }
}

class EchoClient(baseUrl: String)(implicit backend: SttpBackend[Future, Nothing]) extends IEchoClient {
  import IEchoClient._
  import ExecutionContext.Implicits.global
  private val logger: Logger = LoggerFactory.getLogger(this.getClass)
  def echoBody(body: Message): Future[EchoBodyResponse] = {
    val url = Uri.parse(baseUrl+s"/v2/echo/body").get
    val bodyJson = Jsoner.write(body)
    logger.debug(s"Request to url: ${url}, body: ${bodyJson}")
    val response: Future[Response[String]] =
      sttp
        .post(url)
        .header("Content-Type", "application/json")
        .body(bodyJson)
        .parseResponseIf { status => status < 500 }
        .send()
    response.map {
      response: Response[String] =>
        response.body match {
          case Right(body) =>
            logger.debug(s"Response status: ${response.code}, body: ${body}")
            response.code match {
              case 200 => EchoBodyResponse.Ok(Jsoner.readThrowing[Message](body))
              case _ => 
                val errorMessage = s"Request returned unexpected status code: ${response.code}, body: ${new String(body)}"
                logger.error(errorMessage)
                throw new RuntimeException(errorMessage)
            }
          case Left(errorData) =>
            val errorMessage = s"Request failed, status code: ${response.code}, body: ${new String(errorData)}"
            logger.error(errorMessage)
            throw new RuntimeException(errorMessage)
        }
    }
  }
}
