package app.v2

import javax.inject._
import play.api.mvc._
import play.api.routing._
import play.core.routing._
import spec.controllers.ParamsTypesBindings._
import spec.controllers.PlayParamsTypesBindings._
import controllers.v2._
import models.v2._

class EchoRouter @Inject()(Action: DefaultActionBuilder, controller: EchoController) extends SimpleRouter {
  lazy val routeEchoBody = Route("POST", PathPattern(List(
    StaticPart("/v2/echo/body"),
  )))
  def routes: Router.Routes = {
    case routeEchoBody(params@_) =>
      controller.echoBody()
  }
}
