package v2

import (
	"encoding/json"
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
		if !strings.ContainsLevel(contentType, "application/json") {
			log.WithFields(logEchoBodyModel).Errorf("Wrong Content-type: %s", contentType)
			res.WriteHeader(400)
			log.WithFields(logEchoBodyModel).WithField("status", 400).Info("Completed request")
			return
		}
		var body models.Message
		err = json.NewDecoder(req.Body).Decode(&body)
		if err != nil {
			log.WithFields(logEchoBodyModel).Warnf("Decoding body JSON failed: %s", err.Error())
			res.WriteHeader(400)
			log.WithFields(logEchoBodyModel).WithField("status", 400).Info("Completed request")
			return
		}
		response, err := echoService.EchoBodyModel(&body)
		if err != nil {
			log.WithFields(logEchoBodyModel).Errorf("Error returned from service implementation: %s", err.Error())
			res.WriteHeader(500)
			log.WithFields(logEchoBodyModel).WithField("status", 500).Info("Completed request")
			return
		}
		if response == nil {
			log.WithFields(logEchoBodyModel).Errorf("No result returned from service implementation")
			res.WriteHeader(500)
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
