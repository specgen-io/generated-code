package spec

import (
	"encoding/json"
	"github.com/husobee/vestigo"
	log "github.com/sirupsen/logrus"
	"net/http"
)

func AddEchoRoutes(router *vestigo.Router, echoService IEchoService) {

	logEchoBody := log.Fields{"operationId": "echo.echo_body", "method": "POST", "url": "/echo/body"}
	router.Post("/echo/body", func(res http.ResponseWriter, req *http.Request) {
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

	logEchoQuery := log.Fields{"operationId": "echo.echo_query", "method": "GET", "url": "/echo/query"}
	router.Get("/echo/query", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logEchoQuery).Info("Received request")
		queryParams := NewParamsParser(req.URL.Query())
		intQuery := queryParams.Int("int_query")
		stringQuery := queryParams.String("string_query")
		if len(queryParams.Errors) > 0 {
			log.Warnf("Can't parse queryParams: %s", queryParams.Errors)
			res.WriteHeader(400)
			log.WithFields(logEchoQuery).WithField("status", 400).Info("Completed request")
			return
		}
		response, err := echoService.EchoQuery(intQuery, stringQuery)
		if response == nil || err != nil {
			if err != nil {
				log.Errorf("Error returned from service implementation: %s", err.Error())
			} else {
				log.Errorf("No result returned from service implementation")
			}
			res.WriteHeader(500)
			log.WithFields(logEchoQuery).WithField("status", 500).Info("Completed request")
			return
		}
		if response.Ok != nil {
			res.WriteHeader(200)
			json.NewEncoder(res).Encode(response.Ok)
			log.WithFields(logEchoQuery).WithField("status", 200).Info("Completed request")
			return
		}
		log.Error("Result from service implementation does not have anything in it")
		res.WriteHeader(500)
		log.WithFields(logEchoQuery).WithField("status", 500).Info("Completed request")
		return
	})

	logEchoHeader := log.Fields{"operationId": "echo.echo_header", "method": "GET", "url": "/echo/header"}
	router.Get("/echo/header", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logEchoHeader).Info("Received request")
		headerParams := NewParamsParser(req.Header)
		intHeader := headerParams.Int("Int-Header")
		stringHeader := headerParams.String("String-Header")
		if len(headerParams.Errors) > 0 {
			log.Warnf("Can't parse headerParams: %s", headerParams.Errors)
			res.WriteHeader(400)
			log.WithFields(logEchoHeader).WithField("status", 400).Info("Completed request")
			return
		}
		response, err := echoService.EchoHeader(intHeader, stringHeader)
		if response == nil || err != nil {
			if err != nil {
				log.Errorf("Error returned from service implementation: %s", err.Error())
			} else {
				log.Errorf("No result returned from service implementation")
			}
			res.WriteHeader(500)
			log.WithFields(logEchoHeader).WithField("status", 500).Info("Completed request")
			return
		}
		if response.Ok != nil {
			res.WriteHeader(200)
			json.NewEncoder(res).Encode(response.Ok)
			log.WithFields(logEchoHeader).WithField("status", 200).Info("Completed request")
			return
		}
		log.Error("Result from service implementation does not have anything in it")
		res.WriteHeader(500)
		log.WithFields(logEchoHeader).WithField("status", 500).Info("Completed request")
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
			log.Warnf("Can't parse urlParams: %s", urlParams.Errors)
			res.WriteHeader(400)
			log.WithFields(logEchoUrlParams).WithField("status", 400).Info("Completed request")
			return
		}
		response, err := echoService.EchoUrlParams(intUrl, stringUrl)
		if response == nil || err != nil {
			if err != nil {
				log.Errorf("Error returned from service implementation: %s", err.Error())
			} else {
				log.Errorf("No result returned from service implementation")
			}
			res.WriteHeader(500)
			log.WithFields(logEchoUrlParams).WithField("status", 500).Info("Completed request")
			return
		}
		if response.Ok != nil {
			res.WriteHeader(200)
			json.NewEncoder(res).Encode(response.Ok)
			log.WithFields(logEchoUrlParams).WithField("status", 200).Info("Completed request")
			return
		}
		log.Error("Result from service implementation does not have anything in it")
		res.WriteHeader(500)
		log.WithFields(logEchoUrlParams).WithField("status", 500).Info("Completed request")
		return
	})
}

