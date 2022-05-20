package generated

import (
	"encoding/json"
	"fmt"
	"github.com/husobee/vestigo"
	log "github.com/sirupsen/logrus"
	"net/http"
	"strings"
	"test-service/generated/check"
	"test-service/generated/models"
)

func AddCheckRoutes(router *vestigo.Router, checkService check.Service) {
	logCheckEmpty := log.Fields{"operationId": "check.check_empty", "method": "GET", "url": "/check/empty"}
	router.Get("/check/empty", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logCheckEmpty).Info("Received request")
		var err error
		err = checkService.CheckEmpty()
		if err != nil {
			message := fmt.Sprintf("Error returned from service implementation: %s", err.Error())
			log.WithFields(logCheckEmpty).Error(message)
			errorResponse := models.InternalServerError{message}
			res.Header().Set("Content-Type", "application/json")
			res.WriteHeader(500)
			json.NewEncoder(res).Encode(errorResponse)
			log.WithFields(logCheckEmpty).WithField("status", 500).Info("Completed request")
			return
		}
		res.WriteHeader(200)
		log.WithFields(logCheckEmpty).WithField("status", 200).Info("Completed request")
		return
	})

	logCheckEmptyResponse := log.Fields{"operationId": "check.check_empty_response", "method": "POST", "url": "/check/empty_response"}
	router.Post("/check/empty_response", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logCheckEmptyResponse).Info("Received request")
		var err error
		contentType := req.Header.Get("Content-Type")
		if !strings.Contains(contentType, "application/json") {
			message := fmt.Sprintf("Wrong Content-type: %s", contentType)
			log.WithFields(logCheckEmptyResponse).Warn(message)
			errorResponse := models.BadRequestError{message, nil}
			res.Header().Set("Content-Type", "application/json")
			res.WriteHeader(400)
			json.NewEncoder(res).Encode(errorResponse)
			log.WithFields(logCheckEmptyResponse).WithField("status", 400).Info("Completed request")
			return
		}
		var body models.Message
		err = json.NewDecoder(req.Body).Decode(&body)
		if err != nil {
			message := fmt.Sprintf("Decoding body JSON failed: %s", err.Error())
			log.WithFields(logCheckEmptyResponse).Warn(message)
			errorResponse := models.BadRequestError{message, nil}
			res.Header().Set("Content-Type", "application/json")
			res.WriteHeader(400)
			json.NewEncoder(res).Encode(errorResponse)
			log.WithFields(logCheckEmptyResponse).WithField("status", 400).Info("Completed request")
			return
		}
		err = checkService.CheckEmptyResponse(&body)
		if err != nil {
			message := fmt.Sprintf("Error returned from service implementation: %s", err.Error())
			log.WithFields(logCheckEmptyResponse).Error(message)
			errorResponse := models.InternalServerError{message}
			res.Header().Set("Content-Type", "application/json")
			res.WriteHeader(500)
			json.NewEncoder(res).Encode(errorResponse)
			log.WithFields(logCheckEmptyResponse).WithField("status", 500).Info("Completed request")
			return
		}
		res.WriteHeader(200)
		log.WithFields(logCheckEmptyResponse).WithField("status", 200).Info("Completed request")
		return
	})

	logCheckForbidden := log.Fields{"operationId": "check.check_forbidden", "method": "GET", "url": "/check/forbidden"}
	router.Get("/check/forbidden", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logCheckForbidden).Info("Received request")
		var err error
		response, err := checkService.CheckForbidden()
		if err != nil {
			message := fmt.Sprintf("Error returned from service implementation: %s", err.Error())
			log.WithFields(logCheckForbidden).Error(message)
			errorResponse := models.InternalServerError{message}
			res.Header().Set("Content-Type", "application/json")
			res.WriteHeader(500)
			json.NewEncoder(res).Encode(errorResponse)
			log.WithFields(logCheckForbidden).WithField("status", 500).Info("Completed request")
			return
		}
		if response == nil {
			message := "Service implementation returned nil"
			log.WithFields(logCheckForbidden).Error(message)
			errorResponse := models.InternalServerError{message}
			res.Header().Set("Content-Type", "application/json")
			res.WriteHeader(500)
			json.NewEncoder(res).Encode(errorResponse)
			log.WithFields(logCheckForbidden).WithField("status", 500).Info("Completed request")
			return
		}
		if response.Ok != nil {
			res.Header().Set("Content-Type", "application/json")
			res.WriteHeader(200)
			json.NewEncoder(res).Encode(response.Ok)
			log.WithFields(logCheckForbidden).WithField("status", 200).Info("Completed request")
			return
		}
		if response.Forbidden != nil {
			res.WriteHeader(403)
			log.WithFields(logCheckForbidden).WithField("status", 403).Info("Completed request")
			return
		}
		message := "Result from service implementation does not have anything in it"
		log.WithFields(logCheckForbidden).Error(message)
		errorResponse := models.InternalServerError{message}
		res.Header().Set("Content-Type", "application/json")
		res.WriteHeader(500)
		json.NewEncoder(res).Encode(errorResponse)
		log.WithFields(logCheckForbidden).WithField("status", 500).Info("Completed request")
		return
	})

	logSameOperationName := log.Fields{"operationId": "check.same_operation_name", "method": "GET", "url": "/check/same_operation_name"}
	router.Get("/check/same_operation_name", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logSameOperationName).Info("Received request")
		var err error
		response, err := checkService.SameOperationName()
		if err != nil {
			message := fmt.Sprintf("Error returned from service implementation: %s", err.Error())
			log.WithFields(logSameOperationName).Error(message)
			errorResponse := models.InternalServerError{message}
			res.Header().Set("Content-Type", "application/json")
			res.WriteHeader(500)
			json.NewEncoder(res).Encode(errorResponse)
			log.WithFields(logSameOperationName).WithField("status", 500).Info("Completed request")
			return
		}
		if response == nil {
			message := "Service implementation returned nil"
			log.WithFields(logSameOperationName).Error(message)
			errorResponse := models.InternalServerError{message}
			res.Header().Set("Content-Type", "application/json")
			res.WriteHeader(500)
			json.NewEncoder(res).Encode(errorResponse)
			log.WithFields(logSameOperationName).WithField("status", 500).Info("Completed request")
			return
		}
		if response.Ok != nil {
			res.WriteHeader(200)
			log.WithFields(logSameOperationName).WithField("status", 200).Info("Completed request")
			return
		}
		if response.Forbidden != nil {
			res.WriteHeader(403)
			log.WithFields(logSameOperationName).WithField("status", 403).Info("Completed request")
			return
		}
		message := "Result from service implementation does not have anything in it"
		log.WithFields(logSameOperationName).Error(message)
		errorResponse := models.InternalServerError{message}
		res.Header().Set("Content-Type", "application/json")
		res.WriteHeader(500)
		json.NewEncoder(res).Encode(errorResponse)
		log.WithFields(logSameOperationName).WithField("status", 500).Info("Completed request")
		return
	})
}
