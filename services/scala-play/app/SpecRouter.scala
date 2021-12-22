package app

import javax.inject._
import play.api.routing._

class SpecRouter @Inject()(echoV2: v2.routers.EchoRouter, echo: routers.EchoRouter, check: routers.CheckRouter) extends SimpleRouter {
  def routes: Router.Routes =
    Seq(
      echoV2.routes,
      echo.routes,
      check.routes,
    ).reduce { (r1, r2) => r1.orElse(r2) }
}