func AddCheckRoutes(router *vestigo.Router, checkService ICheckService) {

	logCheckEmpty := log.Fields{"operationId": "check.check_empty", "method": "GET", "url": "/check/empty"}
	router.Get("/check/empty", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logCheckEmpty).Info("Received request")
		response, err := checkService.CheckEmpty()
		if response == nil || err != nil {
			if err != nil {
				log.Errorf("Error returned from service implementation: %s", err.Error())
			} else {
				log.Errorf("No result returned from service implementation")
			}
			res.WriteHeader(500)
			log.WithFields(logCheckEmpty).WithField("status", 500).Info("Completed request")
			return
		}
		if response.Ok != nil {
			res.WriteHeader(200)
			log.WithFields(logCheckEmpty).WithField("status", 200).Info("Completed request")
			return
		}
		log.Error("Result from service implementation does not have anything in it")
		res.WriteHeader(500)
		log.WithFields(logCheckEmpty).WithField("status", 500).Info("Completed request")
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
		pEnum := Choice(queryParams.StringEnum("p_enum", ChoiceValuesStrings))
		pStringDefaulted := queryParams.StringDefaulted("p_string_defaulted", "the default value")
		if len(queryParams.Errors) > 0 {
			log.Warnf("Can't parse queryParams: %s", queryParams.Errors)
			res.WriteHeader(400)
			log.WithFields(logCheckQuery).WithField("status", 400).Info("Completed request")
			return
		}
		response, err := checkService.CheckQuery(pString, pStringOpt, pStringArray, pDate, pDateArray, pDatetime, pInt, pLong, pDecimal, pEnum, pStringDefaulted)
		if response == nil || err != nil {
			if err != nil {
				log.Errorf("Error returned from service implementation: %s", err.Error())
			} else {
				log.Errorf("No result returned from service implementation")
			}
			res.WriteHeader(500)
			log.WithFields(logCheckQuery).WithField("status", 500).Info("Completed request")
			return
		}
		if response.Ok != nil {
			res.WriteHeader(200)
			log.WithFields(logCheckQuery).WithField("status", 200).Info("Completed request")
			return
		}
		log.Error("Result from service implementation does not have anything in it")
		res.WriteHeader(500)
		log.WithFields(logCheckQuery).WithField("status", 500).Info("Completed request")
		return
	})

	logCheckUrlParams := log.Fields{"operationId": "check.check_url_params", "method": "GET", "url": "/check/url_params/:int_url/:string_url/:float_url/:bool_url/:uuid_url/:decimal_url/:date_url"}
	router.Get("/check/url_params/:int_url/:string_url/:float_url/:bool_url/:uuid_url/:decimal_url/:date_url", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logCheckUrlParams).Info("Received request")
		urlParams := NewParamsParser(req.URL.Query())
		intUrl := urlParams.Int64(":int_url")
		stringUrl := urlParams.String(":string_url")
		floatUrl := urlParams.Float32(":float_url")
		boolUrl := urlParams.Bool(":bool_url")
		uuidUrl := urlParams.Uuid(":uuid_url")
		decimalUrl := urlParams.Decimal(":decimal_url")
		dateUrl := urlParams.Date(":date_url")
		if len(urlParams.Errors) > 0 {
			log.Warnf("Can't parse urlParams: %s", urlParams.Errors)
			res.WriteHeader(400)
			log.WithFields(logCheckUrlParams).WithField("status", 400).Info("Completed request")
			return
		}
		response, err := checkService.CheckUrlParams(intUrl, stringUrl, floatUrl, boolUrl, uuidUrl, decimalUrl, dateUrl)
		if response == nil || err != nil {
			if err != nil {
				log.Errorf("Error returned from service implementation: %s", err.Error())
			} else {
				log.Errorf("No result returned from service implementation")
			}
			res.WriteHeader(500)
			log.WithFields(logCheckUrlParams).WithField("status", 500).Info("Completed request")
			return
		}
		if response.Ok != nil {
			res.WriteHeader(200)
			log.WithFields(logCheckUrlParams).WithField("status", 200).Info("Completed request")
			return
		}
		log.Error("Result from service implementation does not have anything in it")
		res.WriteHeader(500)
		log.WithFields(logCheckUrlParams).WithField("status", 500).Info("Completed request")
		return
	})

	logCheckForbidden := log.Fields{"operationId": "check.check_forbidden", "method": "GET", "url": "/check/forbidden"}
	router.Get("/check/forbidden", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logCheckForbidden).Info("Received request")
		response, err := checkService.CheckForbidden()
		if response == nil || err != nil {
			if err != nil {
				log.Errorf("Error returned from service implementation: %s", err.Error())
			} else {
				log.Errorf("No result returned from service implementation")
			}
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
		log.Error("Result from service implementation does not have anything in it")
		res.WriteHeader(500)
		log.WithFields(logCheckForbidden).WithField("status", 500).Info("Completed request")
		return
	})
}
