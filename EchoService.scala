package services

import javax.inject._
import scala.concurrent._
import models._

@Singleton
class EchoService @Inject()()(implicit ec: ExecutionContext) extends IEchoService {
  import IEchoService._
  override def echoBody(body: Message): Future[EchoBodyResponse] = Future { ??? }
  override def echoQuery(intQuery: Int, stringQuery: String): Future[EchoQueryResponse] = Future { ??? }
  override def echoHeader(intHeader: Int, stringHeader: String): Future[EchoHeaderResponse] = Future { ??? }
  override def echoUrlParams(intUrl: Int, stringUrl: String): Future[EchoUrlParamsResponse] = Future { ??? }
}
