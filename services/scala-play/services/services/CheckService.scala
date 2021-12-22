package services

import javax.inject._
import scala.concurrent._
import services.check._
import models._

@Singleton
class CheckService @Inject()()(implicit ec: ExecutionContext) extends ICheckService {
  override def checkEmpty(): Future[Unit] = Future { ??? }
  override def checkForbidden(): Future[CheckForbiddenResponse] = Future { ??? }
  override def sameOperationName(): Future[SameOperationNameResponse] = Future { ??? }
}
