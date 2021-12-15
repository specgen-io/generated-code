package services

import javax.inject._
import scala.concurrent._
import models._

@Singleton
class EchoService @Inject()()(implicit ec: ExecutionContext) extends IEchoService {
  import IEchoService._
  override def echoBodyString(body: String): Future[EchoBodyStringResponse] = Future { ??? }
  override def echoBody(body: Message): Future[EchoBodyResponse] = Future { ??? }
  override def echoQuery(intQuery: Int, longQuery: Long, floatQuery: Float, doubleQuery: Double, decimalQuery: BigDecimal, boolQuery: Boolean, stringQuery: String, stringOptQuery: Option[String], stringDefaultedQuery: String, stringArrayQuery: List[String], uuidQuery: java.util.UUID, dateQuery: java.time.LocalDate, dateArrayQuery: List[java.time.LocalDate], datetimeQuery: java.time.LocalDateTime, enumQuery: Choice): Future[EchoQueryResponse] = Future { ??? }
  override def echoHeader(intHeader: Int, longHeader: Long, floatHeader: Float, doubleHeader: Double, decimalHeader: BigDecimal, boolHeader: Boolean, stringHeader: String, stringOptHeader: Option[String], stringDefaultedHeader: String, stringArrayHeader: List[String], uuidHeader: java.util.UUID, dateHeader: java.time.LocalDate, dateArrayHeader: List[java.time.LocalDate], datetimeHeader: java.time.LocalDateTime, enumHeader: Choice): Future[EchoHeaderResponse] = Future { ??? }
  override def echoUrlParams(intUrl: Int, longUrl: Long, floatUrl: Float, doubleUrl: Double, decimalUrl: BigDecimal, boolUrl: Boolean, stringUrl: String, uuidUrl: java.util.UUID, dateUrl: java.time.LocalDate, datetimeUrl: java.time.LocalDateTime, enumUrl: Choice): Future[EchoUrlParamsResponse] = Future { ??? }
  override def echoEverything(uuidHeader: java.util.UUID, datetimeHeader: java.time.LocalDateTime, body: Message, dateUrl: java.time.LocalDate, decimalUrl: BigDecimal, floatQuery: Float, boolQuery: Boolean): Future[EchoEverythingResponse] = Future { ??? }
  override def sameOperationName(): Future[SameOperationNameResponse] = Future { ??? }
}
