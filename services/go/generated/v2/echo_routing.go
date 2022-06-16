package v2

import (
	"encoding/json"
	"fmt"
	"github.com/husobee/vestigo"
	log "github.com/sirupsen/logrus"
	"net/http"
	"strings"
	"test-service/generated/v2/echo"
	"test-service/generated/v2/models"
)

func AddEchoRoutes(router *vestigo.Router, echoService echo.Service) {
	respondBadRequest := func(logFields log.Fields, res http.ResponseWriter, error *models.BadRequestError) {
		log.WithFields(logFields).Warn(error.Message)
		respondJson(logFields, res, 400, error)
	}
	_ = respondBadRequest
	respondNotFound := func(logFields log.Fields, res http.ResponseWriter, error *models.NotFoundError) {
		log.WithFields(logFields).Warn(error.Message)
		respondJson(logFields, res, 404, error)
	}
	_ = respondNotFound

	respondInternalServerError := func(logFields log.Fields, res http.ResponseWriter, error *models.InternalServerError) {
		log.WithFields(logFields).Warn(error.Message)
		respondJson(logFields, res, 500, error)
	}
	_ = respondInternalServerError

	logEchoBodyModel := log.Fields{"operationId": "echo.echo_body_model", "method": "POST", "url": "/v2/echo/body_model"}
	router.Post("/v2/echo/body_model", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logEchoBodyModel).Info("Received request")
		var err error
		contentType := req.Header.Get("Content-Type")
		if !strings.Contains(contentType, "application/json") {
			respondBadRequest(logEchoBodyModel, res, &models.BadRequestError{Location: "header", Message: fmt.Sprintf("Wrong Content-type: %s", contentType), Errors: []models.ValidationError{}})
			return
		}
		var body models.Message
		err = json.NewDecoder(req.Body).Decode(&body)
		if err != nil {
			respondBadRequest(logEchoBodyModel, res, &models.BadRequestError{Location: "body", Message: "Failed to parse body", Errors: []models.ValidationError{}})
			return
		}
		response, err := echoService.EchoBodyModel(&body)
		if err != nil {
			respondInternalServerError(logEchoBodyModel, res, &models.InternalServerError{Message: fmt.Sprintf("Error returned from service implementation: %s", err.Error())})
			return
		}
		if response == nil {
			respondInternalServerError(logEchoBodyModel, res, &models.InternalServerError{Message: "Service implementation returned nil"})
			return
		}
		respondJson(logEchoBodyModel, res, 200, response)
	})
}
