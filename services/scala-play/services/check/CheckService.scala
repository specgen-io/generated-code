package services.check

import com.google.inject.ImplementedBy
import scala.concurrent.Future
import models._

@ImplementedBy(classOf[CheckService])
trait ICheckService {
  def checkEmpty(): Future[CheckEmptyResponse]
  def checkForbidden(): Future[CheckForbiddenResponse]
  def sameOperationName(): Future[SameOperationNameResponse]
}

sealed trait CheckEmptyResponse
object CheckEmptyResponse {
  case class Ok() extends CheckEmptyResponse
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
