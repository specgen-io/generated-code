package controllers

import javax.inject._
import scala.util._
import scala.concurrent._
import play.api.mvc._
import params.ParamsTypesBindings._
import json.Jsoner
import services.check._
import models._

@Singleton
class CheckController @Inject()(api: ICheckService, cc: ControllerComponents)(implicit ec: ExecutionContext) extends AbstractController(cc) {
  def checkEmpty() = Action.async {
    implicit request =>
      val result = api.checkEmpty()
      val response = result.map {
        _ => new Status(200)
      }
      response.recover { case _: Exception => InternalServerError }
  }
  def checkEmptyResponse() = Action(parse.byteString).async {
    implicit request =>
      val params = Try {
        val body = Jsoner.readThrowing[Message](request.body.utf8String)
        (body)
      }
      params match {
        case Failure(ex) => Future { BadRequest }
        case Success(params) =>
          val (body) = params
          val result = api.checkEmptyResponse(body)
          val response = result.map {
            _ => new Status(200)
          }
          response.recover { case _: Exception => InternalServerError }
      }
  }
  def checkForbidden() = Action.async {
    implicit request =>
      val result = api.checkForbidden()
      val response = result.map {
        case CheckForbiddenResponse.Ok(body) => new Status(200)(Jsoner.write(body)).as("application/json")
        case CheckForbiddenResponse.Forbidden() => new Status(403)
      }
      response.recover { case _: Exception => InternalServerError }
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
