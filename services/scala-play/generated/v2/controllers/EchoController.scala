package v2.controllers

import javax.inject._
import scala.util._
import scala.concurrent._
import play.api.mvc._
import params.ParamsTypesBindings._
import json.Jsoner
import v2.services.echo._
import v2.models._

@Singleton
class EchoController @Inject()(api: IEchoService, cc: ControllerComponents)(implicit ec: ExecutionContext) extends AbstractController(cc) {
  def echoBodyModel() = Action(parse.byteString).async {
    implicit request =>
      val params = Try {
        val body = Jsoner.readThrowing[Message](request.body.utf8String)
        (body)
      }
      params match {
        case Failure(ex) => Future { BadRequest }
        case Success(params) =>
          val (body) = params
          val result = api.echoBodyModel(body)
          val response = result.map {
            body => new Status(200)(Jsoner.write(body)).as("application/json")
          }
          response.recover { case _: Exception => InternalServerError }
      }
  }
}
