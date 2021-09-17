package services

import javax.inject._
import scala.concurrent._
import models._

@Singleton
class CheckService @Inject()()(implicit ec: ExecutionContext) extends ICheckService {
  import ICheckService._
  override def checkEmpty(): Future[CheckEmptyResponse] = Future { ??? }
  override def checkQuery(pString: String, pStringOpt: Option[String], pStringArray: List[String], pDate: java.time.LocalDate, pDateArray: List[java.time.LocalDate], pDatetime: java.time.LocalDateTime, pInt: Int, pLong: Long, pDecimal: BigDecimal, pEnum: Choice, pStringDefaulted: String): Future[CheckQueryResponse] = Future { ??? }
  override def checkUrlParams(intUrl: Long, stringUrl: String, floatUrl: Float, boolUrl: Boolean, uuidUrl: java.util.UUID, decimalUrl: BigDecimal, dateUrl: java.time.LocalDate, enumUrl: Choice): Future[CheckUrlParamsResponse] = Future { ??? }
  override def checkForbidden(): Future[CheckForbiddenResponse] = Future { ??? }
}
