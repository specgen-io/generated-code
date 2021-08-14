package app

import javax.inject._
import play.api.mvc._
import play.api.routing._
import play.core.routing._
import spec.controllers.ParamsTypesBindings._
import spec.controllers.PlayParamsTypesBindings._
import controllers._
import models._

class EchoRouter @Inject()(Action: DefaultActionBuilder, controller: EchoController) extends SimpleRouter {
  lazy val routeEchoBody = Route("POST", PathPattern(List(
    StaticPart("/echo/body"),
  )))
  lazy val routeEchoQuery = Route("GET", PathPattern(List(
    StaticPart("/echo/query"),
  )))
  lazy val routeEchoHeader = Route("GET", PathPattern(List(
    StaticPart("/echo/header"),
  )))
  lazy val routeEchoUrlParams = Route("GET", PathPattern(List(
    StaticPart("/echo/url_params/"),
    DynamicPart("int_url", """[^/]+""", true),
    StaticPart("/"),
    DynamicPart("string_url", """[^/]+""", true),
  )))
  def routes: Router.Routes = {
    case routeEchoBody(params@_) =>
      controller.echoBody()
    case routeEchoQuery(params@_) =>
      val arguments =
        for {
          intQuery <- params.fromQuery[Int]("int_query", None).value
          stringQuery <- params.fromQuery[String]("string_query", None).value
        }
        yield (intQuery, stringQuery)
      arguments match{
        case Left(_) => Action { Results.BadRequest }
        case Right((intQuery, stringQuery)) => controller.echoQuery(intQuery, stringQuery)
      }
    case routeEchoHeader(params@_) =>
      controller.echoHeader()
    case routeEchoUrlParams(params@_) =>
      val arguments =
        for {
          intUrl <- params.fromPath[Int]("int_url").value
          stringUrl <- params.fromPath[String]("string_url").value
        }
        yield (intUrl, stringUrl)
      arguments match{
        case Left(_) => Action { Results.BadRequest }
        case Right((intUrl, stringUrl)) => controller.echoUrlParams(intUrl, stringUrl)
      }
  }
}

class CheckRouter @Inject()(Action: DefaultActionBuilder, controller: CheckController) extends SimpleRouter {
  lazy val routeCheckEmpty = Route("GET", PathPattern(List(
    StaticPart("/check/empty"),
  )))
  lazy val routeCheckQuery = Route("GET", PathPattern(List(
    StaticPart("/check/query"),
  )))
  lazy val routeCheckUrlParams = Route("GET", PathPattern(List(
    StaticPart("/check/url_params/"),
    DynamicPart("int_url", """[^/]+""", true),
    StaticPart("/"),
    DynamicPart("string_url", """[^/]+""", true),
    StaticPart("/"),
    DynamicPart("float_url", """[^/]+""", true),
    StaticPart("/"),
    DynamicPart("bool_url", """[^/]+""", true),
    StaticPart("/"),
    DynamicPart("uuid_url", """[^/]+""", true),
    StaticPart("/"),
    DynamicPart("decimal_url", """[^/]+""", true),
    StaticPart("/"),
    DynamicPart("date_url", """[^/]+""", true),
  )))
  lazy val routeCheckForbidden = Route("GET", PathPattern(List(
    StaticPart("/check/forbidden"),
  )))
  def routes: Router.Routes = {
    case routeCheckEmpty(params@_) =>
      controller.checkEmpty()
    case routeCheckQuery(params@_) =>
      val arguments =
        for {
          pString <- params.fromQuery[String]("p_string", None).value
          pStringOpt <- params.fromQuery[Option[String]]("p_string_opt", None).value
          pStringArray <- params.fromQuery[List[String]]("p_string_array", None).value
          pDate <- params.fromQuery[java.time.LocalDate]("p_date", None).value
          pDateArray <- params.fromQuery[List[java.time.LocalDate]]("p_date_array", None).value
          pDatetime <- params.fromQuery[java.time.LocalDateTime]("p_datetime", None).value
          pInt <- params.fromQuery[Int]("p_int", None).value
          pLong <- params.fromQuery[Long]("p_long", None).value
          pDecimal <- params.fromQuery[BigDecimal]("p_decimal", None).value
          pEnum <- params.fromQuery[Choice]("p_enum", None).value
          pStringDefaulted <- params.fromQuery[String]("p_string_defaulted", Some("the default value")).value
        }
        yield (pString, pStringOpt, pStringArray, pDate, pDateArray, pDatetime, pInt, pLong, pDecimal, pEnum, pStringDefaulted)
      arguments match{
        case Left(_) => Action { Results.BadRequest }
        case Right((pString, pStringOpt, pStringArray, pDate, pDateArray, pDatetime, pInt, pLong, pDecimal, pEnum, pStringDefaulted)) => controller.checkQuery(pString, pStringOpt, pStringArray, pDate, pDateArray, pDatetime, pInt, pLong, pDecimal, pEnum, pStringDefaulted)
      }
    case routeCheckUrlParams(params@_) =>
      val arguments =
        for {
          intUrl <- params.fromPath[Long]("int_url").value
          stringUrl <- params.fromPath[String]("string_url").value
          floatUrl <- params.fromPath[Float]("float_url").value
          boolUrl <- params.fromPath[Boolean]("bool_url").value
          uuidUrl <- params.fromPath[java.util.UUID]("uuid_url").value
          decimalUrl <- params.fromPath[BigDecimal]("decimal_url").value
          dateUrl <- params.fromPath[java.time.LocalDate]("date_url").value
        }
        yield (intUrl, stringUrl, floatUrl, boolUrl, uuidUrl, decimalUrl, dateUrl)
      arguments match{
        case Left(_) => Action { Results.BadRequest }
        case Right((intUrl, stringUrl, floatUrl, boolUrl, uuidUrl, decimalUrl, dateUrl)) => controller.checkUrlParams(intUrl, stringUrl, floatUrl, boolUrl, uuidUrl, decimalUrl, dateUrl)
      }
    case routeCheckForbidden(params@_) =>
      controller.checkForbidden()
  }
}
