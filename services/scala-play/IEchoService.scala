package services

import com.google.inject.ImplementedBy
import scala.concurrent.Future
import models._

@ImplementedBy(classOf[EchoService])
trait IEchoService {
  import IEchoService._
  def echoBody(body: Message): Future[EchoBodyResponse]
  def echoQuery(intQuery: Int, stringQuery: String): Future[EchoQueryResponse]
  def echoHeader(intHeader: Int, stringHeader: String): Future[EchoHeaderResponse]
  def echoUrlParams(intUrl: Int, stringUrl: String): Future[EchoUrlParamsResponse]
}

object IEchoService {
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
}
