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
	respondBadRequest := func(logFields log.Fields, res http.ResponseWriter, error *models.BadRequestError) {
		log.WithFields(logFields).Warn(error.Message)
		respondJson(logFields, res, 400, error)
	}
	_ = respondBadRequest

	respondInternalServerError := func(logFields log.Fields, res http.ResponseWriter, error *models.InternalServerError) {
		log.WithFields(logFields).Warn(error.Message)
		respondJson(logFields, res, 500, error)
	}
	_ = respondInternalServerError

	logCheckEmpty := log.Fields{"operationId": "check.check_empty", "method": "GET", "url": "/check/empty"}
	router.Get("/check/empty", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logCheckEmpty).Info("Received request")
		var err error
		err = checkService.CheckEmpty()
		if err != nil {
			respondInternalServerError(logCheckEmpty, res, &models.InternalServerError{Message: fmt.Sprintf("Error returned from service implementation: %s", err.Error())})
			return
		}
		respondEmpty(logCheckEmpty, res, 200)
	})

	logCheckEmptyResponse := log.Fields{"operationId": "check.check_empty_response", "method": "POST", "url": "/check/empty_response"}
	router.Post("/check/empty_response", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logCheckEmptyResponse).Info("Received request")
		var err error
		contentType := req.Header.Get("Content-Type")
		if !strings.Contains(contentType, "application/json") {
			respondBadRequest(logCheckEmptyResponse, res, &models.BadRequestError{Message: fmt.Sprintf("Wrong Content-type: %s", contentType), Params: nil})
			return
		}
		var body models.Message
		err = json.NewDecoder(req.Body).Decode(&body)
		if err != nil {
			respondBadRequest(logCheckEmptyResponse, res, &models.BadRequestError{Message: fmt.Sprintf("Decoding body JSON failed: %s", err.Error()), Params: nil})
			return
		}
		err = checkService.CheckEmptyResponse(&body)
		if err != nil {
			respondInternalServerError(logCheckEmptyResponse, res, &models.InternalServerError{Message: fmt.Sprintf("Error returned from service implementation: %s", err.Error())})
			return
		}
		respondEmpty(logCheckEmptyResponse, res, 200)
	})

	logCheckForbidden := log.Fields{"operationId": "check.check_forbidden", "method": "GET", "url": "/check/forbidden"}
	router.Get("/check/forbidden", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logCheckForbidden).Info("Received request")
		var err error
		response, err := checkService.CheckForbidden()
		if err != nil {
			respondInternalServerError(logCheckForbidden, res, &models.InternalServerError{Message: fmt.Sprintf("Error returned from service implementation: %s", err.Error())})
			return
		}
		if response == nil {
			respondInternalServerError(logCheckForbidden, res, &models.InternalServerError{Message: "Service implementation returned nil"})
			return
		}
		if response.Ok != nil {
			respondJson(logCheckForbidden, res, 200, response.Ok)
			return
		}
		if response.Forbidden != nil {
			respondEmpty(logCheckForbidden, res, 403)
			return
		}
		respondInternalServerError(logCheckForbidden, res, &models.InternalServerError{Message: "Result from service implementation does not have anything in it"})
		return
	})

	logSameOperationName := log.Fields{"operationId": "check.same_operation_name", "method": "GET", "url": "/check/same_operation_name"}
	router.Get("/check/same_operation_name", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logSameOperationName).Info("Received request")
		var err error
		response, err := checkService.SameOperationName()
		if err != nil {
			respondInternalServerError(logSameOperationName, res, &models.InternalServerError{Message: fmt.Sprintf("Error returned from service implementation: %s", err.Error())})
			return
		}
		if response == nil {
			respondInternalServerError(logSameOperationName, res, &models.InternalServerError{Message: "Service implementation returned nil"})
			return
		}
		if response.Ok != nil {
			respondEmpty(logSameOperationName, res, 200)
			return
		}
		if response.Forbidden != nil {
			respondEmpty(logSameOperationName, res, 403)
			return
		}
		respondInternalServerError(logSameOperationName, res, &models.InternalServerError{Message: "Result from service implementation does not have anything in it"})
		return
	})
}
