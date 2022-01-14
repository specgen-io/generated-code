package testservice.check

import scala.concurrent._
import org.slf4j._
import com.softwaremill.sttp._
import testservice.ParamsTypesBindings._
import testservice.Jsoner
import testservice.models._

trait ICheckClient {
  def checkEmpty(): Future[Unit]
  def checkForbidden(): Future[CheckForbiddenResponse]
  def sameOperationName(): Future[SameOperationNameResponse]
}

sealed trait CheckForbiddenResponse
object CheckForbiddenResponse {
  case class Ok(body: Message) extends CheckForbiddenResponse
  case class Forbidden() extends CheckForbiddenResponse
}

sealed trait SameOperationNameResponse
object SameOperationNameResponse {
  case class Ok() extends SameOperationNameResponse
  case class Forbidden() extends SameOperationNameResponse
}

class CheckClient(baseUrl: String)(implicit backend: SttpBackend[Future, Nothing]) extends ICheckClient {
  import ExecutionContext.Implicits.global
  private val logger: Logger = LoggerFactory.getLogger(this.getClass)
  def checkEmpty(): Future[Unit] = {
    val url = Uri.parse(baseUrl+s"/check/empty").get
    logger.debug(s"Request to url: ${url}")
    val response: Future[Response[String]] =
      sttp
        .get(url)
        .parseResponseIf { status => status < 500 }
        .send()
    response.map {
      response: Response[String] =>
        response.body match {
          case Right(body) =>
            logger.debug(s"Response status: ${response.code}, body: ${body}")
            response.code match {
              case 200 => ()
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
  def checkForbidden(): Future[CheckForbiddenResponse] = {
    val url = Uri.parse(baseUrl+s"/check/forbidden").get
    logger.debug(s"Request to url: ${url}")
    val response: Future[Response[String]] =
      sttp
        .get(url)
        .parseResponseIf { status => status < 500 }
        .send()
    response.map {
      response: Response[String] =>
        response.body match {
          case Right(body) =>
            logger.debug(s"Response status: ${response.code}, body: ${body}")
            response.code match {
              case 200 => CheckForbiddenResponse.Ok(Jsoner.readThrowing[Message](body))
              case 403 => CheckForbiddenResponse.Forbidden()
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
  def sameOperationName(): Future[SameOperationNameResponse] = {
    val url = Uri.parse(baseUrl+s"/check/same_operation_name").get
    logger.debug(s"Request to url: ${url}")
    val response: Future[Response[String]] =
      sttp
        .get(url)
        .parseResponseIf { status => status < 500 }
        .send()
    response.map {
      response: Response[String] =>
        response.body match {
          case Right(body) =>
            logger.debug(s"Response status: ${response.code}, body: ${body}")
            response.code match {
              case 200 => SameOperationNameResponse.Ok()
              case 403 => SameOperationNameResponse.Forbidden()
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
