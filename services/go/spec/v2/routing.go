package v2

import (
	"encoding/json"
	"github.com/husobee/vestigo"
	log "github.com/sirupsen/logrus"
	"net/http"
)

func AddEchoRoutes(router *vestigo.Router, echoService IEchoService) {

	logEchoBody := log.Fields{"operationId": "echo.echo_body", "method": "POST", "url": "/v2/echo/body"}
	router.Post("/v2/echo/body", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logEchoBody).Info("Received request")
		var body Message
		err := json.NewDecoder(req.Body).Decode(&body)
		if err != nil {
			log.Warnf("Decoding body JSON failed: %s", err.Error())
			res.WriteHeader(400)
			log.WithFields(logEchoBody).WithField("status", 400).Info("Completed request")
			return
		}
		response, err := echoService.EchoBody(&body)
		if response == nil || err != nil {
			if err != nil {
				log.Errorf("Error returned from service implementation: %s", err.Error())
			} else {
				log.Errorf("No result returned from service implementation")
			}
			res.WriteHeader(500)
			log.WithFields(logEchoBody).WithField("status", 500).Info("Completed request")
			return
		}
		if response.Ok != nil {
			res.WriteHeader(200)
			json.NewEncoder(res).Encode(response.Ok)
			log.WithFields(logEchoBody).WithField("status", 200).Info("Completed request")
			return
		}
		log.Error("Result from service implementation does not have anything in it")
		res.WriteHeader(500)
		log.WithFields(logEchoBody).WithField("status", 500).Info("Completed request")
		return
	})
}
