package v2

import (
	"encoding/json"
	"github.com/husobee/vestigo"
	log "github.com/sirupsen/logrus"
	"net/http"
	"test-service/v2/echo"
	"test-service/v2/models"
)

func AddEchoRoutes(router *vestigo.Router, echoService echo.Service) {
	logEchoBody := log.Fields{"operationId": "echo.echo_body", "method": "POST", "url": "/v2/echo/body"}
	router.Post("/v2/echo/body", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logEchoBody).Info("Received request")
		var body models.Message
		err := json.NewDecoder(req.Body).Decode(&body)
		if err != nil {
			log.WithFields(logEchoBody).Warnf("Decoding body JSON failed: %s", err.Error())
			res.WriteHeader(400)
			log.WithFields(logEchoBody).WithField("status", 400).Info("Completed request")
			return
		}
		response, err := echoService.EchoBody(&body)
		if err != nil {
			log.WithFields(logEchoBody).Errorf("Error returned from service implementation: %s", err.Error())
			res.WriteHeader(500)
			log.WithFields(logEchoBody).WithField("status", 500).Info("Completed request")
			return
		}
		if response == nil {
			log.WithFields(logEchoBody).Errorf("No result returned from service implementation")
			res.WriteHeader(500)
			log.WithFields(logEchoBody).WithField("status", 500).Info("Completed request")
			return
		}
		res.WriteHeader(200)
		json.NewEncoder(res).Encode(response)
		log.WithFields(logEchoBody).WithField("status", 200).Info("Completed request")
		return
	})
}
