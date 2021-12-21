package .

import (
	"encoding/json"
	"github.com/husobee/vestigo"
	log "github.com/sirupsen/logrus"
	"io/ioutil"
	"net/http"
	"test-service/echo"
	"test-service/models"
)

func AddEchoRoutes(router *vestigo.Router, echoService echo.Service) {
	logEchoBodyString := log.Fields{"operationId": "echo.echo_body_string", "method": "POST", "url": "/echo/body_string"}
	router.Post("/echo/body_string", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logEchoBodyString).Info("Received request")
		bodyData, err := ioutil.ReadAll(req.Body)
		if err != nil {
			log.WithFields(logEchoBodyString).Warnf("Reading request body failed: %s", err.Error())
			res.WriteHeader(400)
			log.WithFields(logEchoBodyString).WithField("status", 400).Info("Completed request")
			return
		}
		body := string(bodyData)
		response, err := echoService.EchoBodyString(body)
		if err != nil {
			log.WithFields(logEchoBodyString).Errorf("Error returned from service implementation: %s", err.Error())
			res.WriteHeader(500)
			log.WithFields(logEchoBodyString).WithField("status", 500).Info("Completed request")
			return
		}
		if response == nil {
			log.WithFields(logEchoBodyString).Errorf("No result returned from service implementation")
			res.WriteHeader(500)
			log.WithFields(logEchoBodyString).WithField("status", 500).Info("Completed request")
			return
		}
		res.WriteHeader(200)
		res.Write([]byte(*response))
		log.WithFields(logEchoBodyString).WithField("status", 200).Info("Completed request")
		return
	})

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
		queryParams := NewParamsParser(req.URL.Query(), false)
		intQuery := queryParams.Int("int_query")
		longQuery := queryParams.Int64("long_query")
		floatQuery := queryParams.Float32("float_query")
		doubleQuery := queryParams.Float64("double_query")
		decimalQuery := queryParams.Decimal("decimal_query")
		boolQuery := queryParams.Bool("bool_query")
		stringQuery := queryParams.String("string_query")
		stringOptQuery := queryParams.StringNullable("string_opt_query")
		stringDefaultedQuery := queryParams.StringDefaulted("string_defaulted_query", "the default value")
		stringArrayQuery := queryParams.StringArray("string_array_query")
		uuidQuery := queryParams.Uuid("uuid_query")
		dateQuery := queryParams.Date("date_query")
		dateArrayQuery := queryParams.DateArray("date_array_query")
		datetimeQuery := queryParams.DateTime("datetime_query")
		enumQuery := models.Choice(queryParams.StringEnum("enum_query", models.ChoiceValuesStrings))
		if len(queryParams.Errors) > 0 {
			log.WithFields(logEchoQuery).Warnf("Can't parse queryParams: %s", queryParams.Errors)
			res.WriteHeader(400)
			log.WithFields(logEchoQuery).WithField("status", 400).Info("Completed request")
			return
		}
		response, err := echoService.EchoQuery(intQuery, longQuery, floatQuery, doubleQuery, decimalQuery, boolQuery, stringQuery, stringOptQuery, stringDefaultedQuery, stringArrayQuery, uuidQuery, dateQuery, dateArrayQuery, datetimeQuery, enumQuery)
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
		headerParams := NewParamsParser(req.Header, true)
		intHeader := headerParams.Int("Int-Header")
		longHeader := headerParams.Int64("Long-Header")
		floatHeader := headerParams.Float32("Float-Header")
		doubleHeader := headerParams.Float64("Double-Header")
		decimalHeader := headerParams.Decimal("Decimal-Header")
		boolHeader := headerParams.Bool("Bool-Header")
		stringHeader := headerParams.String("String-Header")
		stringOptHeader := headerParams.StringNullable("String-Opt-Header")
		stringDefaultedHeader := headerParams.StringDefaulted("String-Defaulted-Header", "the default value")
		stringArrayHeader := headerParams.StringArray("String-Array-Header")
		uuidHeader := headerParams.Uuid("Uuid-Header")
		dateHeader := headerParams.Date("Date-Header")
		dateArrayHeader := headerParams.DateArray("Date-Array-Header")
		datetimeHeader := headerParams.DateTime("Datetime-Header")
		enumHeader := models.Choice(headerParams.StringEnum("Enum-Header", models.ChoiceValuesStrings))
		if len(headerParams.Errors) > 0 {
			log.WithFields(logEchoHeader).Warnf("Can't parse headerParams: %s", headerParams.Errors)
			res.WriteHeader(400)
			log.WithFields(logEchoHeader).WithField("status", 400).Info("Completed request")
			return
		}
		response, err := echoService.EchoHeader(intHeader, longHeader, floatHeader, doubleHeader, decimalHeader, boolHeader, stringHeader, stringOptHeader, stringDefaultedHeader, stringArrayHeader, uuidHeader, dateHeader, dateArrayHeader, datetimeHeader, enumHeader)
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
		AllowHeaders: []string{"Int-Header", "Long-Header", "Float-Header", "Double-Header", "Decimal-Header", "Bool-Header", "String-Header", "String-Opt-Header", "String-Defaulted-Header", "String-Array-Header", "Uuid-Header", "Date-Header", "Date-Array-Header", "Datetime-Header", "Enum-Header"},
	})

	logEchoUrlParams := log.Fields{"operationId": "echo.echo_url_params", "method": "GET", "url": "/echo/url_params/:int_url/:long_url/:float_url/:double_url/:decimal_url/:bool_url/:string_url/:uuid_url/:date_url/:datetime_url/:enum_url"}
	router.Get("/echo/url_params/:int_url/:long_url/:float_url/:double_url/:decimal_url/:bool_url/:string_url/:uuid_url/:date_url/:datetime_url/:enum_url", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logEchoUrlParams).Info("Received request")
		urlParams := NewParamsParser(req.URL.Query(), false)
		intUrl := urlParams.Int(":int_url")
		longUrl := urlParams.Int64(":long_url")
		floatUrl := urlParams.Float32(":float_url")
		doubleUrl := urlParams.Float64(":double_url")
		decimalUrl := urlParams.Decimal(":decimal_url")
		boolUrl := urlParams.Bool(":bool_url")
		stringUrl := urlParams.String(":string_url")
		uuidUrl := urlParams.Uuid(":uuid_url")
		dateUrl := urlParams.Date(":date_url")
		datetimeUrl := urlParams.DateTime(":datetime_url")
		enumUrl := models.Choice(urlParams.StringEnum(":enum_url", models.ChoiceValuesStrings))
		if len(urlParams.Errors) > 0 {
			log.WithFields(logEchoUrlParams).Warnf("Can't parse urlParams: %s", urlParams.Errors)
			res.WriteHeader(400)
			log.WithFields(logEchoUrlParams).WithField("status", 400).Info("Completed request")
			return
		}
		response, err := echoService.EchoUrlParams(intUrl, longUrl, floatUrl, doubleUrl, decimalUrl, boolUrl, stringUrl, uuidUrl, dateUrl, datetimeUrl, enumUrl)
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

	logEchoEverything := log.Fields{"operationId": "echo.echo_everything", "method": "POST", "url": "/echo/everything/:date_url/:decimal_url"}
	router.Post("/echo/everything/:date_url/:decimal_url", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logEchoEverything).Info("Received request")
		var body models.Message
		err := json.NewDecoder(req.Body).Decode(&body)
		if err != nil {
			log.WithFields(logEchoEverything).Warnf("Decoding body JSON failed: %s", err.Error())
			res.WriteHeader(400)
			log.WithFields(logEchoEverything).WithField("status", 400).Info("Completed request")
			return
		}
		queryParams := NewParamsParser(req.URL.Query(), false)
		floatQuery := queryParams.Float32("float_query")
		boolQuery := queryParams.Bool("bool_query")
		if len(queryParams.Errors) > 0 {
			log.WithFields(logEchoEverything).Warnf("Can't parse queryParams: %s", queryParams.Errors)
			res.WriteHeader(400)
			log.WithFields(logEchoEverything).WithField("status", 400).Info("Completed request")
			return
		}
		headerParams := NewParamsParser(req.Header, true)
		uuidHeader := headerParams.Uuid("Uuid-Header")
		datetimeHeader := headerParams.DateTime("Datetime-Header")
		if len(headerParams.Errors) > 0 {
			log.WithFields(logEchoEverything).Warnf("Can't parse headerParams: %s", headerParams.Errors)
			res.WriteHeader(400)
			log.WithFields(logEchoEverything).WithField("status", 400).Info("Completed request")
			return
		}
		urlParams := NewParamsParser(req.URL.Query(), false)
		dateUrl := urlParams.Date(":date_url")
		decimalUrl := urlParams.Decimal(":decimal_url")
		if len(urlParams.Errors) > 0 {
			log.WithFields(logEchoEverything).Warnf("Can't parse urlParams: %s", urlParams.Errors)
			res.WriteHeader(400)
			log.WithFields(logEchoEverything).WithField("status", 400).Info("Completed request")
			return
		}
		response, err := echoService.EchoEverything(&body, floatQuery, boolQuery, uuidHeader, datetimeHeader, dateUrl, decimalUrl)
		if err != nil {
			log.WithFields(logEchoEverything).Errorf("Error returned from service implementation: %s", err.Error())
			res.WriteHeader(500)
			log.WithFields(logEchoEverything).WithField("status", 500).Info("Completed request")
			return
		}
		if response == nil {
			log.WithFields(logEchoEverything).Errorf("No result returned from service implementation")
			res.WriteHeader(500)
			log.WithFields(logEchoEverything).WithField("status", 500).Info("Completed request")
			return
		}
		if response.Ok != nil {
			res.WriteHeader(200)
			json.NewEncoder(res).Encode(response.Ok)
			log.WithFields(logEchoEverything).WithField("status", 200).Info("Completed request")
			return
		}
		if response.Forbidden != nil {
			res.WriteHeader(403)
			log.WithFields(logEchoEverything).WithField("status", 403).Info("Completed request")
			return
		}
		log.WithFields(logEchoEverything).Error("Result from service implementation does not have anything in it")
		res.WriteHeader(500)
		log.WithFields(logEchoEverything).WithField("status", 500).Info("Completed request")
		return
	})
	router.SetCors("/echo/everything/:date_url/:decimal_url", &vestigo.CorsAccessControl{
		AllowHeaders: []string{"Uuid-Header", "Datetime-Header"},
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
