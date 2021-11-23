package testservice.client

import scala.concurrent._
import org.slf4j._
import com.softwaremill.sttp._
import spec.Jsoner
import spec.ParamsTypesBindings._

class EchoClient(baseUrl: String)(implicit backend: SttpBackend[Future, Nothing]) extends IEchoClient {
  import IEchoClient._
  import ExecutionContext.Implicits.global
  private val logger: Logger = LoggerFactory.getLogger(this.getClass)
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
  def echoQuery(intQuery: Int, stringQuery: String): Future[EchoQueryResponse] = {
    val query = new StringParamsWriter()
    query.write("int_query", intQuery)
    query.write("string_query", stringQuery)
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
              case 200 => EchoQueryResponse.Ok(Jsoner.readThrowing[Message](body))
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
  def echoHeader(intHeader: Int, stringHeader: String): Future[EchoHeaderResponse] = {
    val url = Uri.parse(baseUrl+s"/echo/header").get
    val headers = new StringParamsWriter()
    headers.write("Int-Header", intHeader)
    headers.write("String-Header", stringHeader)
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
              case 200 => EchoHeaderResponse.Ok(Jsoner.readThrowing[Message](body))
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
  def echoUrlParams(intUrl: Int, stringUrl: String): Future[EchoUrlParamsResponse] = {
    val url = Uri.parse(baseUrl+s"/echo/url_params/$intUrl/$stringUrl").get
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
              case 200 => EchoUrlParamsResponse.Ok(Jsoner.readThrowing[Message](body))
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
              case _ => 
                val errorMessage = s"Request returned unexpected status code: ${response.code}, body: ${new String(body)}"
                logger.error(errorMessage)
                throw new RuntimeException(errorMessage)
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

class CheckClient(baseUrl: String)(implicit backend: SttpBackend[Future, Nothing]) extends ICheckClient {
  import ICheckClient._
  import ExecutionContext.Implicits.global
  private val logger: Logger = LoggerFactory.getLogger(this.getClass)
  def checkEmpty(): Future[CheckEmptyResponse] = {
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
              case 200 => CheckEmptyResponse.Ok()
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
  def checkQuery(pString: String, pStringOpt: Option[String], pStringArray: List[String], pDate: java.time.LocalDate, pDateArray: List[java.time.LocalDate], pDatetime: java.time.LocalDateTime, pInt: Int, pLong: Long, pDecimal: BigDecimal, pEnum: Choice, pStringDefaulted: String = "the default value"): Future[CheckQueryResponse] = {
    val query = new StringParamsWriter()
    query.write("p_string", pString)
    query.write("p_string_opt", pStringOpt)
    query.write("p_string_array", pStringArray)
    query.write("p_date", pDate)
    query.write("p_date_array", pDateArray)
    query.write("p_datetime", pDatetime)
    query.write("p_int", pInt)
    query.write("p_long", pLong)
    query.write("p_decimal", pDecimal)
    query.write("p_enum", pEnum)
    query.write("p_string_defaulted", pStringDefaulted)
    val url = Uri.parse(baseUrl+s"/check/query").get.params(query.params:_*)
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
              case 200 => CheckQueryResponse.Ok()
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
  def checkUrlParams(intUrl: Long, stringUrl: String, floatUrl: Float, boolUrl: Boolean, uuidUrl: java.util.UUID, decimalUrl: BigDecimal, dateUrl: java.time.LocalDate, enumUrl: Choice): Future[CheckUrlParamsResponse] = {
    val url = Uri.parse(baseUrl+s"/check/url_params/$intUrl/$stringUrl/$floatUrl/$boolUrl/$uuidUrl/$decimalUrl/$dateUrl/$enumUrl").get
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
              case 200 => CheckUrlParamsResponse.Ok()
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
              case _ => 
                val errorMessage = s"Request returned unexpected status code: ${response.code}, body: ${new String(body)}"
                logger.error(errorMessage)
                throw new RuntimeException(errorMessage)
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
              case _ => 
                val errorMessage = s"Request returned unexpected status code: ${response.code}, body: ${new String(body)}"
                logger.error(errorMessage)
                throw new RuntimeException(errorMessage)
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
