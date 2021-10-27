package testservice.client

import scala.concurrent._

trait IEchoClient {
  import IEchoClient._
  def echoBody(body: Message): Future[EchoBodyResponse]
  def echoQuery(intQuery: Int, stringQuery: String): Future[EchoQueryResponse]
  def echoHeader(intHeader: Int, stringHeader: String): Future[EchoHeaderResponse]
  def echoUrlParams(intUrl: Int, stringUrl: String): Future[EchoUrlParamsResponse]
  def sameOperationName(): Future[SameOperationNameResponse]
}

trait ICheckClient {
  import ICheckClient._
  def checkEmpty(): Future[CheckEmptyResponse]
  def checkQuery(pString: String, pStringOpt: Option[String], pStringArray: List[String], pDate: java.time.LocalDate, pDateArray: List[java.time.LocalDate], pDatetime: java.time.LocalDateTime, pInt: Int, pLong: Long, pDecimal: BigDecimal, pEnum: Choice, pStringDefaulted: String = "the default value"): Future[CheckQueryResponse]
  def checkUrlParams(intUrl: Long, stringUrl: String, floatUrl: Float, boolUrl: Boolean, uuidUrl: java.util.UUID, decimalUrl: BigDecimal, dateUrl: java.time.LocalDate): Future[CheckUrlParamsResponse]
  def checkForbidden(): Future[CheckForbiddenResponse]
  def sameOperationName(): Future[SameOperationNameResponse]
}

object IEchoClient {
  sealed trait EchoBodyResponse
  object EchoBodyResponse {
    case class Ok(body: Message) extends EchoBodyResponse
  }
  sealed trait EchoQueryResponse
  object EchoQueryResponse {
    case class Ok(body: Message) extends EchoQueryResponse
  }
  sealed trait EchoHeaderResponse
  object EchoHeaderResponse {
    case class Ok(body: Message) extends EchoHeaderResponse
  }
  sealed trait EchoUrlParamsResponse
  object EchoUrlParamsResponse {
    case class Ok(body: Message) extends EchoUrlParamsResponse
  }
  sealed trait SameOperationNameResponse
  object SameOperationNameResponse {
    case class Ok() extends SameOperationNameResponse
    case class Forbidden() extends SameOperationNameResponse
  }
}

object ICheckClient {
  sealed trait CheckEmptyResponse
  object CheckEmptyResponse {
    case class Ok() extends CheckEmptyResponse
  }
  sealed trait CheckQueryResponse
  object CheckQueryResponse {
    case class Ok() extends CheckQueryResponse
  }
  sealed trait CheckUrlParamsResponse
  object CheckUrlParamsResponse {
    case class Ok() extends CheckUrlParamsResponse
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
}
