package controllers

import javax.inject._
import scala.util._
import scala.concurrent._
import play.api.mvc._
import params.ParamsTypesBindings._
import json.Jsoner
import services.echo._
import models._

@Singleton
class EchoController @Inject()(api: IEchoService, cc: ControllerComponents)(implicit ec: ExecutionContext) extends AbstractController(cc) {
  def echoBodyString() = Action(parse.byteString).async {
    implicit request =>
      val params = Try {
        val body = request.body.utf8String
        (body)
      }
      params match {
        case Failure(ex) => Future { BadRequest }
        case Success(params) => 
          val (body) = params
          val result = api.echoBodyString(body)
          val response = result.map {
            body => new Status(200)(body)
          }
          response.recover { case _: Exception => InternalServerError }
      }
  }
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
            body => new Status(200)(Jsoner.write(body))
          }
          response.recover { case _: Exception => InternalServerError }
      }
  }
  def echoQuery(int_query: Int, long_query: Long, float_query: Float, double_query: Double, decimal_query: BigDecimal, bool_query: Boolean, string_query: String, string_opt_query: Option[String], string_defaulted_query: String, string_array_query: List[String], uuid_query: java.util.UUID, date_query: java.time.LocalDate, date_array_query: List[java.time.LocalDate], datetime_query: java.time.LocalDateTime, enum_query: Choice) = Action.async {
    implicit request =>
      val result = api.echoQuery(int_query, long_query, float_query, double_query, decimal_query, bool_query, string_query, string_opt_query, string_defaulted_query, string_array_query, uuid_query, date_query, date_array_query, datetime_query, enum_query)
      val response = result.map {
        body => new Status(200)(Jsoner.write(body))
      }
      response.recover { case _: Exception => InternalServerError }
  }
  def echoHeader() = Action.async {
    implicit request =>
      val params = Try {
        val header = new StringParamsReader(request.headers.toMap)
        val intHeader = header.read[Int]("Int-Header").get
        val longHeader = header.read[Long]("Long-Header").get
        val floatHeader = header.read[Float]("Float-Header").get
        val doubleHeader = header.read[Double]("Double-Header").get
        val decimalHeader = header.read[BigDecimal]("Decimal-Header").get
        val boolHeader = header.read[Boolean]("Bool-Header").get
        val stringHeader = header.read[String]("String-Header").get
        val stringOptHeader = header.read[String]("String-Opt-Header")
        val stringDefaultedHeader = header.read[String]("String-Defaulted-Header").getOrElse("the default value")
        val stringArrayHeader = header.readList[String]("String-Array-Header").get
        val uuidHeader = header.read[java.util.UUID]("Uuid-Header").get
        val dateHeader = header.read[java.time.LocalDate]("Date-Header").get
        val dateArrayHeader = header.readList[java.time.LocalDate]("Date-Array-Header").get
        val datetimeHeader = header.read[java.time.LocalDateTime]("Datetime-Header").get
        val enumHeader = header.read[Choice]("Enum-Header").get
        (intHeader, longHeader, floatHeader, doubleHeader, decimalHeader, boolHeader, stringHeader, stringOptHeader, stringDefaultedHeader, stringArrayHeader, uuidHeader, dateHeader, dateArrayHeader, datetimeHeader, enumHeader)
      }
      params match {
        case Failure(ex) => Future { BadRequest }
        case Success(params) => 
          val (intHeader, longHeader, floatHeader, doubleHeader, decimalHeader, boolHeader, stringHeader, stringOptHeader, stringDefaultedHeader, stringArrayHeader, uuidHeader, dateHeader, dateArrayHeader, datetimeHeader, enumHeader) = params
          val result = api.echoHeader(intHeader, longHeader, floatHeader, doubleHeader, decimalHeader, boolHeader, stringHeader, stringOptHeader, stringDefaultedHeader, stringArrayHeader, uuidHeader, dateHeader, dateArrayHeader, datetimeHeader, enumHeader)
          val response = result.map {
            body => new Status(200)(Jsoner.write(body))
          }
          response.recover { case _: Exception => InternalServerError }
      }
  }
  def echoUrlParams(intUrl: Int, longUrl: Long, floatUrl: Float, doubleUrl: Double, decimalUrl: BigDecimal, boolUrl: Boolean, stringUrl: String, uuidUrl: java.util.UUID, dateUrl: java.time.LocalDate, datetimeUrl: java.time.LocalDateTime, enumUrl: Choice) = Action.async {
    implicit request =>
      val result = api.echoUrlParams(intUrl, longUrl, floatUrl, doubleUrl, decimalUrl, boolUrl, stringUrl, uuidUrl, dateUrl, datetimeUrl, enumUrl)
      val response = result.map {
        body => new Status(200)(Jsoner.write(body))
      }
      response.recover { case _: Exception => InternalServerError }
  }
  def echoEverything(dateUrl: java.time.LocalDate, decimalUrl: BigDecimal, float_query: Float, bool_query: Boolean) = Action(parse.byteString).async {
    implicit request =>
      val params = Try {
        val header = new StringParamsReader(request.headers.toMap)
        val uuidHeader = header.read[java.util.UUID]("Uuid-Header").get
        val datetimeHeader = header.read[java.time.LocalDateTime]("Datetime-Header").get
        val body = Jsoner.readThrowing[Message](request.body.utf8String)
        (uuidHeader, datetimeHeader, body)
      }
      params match {
        case Failure(ex) => Future { BadRequest }
        case Success(params) => 
          val (uuidHeader, datetimeHeader, body) = params
          val result = api.echoEverything(uuidHeader, datetimeHeader, body, dateUrl, decimalUrl, float_query, bool_query)
          val response = result.map {
            case EchoEverythingResponse.Ok(body) => new Status(200)(Jsoner.write(body))
            case EchoEverythingResponse.Forbidden() => new Status(403)
          }
          response.recover { case _: Exception => InternalServerError }
      }
  }
  def sameOperationName() = Action.async {
    implicit request =>
      val result = api.sameOperationName()
      val response = result.map {
        case SameOperationNameResponse.Ok() => new Status(200)
        case SameOperationNameResponse.Forbidden() => new Status(403)
      }
      response.recover { case _: Exception => InternalServerError }
  }
}
