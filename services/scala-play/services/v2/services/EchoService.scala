package v2.services

import javax.inject._
import scala.concurrent._
import v2.services.echo._
import v2.models._

@Singleton
class EchoService @Inject()()(implicit ec: ExecutionContext) extends IEchoService {
  override def echoBody(body: Message): Future[EchoBodyResponse] = Future { ??? }
}
