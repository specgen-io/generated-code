package generated

import (
	"encoding/json"
	"fmt"
	"github.com/husobee/vestigo"
	log "github.com/sirupsen/logrus"
	"io/ioutil"
	"net/http"
	"test-service/generated/echo"
	"test-service/generated/models"
)

func AddEchoRoutes(router *vestigo.Router, echoService echo.Service) {
	logEchoBodyString := log.Fields{"operationId": "echo.echo_body_string", "method": "POST", "url": "/echo/body_string"}
	router.Post("/echo/body_string", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logEchoBodyString).Info("Received request")
		var err error
		if !CheckContentType(logEchoBodyString, res, req, "text/plain") {
			return
		}
		bodyData, err := ioutil.ReadAll(req.Body)
		if err != nil {
			error := models.BadRequestError{Message: fmt.Sprintf("Reading request body failed: %s", err.Error()), Params: nil}
			BadRequest(logEchoBodyString, res, &error)
			return
		}
		body := string(bodyData)
		response, err := echoService.EchoBodyString(body)
		if err != nil {
			error := models.InternalServerError{Message: fmt.Sprintf("Error returned from service implementation: %s", err.Error())}
			InternalServerError(logEchoBodyString, res, &error)
			return
		}
		if response == nil {
			error := models.InternalServerError{Message: "Service implementation returned nil"}
			InternalServerError(logEchoBodyString, res, &error)
			return
		}
		res.Header().Set("Content-Type", "text/plain")
		res.WriteHeader(200)
		res.Write([]byte(*response))
		log.WithFields(logEchoBodyString).WithField("status", 200).Info("Completed request")
	})

	logEchoBodyModel := log.Fields{"operationId": "echo.echo_body_model", "method": "POST", "url": "/echo/body_model"}
	router.Post("/echo/body_model", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logEchoBodyModel).Info("Received request")
		var err error
		if !CheckContentType(logEchoBodyModel, res, req, "application/json") {
			return
		}
		var body models.Message
		err = json.NewDecoder(req.Body).Decode(&body)
		if err != nil {
			error := models.BadRequestError{Message: fmt.Sprintf("Decoding body JSON failed: %s", err.Error()), Params: nil}
			BadRequest(logEchoBodyModel, res, &error)
			return
		}
		response, err := echoService.EchoBodyModel(&body)
		if err != nil {
			error := models.InternalServerError{Message: fmt.Sprintf("Error returned from service implementation: %s", err.Error())}
			InternalServerError(logEchoBodyModel, res, &error)
			return
		}
		if response == nil {
			error := models.InternalServerError{Message: "Service implementation returned nil"}
			InternalServerError(logEchoBodyModel, res, &error)
			return
		}
		res.Header().Set("Content-Type", "application/json")
		res.WriteHeader(200)
		json.NewEncoder(res).Encode(response)
		log.WithFields(logEchoBodyModel).WithField("status", 200).Info("Completed request")
	})

	logEchoBodyArray := log.Fields{"operationId": "echo.echo_body_array", "method": "POST", "url": "/echo/body_array"}
	router.Post("/echo/body_array", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logEchoBodyArray).Info("Received request")
		var err error
		if !CheckContentType(logEchoBodyArray, res, req, "application/json") {
			return
		}
		var body []string
		err = json.NewDecoder(req.Body).Decode(&body)
		if err != nil {
			error := models.BadRequestError{Message: fmt.Sprintf("Decoding body JSON failed: %s", err.Error()), Params: nil}
			BadRequest(logEchoBodyArray, res, &error)
			return
		}
		response, err := echoService.EchoBodyArray(&body)
		if err != nil {
			error := models.InternalServerError{Message: fmt.Sprintf("Error returned from service implementation: %s", err.Error())}
			InternalServerError(logEchoBodyArray, res, &error)
			return
		}
		if response == nil {
			error := models.InternalServerError{Message: "Service implementation returned nil"}
			InternalServerError(logEchoBodyArray, res, &error)
			return
		}
		res.Header().Set("Content-Type", "application/json")
		res.WriteHeader(200)
		json.NewEncoder(res).Encode(response)
		log.WithFields(logEchoBodyArray).WithField("status", 200).Info("Completed request")
	})

	logEchoBodyMap := log.Fields{"operationId": "echo.echo_body_map", "method": "POST", "url": "/echo/body_map"}
	router.Post("/echo/body_map", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logEchoBodyMap).Info("Received request")
		var err error
		if !CheckContentType(logEchoBodyMap, res, req, "application/json") {
			return
		}
		var body map[string]string
		err = json.NewDecoder(req.Body).Decode(&body)
		if err != nil {
			error := models.BadRequestError{Message: fmt.Sprintf("Decoding body JSON failed: %s", err.Error()), Params: nil}
			BadRequest(logEchoBodyMap, res, &error)
			return
		}
		response, err := echoService.EchoBodyMap(&body)
		if err != nil {
			error := models.InternalServerError{Message: fmt.Sprintf("Error returned from service implementation: %s", err.Error())}
			InternalServerError(logEchoBodyMap, res, &error)
			return
		}
		if response == nil {
			error := models.InternalServerError{Message: "Service implementation returned nil"}
			InternalServerError(logEchoBodyMap, res, &error)
			return
		}
		res.Header().Set("Content-Type", "application/json")
		res.WriteHeader(200)
		json.NewEncoder(res).Encode(response)
		log.WithFields(logEchoBodyMap).WithField("status", 200).Info("Completed request")
	})

	logEchoQuery := log.Fields{"operationId": "echo.echo_query", "method": "GET", "url": "/echo/query"}
	router.Get("/echo/query", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logEchoQuery).Info("Received request")
		var err error
		queryParams := NewParamsParser(req.URL.Query(), true)
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
			error := models.BadRequestError{Message: "Can't parse queryParams", Params: queryParams.Errors}
			BadRequest(logEchoQuery, res, &error)
			return
		}
		response, err := echoService.EchoQuery(intQuery, longQuery, floatQuery, doubleQuery, decimalQuery, boolQuery, stringQuery, stringOptQuery, stringDefaultedQuery, stringArrayQuery, uuidQuery, dateQuery, dateArrayQuery, datetimeQuery, enumQuery)
		if err != nil {
			error := models.InternalServerError{Message: fmt.Sprintf("Error returned from service implementation: %s", err.Error())}
			InternalServerError(logEchoQuery, res, &error)
			return
		}
		if response == nil {
			error := models.InternalServerError{Message: "Service implementation returned nil"}
			InternalServerError(logEchoQuery, res, &error)
			return
		}
		res.Header().Set("Content-Type", "application/json")
		res.WriteHeader(200)
		json.NewEncoder(res).Encode(response)
		log.WithFields(logEchoQuery).WithField("status", 200).Info("Completed request")
	})

	logEchoHeader := log.Fields{"operationId": "echo.echo_header", "method": "GET", "url": "/echo/header"}
	router.Get("/echo/header", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logEchoHeader).Info("Received request")
		var err error
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
			error := models.BadRequestError{Message: "Can't parse headerParams", Params: headerParams.Errors}
			BadRequest(logEchoHeader, res, &error)
			return
		}
		response, err := echoService.EchoHeader(intHeader, longHeader, floatHeader, doubleHeader, decimalHeader, boolHeader, stringHeader, stringOptHeader, stringDefaultedHeader, stringArrayHeader, uuidHeader, dateHeader, dateArrayHeader, datetimeHeader, enumHeader)
		if err != nil {
			error := models.InternalServerError{Message: fmt.Sprintf("Error returned from service implementation: %s", err.Error())}
			InternalServerError(logEchoHeader, res, &error)
			return
		}
		if response == nil {
			error := models.InternalServerError{Message: "Service implementation returned nil"}
			InternalServerError(logEchoHeader, res, &error)
			return
		}
		res.Header().Set("Content-Type", "application/json")
		res.WriteHeader(200)
		json.NewEncoder(res).Encode(response)
		log.WithFields(logEchoHeader).WithField("status", 200).Info("Completed request")
	})
	router.SetCors("/echo/header", &vestigo.CorsAccessControl{
		AllowHeaders: []string{"Int-Header", "Long-Header", "Float-Header", "Double-Header", "Decimal-Header", "Bool-Header", "String-Header", "String-Opt-Header", "String-Defaulted-Header", "String-Array-Header", "Uuid-Header", "Date-Header", "Date-Array-Header", "Datetime-Header", "Enum-Header"},
	})

	logEchoUrlParams := log.Fields{"operationId": "echo.echo_url_params", "method": "GET", "url": "/echo/url_params/:int_url/:long_url/:float_url/:double_url/:decimal_url/:bool_url/:string_url/:uuid_url/:date_url/:datetime_url/:enum_url"}
	router.Get("/echo/url_params/:int_url/:long_url/:float_url/:double_url/:decimal_url/:bool_url/:string_url/:uuid_url/:date_url/:datetime_url/:enum_url", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logEchoUrlParams).Info("Received request")
		var err error
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
			error := models.BadRequestError{Message: "Can't parse urlParams", Params: urlParams.Errors}
			BadRequest(logEchoUrlParams, res, &error)
			return
		}
		response, err := echoService.EchoUrlParams(intUrl, longUrl, floatUrl, doubleUrl, decimalUrl, boolUrl, stringUrl, uuidUrl, dateUrl, datetimeUrl, enumUrl)
		if err != nil {
			error := models.InternalServerError{Message: fmt.Sprintf("Error returned from service implementation: %s", err.Error())}
			InternalServerError(logEchoUrlParams, res, &error)
			return
		}
		if response == nil {
			error := models.InternalServerError{Message: "Service implementation returned nil"}
			InternalServerError(logEchoUrlParams, res, &error)
			return
		}
		res.Header().Set("Content-Type", "application/json")
		res.WriteHeader(200)
		json.NewEncoder(res).Encode(response)
		log.WithFields(logEchoUrlParams).WithField("status", 200).Info("Completed request")
	})

	logEchoEverything := log.Fields{"operationId": "echo.echo_everything", "method": "POST", "url": "/echo/everything/:date_url/:decimal_url"}
	router.Post("/echo/everything/:date_url/:decimal_url", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logEchoEverything).Info("Received request")
		var err error
		if !CheckContentType(logEchoEverything, res, req, "application/json") {
			return
		}
		var body models.Message
		err = json.NewDecoder(req.Body).Decode(&body)
		if err != nil {
			error := models.BadRequestError{Message: fmt.Sprintf("Decoding body JSON failed: %s", err.Error()), Params: nil}
			BadRequest(logEchoEverything, res, &error)
			return
		}
		queryParams := NewParamsParser(req.URL.Query(), true)
		floatQuery := queryParams.Float32("float_query")
		boolQuery := queryParams.Bool("bool_query")
		if len(queryParams.Errors) > 0 {
			error := models.BadRequestError{Message: "Can't parse queryParams", Params: queryParams.Errors}
			BadRequest(logEchoEverything, res, &error)
			return
		}
		headerParams := NewParamsParser(req.Header, true)
		uuidHeader := headerParams.Uuid("Uuid-Header")
		datetimeHeader := headerParams.DateTime("Datetime-Header")
		if len(headerParams.Errors) > 0 {
			error := models.BadRequestError{Message: "Can't parse headerParams", Params: headerParams.Errors}
			BadRequest(logEchoEverything, res, &error)
			return
		}
		urlParams := NewParamsParser(req.URL.Query(), false)
		dateUrl := urlParams.Date(":date_url")
		decimalUrl := urlParams.Decimal(":decimal_url")
		if len(urlParams.Errors) > 0 {
			error := models.BadRequestError{Message: "Can't parse urlParams", Params: urlParams.Errors}
			BadRequest(logEchoEverything, res, &error)
			return
		}
		response, err := echoService.EchoEverything(&body, floatQuery, boolQuery, uuidHeader, datetimeHeader, dateUrl, decimalUrl)
		if err != nil {
			error := models.InternalServerError{Message: fmt.Sprintf("Error returned from service implementation: %s", err.Error())}
			InternalServerError(logEchoEverything, res, &error)
			return
		}
		if response == nil {
			error := models.InternalServerError{Message: "Service implementation returned nil"}
			InternalServerError(logEchoEverything, res, &error)
			return
		}
		if response.Ok != nil {
			res.Header().Set("Content-Type", "application/json")
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
		error := models.InternalServerError{Message: "Result from service implementation does not have anything in it"}
		InternalServerError(logEchoEverything, res, &error)
		return
	})
	router.SetCors("/echo/everything/:date_url/:decimal_url", &vestigo.CorsAccessControl{
		AllowHeaders: []string{"Uuid-Header", "Datetime-Header"},
	})

	logSameOperationName := log.Fields{"operationId": "echo.same_operation_name", "method": "GET", "url": "/echo/same_operation_name"}
	router.Get("/echo/same_operation_name", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logSameOperationName).Info("Received request")
		var err error
		response, err := echoService.SameOperationName()
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
