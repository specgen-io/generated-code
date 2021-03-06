package services.check

import com.google.inject.ImplementedBy
import scala.concurrent.Future
import models._

@ImplementedBy(classOf[services.CheckService])
trait ICheckService {
  def checkEmpty(): Future[Unit]
  def checkEmptyResponse(body: Message): Future[Unit]
  def checkForbidden(): Future[CheckForbiddenResponse]
  def sameOperationName(): Future[SameOperationNameResponse]
}

sealed trait CheckForbiddenResponse
object CheckForbiddenResponse {
  case class Ok(body: Message) extends CheckForbiddenResponse
  case class Forbidden() extends CheckForbiddenResponse
}

sealed trait SameOperationNameResponse
object SameOperationNameResponse {
  case class Ok() extends SameOperationNameResponse
  case class Forbidden() extends SameOperationNameResponse
}
