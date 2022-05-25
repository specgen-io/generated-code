package generated

import (
	"encoding/json"
	"fmt"
	"github.com/husobee/vestigo"
	log "github.com/sirupsen/logrus"
	"net/http"
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
			error := models.InternalServerError{Message: fmt.Sprintf("Error returned from service implementation: %s", err.Error())}
			InternalServerError(logCheckEmpty, res, &error)
			return
		}
		res.WriteHeader(200)
		log.WithFields(logCheckEmpty).WithField("status", 200).Info("Completed request")
	})

	logCheckEmptyResponse := log.Fields{"operationId": "check.check_empty_response", "method": "POST", "url": "/check/empty_response"}
	router.Post("/check/empty_response", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logCheckEmptyResponse).Info("Received request")
		var err error
		if !CheckContentType(logCheckEmptyResponse, res, req, "application/json") {
			return
		}
		var body models.Message
		err = json.NewDecoder(req.Body).Decode(&body)
		if err != nil {
			error := models.BadRequestError{Message: fmt.Sprintf("Decoding body JSON failed: %s", err.Error()), Params: nil}
			BadRequest(logCheckEmptyResponse, res, &error)
			return
		}
		err = checkService.CheckEmptyResponse(&body)
		if err != nil {
			error := models.InternalServerError{Message: fmt.Sprintf("Error returned from service implementation: %s", err.Error())}
			InternalServerError(logCheckEmptyResponse, res, &error)
			return
		}
		res.WriteHeader(200)
		log.WithFields(logCheckEmptyResponse).WithField("status", 200).Info("Completed request")
	})

	logCheckForbidden := log.Fields{"operationId": "check.check_forbidden", "method": "GET", "url": "/check/forbidden"}
	router.Get("/check/forbidden", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logCheckForbidden).Info("Received request")
		var err error
		response, err := checkService.CheckForbidden()
		if err != nil {
			error := models.InternalServerError{Message: fmt.Sprintf("Error returned from service implementation: %s", err.Error())}
			InternalServerError(logCheckForbidden, res, &error)
			return
		}
		if response == nil {
			error := models.InternalServerError{Message: "Service implementation returned nil"}
			InternalServerError(logCheckForbidden, res, &error)
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
		error := models.InternalServerError{Message: "Result from service implementation does not have anything in it"}
		InternalServerError(logCheckForbidden, res, &error)
		return
	})

	logSameOperationName := log.Fields{"operationId": "check.same_operation_name", "method": "GET", "url": "/check/same_operation_name"}
	router.Get("/check/same_operation_name", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logSameOperationName).Info("Received request")
		var err error
		response, err := checkService.SameOperationName()
		if err != nil {
			error := models.InternalServerError{Message: fmt.Sprintf("Error returned from service implementation: %s", err.Error())}
			InternalServerError(logSameOperationName, res, &error)
			return
		}
		if response == nil {
			error := models.InternalServerError{Message: "Service implementation returned nil"}
			InternalServerError(logSameOperationName, res, &error)
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
		error := models.InternalServerError{Message: "Result from service implementation does not have anything in it"}
		InternalServerError(logSameOperationName, res, &error)
		return
	})
}
