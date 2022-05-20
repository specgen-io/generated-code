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
	logEchoBodyModel := log.Fields{"operationId": "echo.echo_body_model", "method": "POST", "url": "/v2/echo/body_model"}
	router.Post("/v2/echo/body_model", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logEchoBodyModel).Info("Received request")
		var err error
		contentType := req.Header.Get("Content-Type")
		if !strings.Contains(contentType, "application/json") {
			message := fmt.Sprintf("Wrong Content-type: %s", contentType)
			log.WithFields(logEchoBodyModel).Warn(message)
			errorResponse := models.BadRequestError{message, nil}
			res.Header().Set("Content-Type", "application/json")
			res.WriteHeader(400)
			json.NewEncoder(res).Encode(errorResponse)
			log.WithFields(logEchoBodyModel).WithField("status", 400).Info("Completed request")
			return
		}
		var body models.Message
		err = json.NewDecoder(req.Body).Decode(&body)
		if err != nil {
			message := fmt.Sprintf("Decoding body JSON failed: %s", err.Error())
			log.WithFields(logEchoBodyModel).Warn(message)
			errorResponse := models.BadRequestError{message, nil}
			res.Header().Set("Content-Type", "application/json")
			res.WriteHeader(400)
			json.NewEncoder(res).Encode(errorResponse)
			log.WithFields(logEchoBodyModel).WithField("status", 400).Info("Completed request")
			return
		}
		response, err := echoService.EchoBodyModel(&body)
		if err != nil {
			message := fmt.Sprintf("Error returned from service implementation: %s", err.Error())
			log.WithFields(logEchoBodyModel).Error(message)
			errorResponse := models.InternalServerError{message}
			res.Header().Set("Content-Type", "application/json")
			res.WriteHeader(500)
			json.NewEncoder(res).Encode(errorResponse)
			log.WithFields(logEchoBodyModel).WithField("status", 500).Info("Completed request")
			return
		}
		if response == nil {
			message := "Service implementation returned nil"
			log.WithFields(logEchoBodyModel).Error(message)
			errorResponse := models.InternalServerError{message}
			res.Header().Set("Content-Type", "application/json")
			res.WriteHeader(500)
			json.NewEncoder(res).Encode(errorResponse)
			log.WithFields(logEchoBodyModel).WithField("status", 500).Info("Completed request")
			return
		}
		res.Header().Set("Content-Type", "application/json")
		res.WriteHeader(200)
		json.NewEncoder(res).Encode(response)
		log.WithFields(logEchoBodyModel).WithField("status", 200).Info("Completed request")
		return
	})
}
