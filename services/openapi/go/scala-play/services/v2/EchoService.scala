package services.v2

import javax.inject._
import scala.concurrent._
import models.v2._

@Singleton
class EchoService @Inject()()(implicit ec: ExecutionContext) extends IEchoService {
  import IEchoService._
  override def echoBody(body: Message): Future[EchoBodyResponse] = Future { ??? }
}
