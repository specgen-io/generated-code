package v2.routers

import javax.inject._
import play.api.mvc._
import play.api.routing._
import play.core.routing._
import params.ParamsTypesBindings._
import params.PlayParamsTypesBindings._
import v2.controllers._
import v2.models._

class EchoRouter @Inject()(Action: DefaultActionBuilder, controller: EchoController) extends SimpleRouter {
  lazy val routeEchoBody = Route("POST", PathPattern(List(
    StaticPart("/v2"),
    StaticPart("/echo/body"),
  )))
  def routes: Router.Routes = {
    case routeEchoBody(params@_) =>
      controller.echoBody()
  }
}
