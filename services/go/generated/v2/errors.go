package v2

import (
	"encoding/json"
	"fmt"
	log "github.com/sirupsen/logrus"
	"net/http"
	"strings"
	"test-service/generated/v2/models"
)

func BadRequest(logFields log.Fields, res http.ResponseWriter, error *models.BadRequestError) {
	log.WithFields(logFields).Warn(error.Message)
	res.Header().Set("Content-Type", "application/json")
	res.WriteHeader(400)
	json.NewEncoder(res).Encode(error)
	log.WithFields(logFields).WithField("status", 400).Info("Completed request")
}

func InternalServerError(logFields log.Fields, res http.ResponseWriter, error *models.InternalServerError) {
	log.WithFields(logFields).Warn(error.Message)
	res.Header().Set("Content-Type", "application/json")
	res.WriteHeader(500)
	json.NewEncoder(res).Encode(error)
	log.WithFields(logFields).WithField("status", 500).Info("Completed request")
}

func CheckContentType(logFields log.Fields, res http.ResponseWriter, req *http.Request, expectedContentType string) bool {
	contentType := req.Header.Get("Content-Type")
	if !strings.Contains(contentType, expectedContentType) {
		error := models.BadRequestError{fmt.Sprintf("Wrong Content-type: %s", contentType), nil}
		BadRequest(logFields, res, &error)
		return false
	}
	return true
}
