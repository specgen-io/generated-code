package services.check

import javax.inject._
import scala.concurrent._
import models._

@Singleton
class CheckService @Inject()()(implicit ec: ExecutionContext) extends ICheckService {
  override def checkEmpty(): Future[CheckEmptyResponse] = Future { ??? }
  override def checkForbidden(): Future[CheckForbiddenResponse] = Future { ??? }
  override def sameOperationName(): Future[SameOperationNameResponse] = Future { ??? }
}
