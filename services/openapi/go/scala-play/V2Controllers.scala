package controllers.v2

import javax.inject._
import scala.util._
import scala.concurrent._
import play.api.mvc._
import spec.controllers.ParamsTypesBindings._
import spec.models.Jsoner
import services.v2._
import models.v2._

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
}
