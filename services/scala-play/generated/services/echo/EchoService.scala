package services.echo

import com.google.inject.ImplementedBy
import scala.concurrent.Future
import models._

@ImplementedBy(classOf[services.EchoService])
trait IEchoService {
  def echoBodyString(body: String): Future[String]
  def echoBodyModel(body: Message): Future[Message]
  def echoBodyArray(body: List[String]): Future[List[String]]
  def echoBodyMap(body: Map[String, String]): Future[Map[String, String]]
  def echoQuery(intQuery: Int, longQuery: Long, floatQuery: Float, doubleQuery: Double, decimalQuery: BigDecimal, boolQuery: Boolean, stringQuery: String, stringOptQuery: Option[String], stringDefaultedQuery: String, stringArrayQuery: List[String], uuidQuery: java.util.UUID, dateQuery: java.time.LocalDate, dateArrayQuery: List[java.time.LocalDate], datetimeQuery: java.time.LocalDateTime, enumQuery: Choice): Future[Parameters]
  def echoHeader(intHeader: Int, longHeader: Long, floatHeader: Float, doubleHeader: Double, decimalHeader: BigDecimal, boolHeader: Boolean, stringHeader: String, stringOptHeader: Option[String], stringDefaultedHeader: String, stringArrayHeader: List[String], uuidHeader: java.util.UUID, dateHeader: java.time.LocalDate, dateArrayHeader: List[java.time.LocalDate], datetimeHeader: java.time.LocalDateTime, enumHeader: Choice): Future[Parameters]
  def echoUrlParams(intUrl: Int, longUrl: Long, floatUrl: Float, doubleUrl: Double, decimalUrl: BigDecimal, boolUrl: Boolean, stringUrl: String, uuidUrl: java.util.UUID, dateUrl: java.time.LocalDate, datetimeUrl: java.time.LocalDateTime, enumUrl: Choice): Future[UrlParameters]
  def echoEverything(uuidHeader: java.util.UUID, datetimeHeader: java.time.LocalDateTime, body: Message, dateUrl: java.time.LocalDate, decimalUrl: BigDecimal, floatQuery: Float, boolQuery: Boolean): Future[EchoEverythingResponse]
  def sameOperationName(): Future[SameOperationNameResponse]
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
