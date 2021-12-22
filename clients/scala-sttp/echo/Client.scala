package testservice.client.echo

import scala.concurrent._
import org.slf4j._
import com.softwaremill.sttp._
import testservice.client.ParamsTypesBindings._
import testservice.client.Jsoner
import testservice.client.models._

trait IEchoClient {
  def echoBodyString(body: String): Future[EchoBodyStringResponse]
  def echoBody(body: Message): Future[EchoBodyResponse]
  def echoQuery(intQuery: Int, longQuery: Long, floatQuery: Float, doubleQuery: Double, decimalQuery: BigDecimal, boolQuery: Boolean, stringQuery: String, stringOptQuery: Option[String], stringArrayQuery: List[String], uuidQuery: java.util.UUID, dateQuery: java.time.LocalDate, dateArrayQuery: List[java.time.LocalDate], datetimeQuery: java.time.LocalDateTime, enumQuery: Choice, stringDefaultedQuery: String = "the default value"): Future[EchoQueryResponse]
  def echoHeader(intHeader: Int, longHeader: Long, floatHeader: Float, doubleHeader: Double, decimalHeader: BigDecimal, boolHeader: Boolean, stringHeader: String, stringOptHeader: Option[String], stringArrayHeader: List[String], uuidHeader: java.util.UUID, dateHeader: java.time.LocalDate, dateArrayHeader: List[java.time.LocalDate], datetimeHeader: java.time.LocalDateTime, enumHeader: Choice, stringDefaultedHeader: String = "the default value"): Future[EchoHeaderResponse]
  def echoUrlParams(intUrl: Int, longUrl: Long, floatUrl: Float, doubleUrl: Double, decimalUrl: BigDecimal, boolUrl: Boolean, stringUrl: String, uuidUrl: java.util.UUID, dateUrl: java.time.LocalDate, datetimeUrl: java.time.LocalDateTime, enumUrl: Choice): Future[EchoUrlParamsResponse]
  def echoEverything(uuidHeader: java.util.UUID, datetimeHeader: java.time.LocalDateTime, body: Message, dateUrl: java.time.LocalDate, decimalUrl: BigDecimal, floatQuery: Float, boolQuery: Boolean): Future[EchoEverythingResponse]
  def sameOperationName(): Future[SameOperationNameResponse]
}

sealed trait EchoBodyStringResponse
object EchoBodyStringResponse {
  case class Ok(body: String) extends EchoBodyStringResponse
}

sealed trait EchoBodyResponse
object EchoBodyResponse {
  case class Ok(body: Message) extends EchoBodyResponse
}

sealed trait EchoQueryResponse
object EchoQueryResponse {
  case class Ok(body: Parameters) extends EchoQueryResponse
}

sealed trait EchoHeaderResponse
object EchoHeaderResponse {
  case class Ok(body: Parameters) extends EchoHeaderResponse
}

sealed trait EchoUrlParamsResponse
object EchoUrlParamsResponse {
  case class Ok(body: UrlParameters) extends EchoUrlParamsResponse
}

sealed trait EchoEverythingResponse
object EchoEverythingResponse {
  case class Ok(body: Everything) extends EchoEverythingResponse
  case class Forbidden() extends EchoEverythingResponse
}

sealed trait SameOperationNameResponse
object SameOperationNameResponse {
  case class Ok() extends SameOperationNameResponse
  case class Forbidden() extends SameOperationNameResponse
}

