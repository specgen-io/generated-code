package testservice.models.v2

import scala.concurrent._

trait IEchoClient {
  import IEchoClient._
  def echoBody(body: Message): Future[EchoBodyResponse]
}

object IEchoClient {
  sealed trait EchoBodyResponse
  object EchoBodyResponse {
    case class Ok(body: Message) extends EchoBodyResponse
  }
}
