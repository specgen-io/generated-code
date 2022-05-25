package v2

import (
	"encoding/json"
	"fmt"
	"github.com/husobee/vestigo"
	log "github.com/sirupsen/logrus"
	"net/http"
	"test-service/generated/v2/echo"
	"test-service/generated/v2/models"
)

func AddEchoRoutes(router *vestigo.Router, echoService echo.Service) {
	logEchoBodyModel := log.Fields{"operationId": "echo.echo_body_model", "method": "POST", "url": "/v2/echo/body_model"}
	router.Post("/v2/echo/body_model", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logEchoBodyModel).Info("Received request")
		var err error
		if !CheckContentType(logEchoBodyModel, res, req, "application/json") {
			return
		}
		var body models.Message
		err = json.NewDecoder(req.Body).Decode(&body)
		if err != nil {
			error := models.BadRequestError{Message: fmt.Sprintf("Decoding body JSON failed: %s", err.Error()), Params: nil}
			BadRequest(logEchoBodyModel, res, &error)
			return
		}
		response, err := echoService.EchoBodyModel(&body)
		if err != nil {
			error := models.InternalServerError{Message: fmt.Sprintf("Error returned from service implementation: %s", err.Error())}
			InternalServerError(logEchoBodyModel, res, &error)
			return
		}
		if response == nil {
			error := models.InternalServerError{Message: "Service implementation returned nil"}
			InternalServerError(logEchoBodyModel, res, &error)
			return
		}
		res.Header().Set("Content-Type", "application/json")
		res.WriteHeader(200)
		json.NewEncoder(res).Encode(response)
		log.WithFields(logEchoBodyModel).WithField("status", 200).Info("Completed request")
	})
}
