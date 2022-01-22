package testservice.v2.echo

import scala.concurrent._
import org.slf4j._
import com.softwaremill.sttp._
import testservice.ParamsTypesBindings._
import testservice.Jsoner
import testservice.v2.models._

trait IEchoClient {
  def echoBodyModel(body: Message): Future[Message]
}

class EchoClient(baseUrl: String)(implicit backend: SttpBackend[Future, Nothing]) extends IEchoClient {
  import ExecutionContext.Implicits.global
  private val logger: Logger = LoggerFactory.getLogger(this.getClass)
  def echoBodyModel(body: Message): Future[Message] = {
    val url = Uri.parse(baseUrl+s"/v2/echo/body_model").get
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
              case 200 => Jsoner.readThrowing[Message](body)
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
