package services

import com.google.inject.ImplementedBy
import scala.concurrent.Future
import models._

@ImplementedBy(classOf[CheckService])
trait ICheckService {
  import ICheckService._
  def checkEmpty(): Future[CheckEmptyResponse]
  def checkQuery(pString: String, pStringOpt: Option[String], pStringArray: List[String], pDate: java.time.LocalDate, pDateArray: List[java.time.LocalDate], pDatetime: java.time.LocalDateTime, pInt: Int, pLong: Long, pDecimal: BigDecimal, pEnum: Choice, pStringDefaulted: String): Future[CheckQueryResponse]
  def checkUrlParams(intUrl: Long, stringUrl: String, floatUrl: Float, boolUrl: Boolean, uuidUrl: java.util.UUID, decimalUrl: BigDecimal, dateUrl: java.time.LocalDate): Future[CheckUrlParamsResponse]
  def checkForbidden(): Future[CheckForbiddenResponse]
}

object ICheckService {
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
}
