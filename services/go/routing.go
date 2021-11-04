package .

import (
	"encoding/json"
	"github.com/husobee/vestigo"
	log "github.com/sirupsen/logrus"
	"net/http"
	"test-service/check"
	"test-service/echo"
	"test-service/models"
)

func AddEchoRoutes(router *vestigo.Router, echoService echo.Service) {
	logEchoBody := log.Fields{"operationId": "echo.echo_body", "method": "POST", "url": "/echo/body"}
	router.Post("/echo/body", func(res http.ResponseWriter, req *http.Request) {
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

	logEchoQuery := log.Fields{"operationId": "echo.echo_query", "method": "GET", "url": "/echo/query"}
	router.Get("/echo/query", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logEchoQuery).Info("Received request")
		queryParams := NewParamsParser(req.URL.Query())
		intQuery := queryParams.Int("int_query")
		stringQuery := queryParams.String("string_query")
		if len(queryParams.Errors) > 0 {
			log.WithFields(logEchoQuery).Warnf("Can't parse queryParams: %s", queryParams.Errors)
			res.WriteHeader(400)
			log.WithFields(logEchoQuery).WithField("status", 400).Info("Completed request")
			return
		}
		response, err := echoService.EchoQuery(intQuery, stringQuery)
		if err != nil {
			log.WithFields(logEchoQuery).Errorf("Error returned from service implementation: %s", err.Error())
			res.WriteHeader(500)
			log.WithFields(logEchoQuery).WithField("status", 500).Info("Completed request")
			return
		}
		if response == nil {
			log.WithFields(logEchoQuery).Errorf("No result returned from service implementation")
			res.WriteHeader(500)
			log.WithFields(logEchoQuery).WithField("status", 500).Info("Completed request")
			return
		}
		res.WriteHeader(200)
		json.NewEncoder(res).Encode(response)
		log.WithFields(logEchoQuery).WithField("status", 200).Info("Completed request")
		return
	})

	logEchoHeader := log.Fields{"operationId": "echo.echo_header", "method": "GET", "url": "/echo/header"}
	router.Get("/echo/header", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logEchoHeader).Info("Received request")
		headerParams := NewParamsParser(req.Header)
		intHeader := headerParams.Int("Int-Header")
		stringHeader := headerParams.String("String-Header")
		if len(headerParams.Errors) > 0 {
			log.WithFields(logEchoHeader).Warnf("Can't parse headerParams: %s", headerParams.Errors)
			res.WriteHeader(400)
			log.WithFields(logEchoHeader).WithField("status", 400).Info("Completed request")
			return
		}
		response, err := echoService.EchoHeader(intHeader, stringHeader)
		if err != nil {
			log.WithFields(logEchoHeader).Errorf("Error returned from service implementation: %s", err.Error())
			res.WriteHeader(500)
			log.WithFields(logEchoHeader).WithField("status", 500).Info("Completed request")
			return
		}
		if response == nil {
			log.WithFields(logEchoHeader).Errorf("No result returned from service implementation")
			res.WriteHeader(500)
			log.WithFields(logEchoHeader).WithField("status", 500).Info("Completed request")
			return
		}
		res.WriteHeader(200)
		json.NewEncoder(res).Encode(response)
		log.WithFields(logEchoHeader).WithField("status", 200).Info("Completed request")
		return
	})
	router.SetCors("/echo/header", &vestigo.CorsAccessControl{
		AllowHeaders: []string{"Int-Header", "String-Header"},
	})

	logEchoUrlParams := log.Fields{"operationId": "echo.echo_url_params", "method": "GET", "url": "/echo/url_params/:int_url/:string_url"}
	router.Get("/echo/url_params/:int_url/:string_url", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logEchoUrlParams).Info("Received request")
		urlParams := NewParamsParser(req.URL.Query())
		intUrl := urlParams.Int(":int_url")
		stringUrl := urlParams.String(":string_url")
		if len(urlParams.Errors) > 0 {
			log.WithFields(logEchoUrlParams).Warnf("Can't parse urlParams: %s", urlParams.Errors)
			res.WriteHeader(400)
			log.WithFields(logEchoUrlParams).WithField("status", 400).Info("Completed request")
			return
		}
		response, err := echoService.EchoUrlParams(intUrl, stringUrl)
		if err != nil {
			log.WithFields(logEchoUrlParams).Errorf("Error returned from service implementation: %s", err.Error())
			res.WriteHeader(500)
			log.WithFields(logEchoUrlParams).WithField("status", 500).Info("Completed request")
			return
		}
		if response == nil {
			log.WithFields(logEchoUrlParams).Errorf("No result returned from service implementation")
			res.WriteHeader(500)
			log.WithFields(logEchoUrlParams).WithField("status", 500).Info("Completed request")
			return
		}
		res.WriteHeader(200)
		json.NewEncoder(res).Encode(response)
		log.WithFields(logEchoUrlParams).WithField("status", 200).Info("Completed request")
		return
	})

	logSameOperationName := log.Fields{"operationId": "echo.same_operation_name", "method": "GET", "url": "/echo/same_operation_name"}
	router.Get("/echo/same_operation_name", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logSameOperationName).Info("Received request")
		response, err := echoService.SameOperationName()
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

