package routers

import javax.inject._
import play.api.mvc._
import play.api.routing._
import play.core.routing._
import params.ParamsTypesBindings._
import params.PlayParamsTypesBindings._
import controllers._
import models._

class CheckRouter @Inject()(Action: DefaultActionBuilder, controller: CheckController) extends SimpleRouter {
  lazy val routeCheckEmpty = Route("GET", PathPattern(List(
    StaticPart("/check/empty"),
  )))
  lazy val routeCheckEmptyResponse = Route("POST", PathPattern(List(
    StaticPart("/check/empty_response"),
  )))
  lazy val routeCheckForbidden = Route("GET", PathPattern(List(
    StaticPart("/check/forbidden"),
  )))
  lazy val routeSameOperationName = Route("GET", PathPattern(List(
    StaticPart("/check/same_operation_name"),
  )))
  def routes: Router.Routes = {
    case routeCheckEmpty(params@_) =>
      controller.checkEmpty()
    case routeCheckEmptyResponse(params@_) =>
      controller.checkEmptyResponse()
    case routeCheckForbidden(params@_) =>
      controller.checkForbidden()
    case routeSameOperationName(params@_) =>
      controller.sameOperationName()
  }
}
