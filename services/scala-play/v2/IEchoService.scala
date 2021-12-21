package services.v2

import com.google.inject.ImplementedBy
import scala.concurrent.Future
import models.v2._

@ImplementedBy(classOf[EchoService])
trait IEchoService {
  import IEchoService._
  def echoBody(body: Message): Future[EchoBodyResponse]
}

object IEchoService {
  sealed trait EchoBodyResponse
  object EchoBodyResponse {
    case class Ok(body: Message) extends EchoBodyResponse
  }
}
