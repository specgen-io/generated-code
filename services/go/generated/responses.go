package generated

import (
	"encoding/json"
	log "github.com/sirupsen/logrus"
	"net/http"
)

func respondJson(logFields log.Fields, res http.ResponseWriter, statusCode int, data interface{}) {
	res.Header().Set("Content-Type", "application/json")
	res.WriteHeader(statusCode)
	json.NewEncoder(res).Encode(data)
	log.WithFields(logFields).WithField("status", statusCode).Info("Completed request")
}

func respondText(logFields log.Fields, res http.ResponseWriter, statusCode int, data string) {
	res.Header().Set("Content-Type", "text/plain")
	res.WriteHeader(statusCode)
	res.Write([]byte(data))
	log.WithFields(logFields).WithField("status", statusCode).Info("Completed request")
}

func respondEmpty(logFields log.Fields, res http.ResponseWriter, statusCode int) {
	res.WriteHeader(statusCode)
	log.WithFields(logFields).WithField("status", statusCode).Info("Completed request")
}