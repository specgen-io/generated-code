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

	checkContentType := func(logFields log.Fields, expectedContentType string, req *http.Request, res http.ResponseWriter) bool {
		contentType := req.Header.Get("Content-Type")
		if !strings.Contains(contentType, expectedContentType) {
			message := fmt.Sprintf("Expected Content-Type header: '%!!(MISSING)s(MISSING)' was not provided, found: '%!!(MISSING)s(MISSING)'", expectedContentType, contentType)
			respondBadRequest(logFields, res, &models.BadRequestError{Location: "header", Message: "Failed to parse header", Errors: []models.ValidationError{{Path: "Content-Type", Code: "missing", Message: &message}}})
			return false
		}
		return true
	}
	_ = checkContentType

	logEchoBodyModel := log.Fields{"operationId": "echo.echo_body_model", "method": "POST", "url": "/v2/echo/body_model"}
	router.Post("/v2/echo/body_model", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logEchoBodyModel).Info("Received request")
		var err error
		if !checkContentType(logEchoBodyModel, "application/json", req, res) {
			return
		}
		var body models.Message
		err = json.NewDecoder(req.Body).Decode(&body)
		if err != nil {
			var errors []models.ValidationError = nil
			if unmarshalError, ok := err.(*json.UnmarshalTypeError); ok {
				message := fmt.Sprintf("Failed to parse JSON, field: %s", unmarshalError.Field)
				errors = []models.ValidationError{{Path: unmarshalError.Field, Code: "parsing_failed", Message: &message}}
			}
			respondBadRequest(logEchoBodyModel, res, &models.BadRequestError{Location: "body", Message: "Failed to parse body", Errors: errors})
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
