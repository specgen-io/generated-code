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
  lazy val routeEchoBodyString = Route("POST", PathPattern(List(
    StaticPart("/echo/body_string"),
  )))
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
    DynamicPart("long_url", """[^/]+""", true),
    StaticPart("/"),
    DynamicPart("float_url", """[^/]+""", true),
    StaticPart("/"),
    DynamicPart("double_url", """[^/]+""", true),
    StaticPart("/"),
    DynamicPart("decimal_url", """[^/]+""", true),
    StaticPart("/"),
    DynamicPart("bool_url", """[^/]+""", true),
    StaticPart("/"),
    DynamicPart("string_url", """[^/]+""", true),
    StaticPart("/"),
    DynamicPart("uuid_url", """[^/]+""", true),
    StaticPart("/"),
    DynamicPart("date_url", """[^/]+""", true),
    StaticPart("/"),
    DynamicPart("datetime_url", """[^/]+""", true),
    StaticPart("/"),
    DynamicPart("enum_url", """[^/]+""", true),
  )))
  lazy val routeEchoEverything = Route("POST", PathPattern(List(
    StaticPart("/echo/everything/"),
    DynamicPart("date_url", """[^/]+""", true),
    StaticPart("/"),
    DynamicPart("decimal_url", """[^/]+""", true),
  )))
  lazy val routeSameOperationName = Route("GET", PathPattern(List(
    StaticPart("/echo/same_operation_name"),
  )))
  def routes: Router.Routes = {
    case routeEchoBodyString(params@_) =>
      controller.echoBodyString()
    case routeEchoBody(params@_) =>
      controller.echoBody()
    case routeEchoQuery(params@_) =>
      val arguments =
        for {
          intQuery <- params.fromQuery[Int]("int_query", None).value
          longQuery <- params.fromQuery[Long]("long_query", None).value
          floatQuery <- params.fromQuery[Float]("float_query", None).value
          doubleQuery <- params.fromQuery[Double]("double_query", None).value
          decimalQuery <- params.fromQuery[BigDecimal]("decimal_query", None).value
          boolQuery <- params.fromQuery[Boolean]("bool_query", None).value
          stringQuery <- params.fromQuery[String]("string_query", None).value
          stringOptQuery <- params.fromQuery[Option[String]]("string_opt_query", None).value
          stringDefaultedQuery <- params.fromQuery[String]("string_defaulted_query", Some("the default value")).value
          stringArrayQuery <- params.fromQuery[List[String]]("string_array_query", None).value
          uuidQuery <- params.fromQuery[java.util.UUID]("uuid_query", None).value
          dateQuery <- params.fromQuery[java.time.LocalDate]("date_query", None).value
          dateArrayQuery <- params.fromQuery[List[java.time.LocalDate]]("date_array_query", None).value
          datetimeQuery <- params.fromQuery[java.time.LocalDateTime]("datetime_query", None).value
          enumQuery <- params.fromQuery[Choice]("enum_query", None).value
        }
        yield (intQuery, longQuery, floatQuery, doubleQuery, decimalQuery, boolQuery, stringQuery, stringOptQuery, stringDefaultedQuery, stringArrayQuery, uuidQuery, dateQuery, dateArrayQuery, datetimeQuery, enumQuery)
      arguments match{
        case Left(_) => Action { Results.BadRequest }
        case Right((intQuery, longQuery, floatQuery, doubleQuery, decimalQuery, boolQuery, stringQuery, stringOptQuery, stringDefaultedQuery, stringArrayQuery, uuidQuery, dateQuery, dateArrayQuery, datetimeQuery, enumQuery)) => controller.echoQuery(intQuery, longQuery, floatQuery, doubleQuery, decimalQuery, boolQuery, stringQuery, stringOptQuery, stringDefaultedQuery, stringArrayQuery, uuidQuery, dateQuery, dateArrayQuery, datetimeQuery, enumQuery)
      }
    case routeEchoHeader(params@_) =>
      controller.echoHeader()
    case routeEchoUrlParams(params@_) =>
      val arguments =
        for {
          intUrl <- params.fromPath[Int]("int_url").value
          longUrl <- params.fromPath[Long]("long_url").value
          floatUrl <- params.fromPath[Float]("float_url").value
          doubleUrl <- params.fromPath[Double]("double_url").value
          decimalUrl <- params.fromPath[BigDecimal]("decimal_url").value
          boolUrl <- params.fromPath[Boolean]("bool_url").value
          stringUrl <- params.fromPath[String]("string_url").value
          uuidUrl <- params.fromPath[java.util.UUID]("uuid_url").value
          dateUrl <- params.fromPath[java.time.LocalDate]("date_url").value
          datetimeUrl <- params.fromPath[java.time.LocalDateTime]("datetime_url").value
          enumUrl <- params.fromPath[Choice]("enum_url").value
        }
        yield (intUrl, longUrl, floatUrl, doubleUrl, decimalUrl, boolUrl, stringUrl, uuidUrl, dateUrl, datetimeUrl, enumUrl)
      arguments match{
        case Left(_) => Action { Results.BadRequest }
        case Right((intUrl, longUrl, floatUrl, doubleUrl, decimalUrl, boolUrl, stringUrl, uuidUrl, dateUrl, datetimeUrl, enumUrl)) => controller.echoUrlParams(intUrl, longUrl, floatUrl, doubleUrl, decimalUrl, boolUrl, stringUrl, uuidUrl, dateUrl, datetimeUrl, enumUrl)
      }
    case routeEchoEverything(params@_) =>
      val arguments =
        for {
          dateUrl <- params.fromPath[java.time.LocalDate]("date_url").value
          decimalUrl <- params.fromPath[BigDecimal]("decimal_url").value
          floatQuery <- params.fromQuery[Float]("float_query", None).value
          boolQuery <- params.fromQuery[Boolean]("bool_query", None).value
        }
        yield (dateUrl, decimalUrl, floatQuery, boolQuery)
      arguments match{
        case Left(_) => Action { Results.BadRequest }
        case Right((dateUrl, decimalUrl, floatQuery, boolQuery)) => controller.echoEverything(dateUrl, decimalUrl, floatQuery, boolQuery)
      }
    case routeSameOperationName(params@_) =>
      controller.sameOperationName()
  }
}

class CheckRouter @Inject()(Action: DefaultActionBuilder, controller: CheckController) extends SimpleRouter {
  lazy val routeCheckEmpty = Route("GET", PathPattern(List(
    StaticPart("/check/empty"),
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
    case routeCheckForbidden(params@_) =>
      controller.checkForbidden()
    case routeSameOperationName(params@_) =>
      controller.sameOperationName()
  }
}
