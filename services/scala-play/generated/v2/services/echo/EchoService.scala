package v2.services.echo

import com.google.inject.ImplementedBy
import scala.concurrent.Future
import v2.models._

@ImplementedBy(classOf[v2.services.EchoService])
trait IEchoService {
  def echoBodyModel(body: Message): Future[Message]
}