func AddCheckRoutes(router *vestigo.Router, checkService check.Service) {
	logCheckEmpty := log.Fields{"operationId": "check.check_empty", "method": "GET", "url": "/check/empty"}
	router.Get("/check/empty", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logCheckEmpty).Info("Received request")
		err := checkService.CheckEmpty()
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

	logCheckQuery := log.Fields{"operationId": "check.check_query", "method": "GET", "url": "/check/query"}
	router.Get("/check/query", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logCheckQuery).Info("Received request")
		queryParams := NewParamsParser(req.URL.Query())
		pString := queryParams.String("p_string")
		pStringOpt := queryParams.StringNullable("p_string_opt")
		pStringArray := queryParams.StringArray("p_string_array")
		pDate := queryParams.Date("p_date")
		pDateArray := queryParams.DateArray("p_date_array")
		pDatetime := queryParams.DateTime("p_datetime")
		pInt := queryParams.Int("p_int")
		pLong := queryParams.Int64("p_long")
		pDecimal := queryParams.Decimal("p_decimal")
		pEnum := models.Choice(queryParams.StringEnum("p_enum", models.ChoiceValuesStrings))
		pStringDefaulted := queryParams.StringDefaulted("p_string_defaulted", "the default value")
		if len(queryParams.Errors) > 0 {
			log.WithFields(logCheckQuery).Warnf("Can't parse queryParams: %s", queryParams.Errors)
			res.WriteHeader(400)
			log.WithFields(logCheckQuery).WithField("status", 400).Info("Completed request")
			return
		}
		err := checkService.CheckQuery(pString, pStringOpt, pStringArray, pDate, pDateArray, pDatetime, pInt, pLong, pDecimal, pEnum, pStringDefaulted)
		if err != nil {
			log.WithFields(logCheckQuery).Errorf("Error returned from service implementation: %s", err.Error())
			res.WriteHeader(500)
			log.WithFields(logCheckQuery).WithField("status", 500).Info("Completed request")
			return
		}
		res.WriteHeader(200)
		log.WithFields(logCheckQuery).WithField("status", 200).Info("Completed request")
		return
	})

	logCheckUrlParams := log.Fields{"operationId": "check.check_url_params", "method": "GET", "url": "/check/url_params/:int_url/:string_url/:float_url/:bool_url/:uuid_url/:decimal_url/:date_url/:enum_url"}
	router.Get("/check/url_params/:int_url/:string_url/:float_url/:bool_url/:uuid_url/:decimal_url/:date_url/:enum_url", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logCheckUrlParams).Info("Received request")
		urlParams := NewParamsParser(req.URL.Query())
		intUrl := urlParams.Int64(":int_url")
		stringUrl := urlParams.String(":string_url")
		floatUrl := urlParams.Float32(":float_url")
		boolUrl := urlParams.Bool(":bool_url")
		uuidUrl := urlParams.Uuid(":uuid_url")
		decimalUrl := urlParams.Decimal(":decimal_url")
		dateUrl := urlParams.Date(":date_url")
		enumUrl := models.Choice(urlParams.StringEnum(":enum_url", models.ChoiceValuesStrings))
		if len(urlParams.Errors) > 0 {
			log.WithFields(logCheckUrlParams).Warnf("Can't parse urlParams: %s", urlParams.Errors)
			res.WriteHeader(400)
			log.WithFields(logCheckUrlParams).WithField("status", 400).Info("Completed request")
			return
		}
		err := checkService.CheckUrlParams(intUrl, stringUrl, floatUrl, boolUrl, uuidUrl, decimalUrl, dateUrl, enumUrl)
		if err != nil {
			log.WithFields(logCheckUrlParams).Errorf("Error returned from service implementation: %s", err.Error())
			res.WriteHeader(500)
			log.WithFields(logCheckUrlParams).WithField("status", 500).Info("Completed request")
			return
		}
		res.WriteHeader(200)
		log.WithFields(logCheckUrlParams).WithField("status", 200).Info("Completed request")
		return
	})

	logCheckForbidden := log.Fields{"operationId": "check.check_forbidden", "method": "GET", "url": "/check/forbidden"}
	router.Get("/check/forbidden", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logCheckForbidden).Info("Received request")
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
