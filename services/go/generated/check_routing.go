package generated

import (
	"encoding/json"
	"github.com/husobee/vestigo"
	log "github.com/sirupsen/logrus"
	"net/http"
	"test-service/generated/check"
)

func AddCheckRoutes(router *vestigo.Router, checkService check.Service) {
	logCheckEmpty := log.Fields{"operationId": "check.check_empty", "method": "GET", "url": "/check/empty"}
	router.Get("/check/empty", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logCheckEmpty).Info("Received request")
		var err error
		err = checkService.CheckEmpty()
		if err != nil {
			log.WithFields(logCheckEmpty).Errorf("Error returned from service implementation: %s", err.Error())
			res.WriteHeader(500)
			log.WithFields(logCheckEmpty).WithField("status", 500).Info("Completed request")
			return
		}
		res.WriteHeader(200)
		log.WithFields(logCheckEmpty).WithField("status", 200).Info("Completed request")
		return
	})

	logCheckForbidden := log.Fields{"operationId": "check.check_forbidden", "method": "GET", "url": "/check/forbidden"}
	router.Get("/check/forbidden", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logCheckForbidden).Info("Received request")
		var err error
		response, err := checkService.CheckForbidden()
		if err != nil {
			log.WithFields(logCheckForbidden).Errorf("Error returned from service implementation: %s", err.Error())
			res.WriteHeader(500)
			log.WithFields(logCheckForbidden).WithField("status", 500).Info("Completed request")
			return
		}
		if response == nil {
			log.WithFields(logCheckForbidden).Errorf("No result returned from service implementation")
			res.WriteHeader(500)
			log.WithFields(logCheckForbidden).WithField("status", 500).Info("Completed request")
			return
		}
		if response.Ok != nil {
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
		log.WithFields(logCheckForbidden).Error("Result from service implementation does not have anything in it")
		res.WriteHeader(500)
		log.WithFields(logCheckForbidden).WithField("status", 500).Info("Completed request")
		return
	})

	logSameOperationName := log.Fields{"operationId": "check.same_operation_name", "method": "GET", "url": "/check/same_operation_name"}
	router.Get("/check/same_operation_name", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logSameOperationName).Info("Received request")
		var err error
		response, err := checkService.SameOperationName()
		if err != nil {
			log.WithFields(logSameOperationName).Errorf("Error returned from service implementation: %s", err.Error())
			res.WriteHeader(500)
			log.WithFields(logSameOperationName).WithField("status", 500).Info("Completed request")
			return
		}
		if response == nil {
			log.WithFields(logSameOperationName).Errorf("No result returned from service implementation")
			res.WriteHeader(500)
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
		log.WithFields(logSameOperationName).Error("Result from service implementation does not have anything in it")
		res.WriteHeader(500)
		log.WithFields(logSameOperationName).WithField("status", 500).Info("Completed request")
		return
	})
}
