package v2.services.echo

import com.google.inject.ImplementedBy
import scala.concurrent.Future
import v2.models._

@ImplementedBy(classOf[v2.services.EchoService])
trait IEchoService {
  def echoBody(body: Message): Future[EchoBodyResponse]
}

sealed trait EchoBodyResponse
object EchoBodyResponse {
  case class Ok(body: Message) extends EchoBodyResponse
}