class EchoClient(baseUrl: String)(implicit backend: SttpBackend[Future, Nothing]) extends IEchoClient {
  import ExecutionContext.Implicits.global
  private val logger: Logger = LoggerFactory.getLogger(this.getClass)
  def echoBodyString(body: String): Future[EchoBodyStringResponse] = {
    val url = Uri.parse(baseUrl+s"/echo/body_string").get
    logger.debug(s"Request to url: ${url}, body: ${body}")
    val response: Future[Response[String]] =
      sttp
        .post(url)
        .header("Content-Type", "application/text")
        .body(body)
        .parseResponseIf { status => status < 500 }
        .send()
    response.map {
      response: Response[String] =>
        response.body match {
          case Right(body) =>
            logger.debug(s"Response status: ${response.code}, body: ${body}")
            response.code match {
              case 200 => EchoBodyStringResponse.Ok(body)
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
  def echoBody(body: Message): Future[EchoBodyResponse] = {
    val url = Uri.parse(baseUrl+s"/echo/body").get
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
  def echoQuery(intQuery: Int, longQuery: Long, floatQuery: Float, doubleQuery: Double, decimalQuery: BigDecimal, boolQuery: Boolean, stringQuery: String, stringOptQuery: Option[String], stringArrayQuery: List[String], uuidQuery: java.util.UUID, dateQuery: java.time.LocalDate, dateArrayQuery: List[java.time.LocalDate], datetimeQuery: java.time.LocalDateTime, enumQuery: Choice, stringDefaultedQuery: String = "the default value"): Future[EchoQueryResponse] = {
    val query = new StringParamsWriter()
    query.write("int_query", intQuery)
    query.write("long_query", longQuery)
    query.write("float_query", floatQuery)
    query.write("double_query", doubleQuery)
    query.write("decimal_query", decimalQuery)
    query.write("bool_query", boolQuery)
    query.write("string_query", stringQuery)
    query.write("string_opt_query", stringOptQuery)
    query.write("string_defaulted_query", stringDefaultedQuery)
    query.write("string_array_query", stringArrayQuery)
    query.write("uuid_query", uuidQuery)
    query.write("date_query", dateQuery)
    query.write("date_array_query", dateArrayQuery)
    query.write("datetime_query", datetimeQuery)
    query.write("enum_query", enumQuery)
    val url = Uri.parse(baseUrl+s"/echo/query").get.params(query.params:_*)
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
              case 200 => EchoQueryResponse.Ok(Jsoner.readThrowing[Parameters](body))
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
  def echoHeader(intHeader: Int, longHeader: Long, floatHeader: Float, doubleHeader: Double, decimalHeader: BigDecimal, boolHeader: Boolean, stringHeader: String, stringOptHeader: Option[String], stringArrayHeader: List[String], uuidHeader: java.util.UUID, dateHeader: java.time.LocalDate, dateArrayHeader: List[java.time.LocalDate], datetimeHeader: java.time.LocalDateTime, enumHeader: Choice, stringDefaultedHeader: String = "the default value"): Future[EchoHeaderResponse] = {
    val url = Uri.parse(baseUrl+s"/echo/header").get
    val headers = new StringParamsWriter()
    headers.write("Int-Header", intHeader)
    headers.write("Long-Header", longHeader)
    headers.write("Float-Header", floatHeader)
    headers.write("Double-Header", doubleHeader)
    headers.write("Decimal-Header", decimalHeader)
    headers.write("Bool-Header", boolHeader)
    headers.write("String-Header", stringHeader)
    headers.write("String-Opt-Header", stringOptHeader)
    headers.write("String-Defaulted-Header", stringDefaultedHeader)
    headers.write("String-Array-Header", stringArrayHeader)
    headers.write("Uuid-Header", uuidHeader)
    headers.write("Date-Header", dateHeader)
    headers.write("Date-Array-Header", dateArrayHeader)
    headers.write("Datetime-Header", datetimeHeader)
    headers.write("Enum-Header", enumHeader)
    logger.debug(s"Request to url: ${url}")
    val response: Future[Response[String]] =
      sttp
        .get(url)
        .headers(headers.params:_*)
        .parseResponseIf { status => status < 500 }
        .send()
    response.map {
      response: Response[String] =>
        response.body match {
          case Right(body) =>
            logger.debug(s"Response status: ${response.code}, body: ${body}")
            response.code match {
              case 200 => EchoHeaderResponse.Ok(Jsoner.readThrowing[Parameters](body))
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
  def echoUrlParams(intUrl: Int, longUrl: Long, floatUrl: Float, doubleUrl: Double, decimalUrl: BigDecimal, boolUrl: Boolean, stringUrl: String, uuidUrl: java.util.UUID, dateUrl: java.time.LocalDate, datetimeUrl: java.time.LocalDateTime, enumUrl: Choice): Future[EchoUrlParamsResponse] = {
    val url = Uri.parse(baseUrl+s"/echo/url_params/${stringify(intUrl)}/${stringify(longUrl)}/${stringify(floatUrl)}/${stringify(doubleUrl)}/${stringify(decimalUrl)}/${stringify(boolUrl)}/${stringify(stringUrl)}/${stringify(uuidUrl)}/${stringify(dateUrl)}/${stringify(datetimeUrl)}/${stringify(enumUrl)}").get
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
              case 200 => EchoUrlParamsResponse.Ok(Jsoner.readThrowing[UrlParameters](body))
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
  def echoEverything(uuidHeader: java.util.UUID, datetimeHeader: java.time.LocalDateTime, body: Message, dateUrl: java.time.LocalDate, decimalUrl: BigDecimal, floatQuery: Float, boolQuery: Boolean): Future[EchoEverythingResponse] = {
    val query = new StringParamsWriter()
    query.write("float_query", floatQuery)
    query.write("bool_query", boolQuery)
    val url = Uri.parse(baseUrl+s"/echo/everything/${stringify(dateUrl)}/${stringify(decimalUrl)}").get.params(query.params:_*)
    val headers = new StringParamsWriter()
    headers.write("Uuid-Header", uuidHeader)
    headers.write("Datetime-Header", datetimeHeader)
    val bodyJson = Jsoner.write(body)
    logger.debug(s"Request to url: ${url}, body: ${bodyJson}")
    val response: Future[Response[String]] =
      sttp
        .post(url)
        .headers(headers.params:_*)
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
              case 200 => EchoEverythingResponse.Ok(Jsoner.readThrowing[Everything](body))
              case 403 => EchoEverythingResponse.Forbidden()
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
    val url = Uri.parse(baseUrl+s"/echo/same_operation_name").get
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
