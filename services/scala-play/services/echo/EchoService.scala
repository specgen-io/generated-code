package services.echo

import com.google.inject.ImplementedBy
import scala.concurrent.Future
import models._

@ImplementedBy(classOf[services.EchoService])
trait IEchoService {
  def echoBodyString(body: String): Future[EchoBodyStringResponse]
  def echoBody(body: Message): Future[EchoBodyResponse]
  def echoQuery(intQuery: Int, longQuery: Long, floatQuery: Float, doubleQuery: Double, decimalQuery: BigDecimal, boolQuery: Boolean, stringQuery: String, stringOptQuery: Option[String], stringDefaultedQuery: String, stringArrayQuery: List[String], uuidQuery: java.util.UUID, dateQuery: java.time.LocalDate, dateArrayQuery: List[java.time.LocalDate], datetimeQuery: java.time.LocalDateTime, enumQuery: Choice): Future[EchoQueryResponse]
  def echoHeader(intHeader: Int, longHeader: Long, floatHeader: Float, doubleHeader: Double, decimalHeader: BigDecimal, boolHeader: Boolean, stringHeader: String, stringOptHeader: Option[String], stringDefaultedHeader: String, stringArrayHeader: List[String], uuidHeader: java.util.UUID, dateHeader: java.time.LocalDate, dateArrayHeader: List[java.time.LocalDate], datetimeHeader: java.time.LocalDateTime, enumHeader: Choice): Future[EchoHeaderResponse]
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
