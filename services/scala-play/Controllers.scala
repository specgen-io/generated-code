package controllers

import javax.inject._
import scala.util._
import scala.concurrent._
import play.api.mvc._
import spec.controllers.ParamsTypesBindings._
import spec.models.Jsoner
import services._
import models._

@Singleton
class EchoController @Inject()(api: IEchoService, cc: ControllerComponents)(implicit ec: ExecutionContext) extends AbstractController(cc) {
  import IEchoService._
  def echoBody() = Action(parse.byteString).async {
    implicit request =>
      val params = Try {
        val body = Jsoner.readThrowing[Message](request.body.utf8String)
        (body)
      }
      params match {
        case Failure(ex) => Future { BadRequest }
        case Success(params) => 
          val (body) = params
          val result = api.echoBody(body)
          val response = result.map {
            case EchoBodyResponse.Ok(body) => new Status(200)(Jsoner.write(body))
          }
          response.recover { case _: Exception => InternalServerError }
      }
  }
  def echoQuery(int_query: Int, string_query: String) = Action.async {
    implicit request =>
      val result = api.echoQuery(int_query, string_query)
      val response = result.map {
        case EchoQueryResponse.Ok(body) => new Status(200)(Jsoner.write(body))
      }
      response.recover { case _: Exception => InternalServerError }
  }
  def echoHeader() = Action.async {
    implicit request =>
      val params = Try {
        val header = new StringParamsReader(request.headers.get)
        val intHeader = header.read[Int]("Int-Header").get
        val stringHeader = header.read[String]("String-Header").get
        (intHeader, stringHeader)
      }
      params match {
        case Failure(ex) => Future { BadRequest }
        case Success(params) => 
          val (intHeader, stringHeader) = params
          val result = api.echoHeader(intHeader, stringHeader)
          val response = result.map {
            case EchoHeaderResponse.Ok(body) => new Status(200)(Jsoner.write(body))
          }
          response.recover { case _: Exception => InternalServerError }
      }
  }
  def echoUrlParams(intUrl: Int, stringUrl: String) = Action.async {
    implicit request =>
      val result = api.echoUrlParams(intUrl, stringUrl)
      val response = result.map {
        case EchoUrlParamsResponse.Ok(body) => new Status(200)(Jsoner.write(body))
      }
      response.recover { case _: Exception => InternalServerError }
  }
}

@Singleton
class CheckController @Inject()(api: ICheckService, cc: ControllerComponents)(implicit ec: ExecutionContext) extends AbstractController(cc) {
  import ICheckService._
  def checkEmpty() = Action.async {
    implicit request =>
      val result = api.checkEmpty()
      val response = result.map {
        case CheckEmptyResponse.Ok() => new Status(200)
      }
      response.recover { case _: Exception => InternalServerError }
  }
  def checkQuery(p_string: String, p_string_opt: Option[String], p_string_array: List[String], p_date: java.time.LocalDate, p_date_array: List[java.time.LocalDate], p_datetime: java.time.LocalDateTime, p_int: Int, p_long: Long, p_decimal: BigDecimal, p_enum: Choice, p_string_defaulted: String) = Action.async {
    implicit request =>
      val result = api.checkQuery(p_string, p_string_opt, p_string_array, p_date, p_date_array, p_datetime, p_int, p_long, p_decimal, p_enum, p_string_defaulted)
      val response = result.map {
        case CheckQueryResponse.Ok() => new Status(200)
      }
      response.recover { case _: Exception => InternalServerError }
  }
  def checkUrlParams(intUrl: Long, stringUrl: String, floatUrl: Float, boolUrl: Boolean, uuidUrl: java.util.UUID, decimalUrl: BigDecimal, dateUrl: java.time.LocalDate) = Action.async {
    implicit request =>
      val result = api.checkUrlParams(intUrl, stringUrl, floatUrl, boolUrl, uuidUrl, decimalUrl, dateUrl)
      val response = result.map {
        case CheckUrlParamsResponse.Ok() => new Status(200)
      }
      response.recover { case _: Exception => InternalServerError }
  }
  def checkForbidden() = Action.async {
    implicit request =>
      val result = api.checkForbidden()
      val response = result.map {
        case CheckForbiddenResponse.Ok(body) => new Status(200)(Jsoner.write(body))
        case CheckForbiddenResponse.Forbidden() => new Status(403)
      }
      response.recover { case _: Exception => InternalServerError }
  }
}
