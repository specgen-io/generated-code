package generated

import (
	"encoding/json"
	"fmt"
	"github.com/husobee/vestigo"
	log "github.com/sirupsen/logrus"
	"io/ioutil"
	"net/http"
	"strings"
	"test-service/generated/echo"
	"test-service/generated/models"
)

func AddEchoRoutes(router *vestigo.Router, echoService echo.Service) {
	respondBadRequest := func(logFields log.Fields, res http.ResponseWriter, error *models.BadRequestError) {
		log.WithFields(logFields).Warn(error.Message)
		respondJson(logFields, res, 400, error)
	}
	_ = respondBadRequest
	respondNotFound := func(logFields log.Fields, res http.ResponseWriter, error *models.NotFoundError) {
		log.WithFields(logFields).Warn(error.Message)
		respondJson(logFields, res, 404, error)
	}
	_ = respondNotFound

	respondInternalServerError := func(logFields log.Fields, res http.ResponseWriter, error *models.InternalServerError) {
		log.WithFields(logFields).Warn(error.Message)
		respondJson(logFields, res, 500, error)
	}
	_ = respondInternalServerError

	logEchoBodyString := log.Fields{"operationId": "echo.echo_body_string", "method": "POST", "url": "/echo/body_string"}
	router.Post("/echo/body_string", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logEchoBodyString).Info("Received request")
		var err error
		contentType := req.Header.Get("Content-Type")
		if !strings.Contains(contentType, "text/plain") {
			respondBadRequest(logEchoBodyString, res, &models.BadRequestError{Message: fmt.Sprintf("Wrong Content-type: %s", contentType), Errors: nil})
			return
		}
		bodyData, err := ioutil.ReadAll(req.Body)
		if err != nil {
			respondBadRequest(logEchoBodyString, res, &models.BadRequestError{Message: fmt.Sprintf("Reading request body failed: %s", err.Error()), Errors: nil})
			return
		}
		body := string(bodyData)
		response, err := echoService.EchoBodyString(body)
		if err != nil {
			respondInternalServerError(logEchoBodyString, res, &models.InternalServerError{Message: fmt.Sprintf("Error returned from service implementation: %s", err.Error())})
			return
		}
		if response == nil {
			respondInternalServerError(logEchoBodyString, res, &models.InternalServerError{Message: "Service implementation returned nil"})
			return
		}
		respondText(logEchoBodyString, res, 200, *response)
	})

	logEchoBodyModel := log.Fields{"operationId": "echo.echo_body_model", "method": "POST", "url": "/echo/body_model"}
	router.Post("/echo/body_model", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logEchoBodyModel).Info("Received request")
		var err error
		contentType := req.Header.Get("Content-Type")
		if !strings.Contains(contentType, "application/json") {
			respondBadRequest(logEchoBodyModel, res, &models.BadRequestError{Message: fmt.Sprintf("Wrong Content-type: %s", contentType), Errors: nil})
			return
		}
		var body models.Message
		err = json.NewDecoder(req.Body).Decode(&body)
		if err != nil {
			respondBadRequest(logEchoBodyModel, res, &models.BadRequestError{Message: fmt.Sprintf("Decoding body JSON failed: %s", err.Error()), Errors: nil})
			return
		}
		response, err := echoService.EchoBodyModel(&body)
		if err != nil {
			respondInternalServerError(logEchoBodyModel, res, &models.InternalServerError{Message: fmt.Sprintf("Error returned from service implementation: %s", err.Error())})
			return
		}
		if response == nil {
			respondInternalServerError(logEchoBodyModel, res, &models.InternalServerError{Message: "Service implementation returned nil"})
			return
		}
		respondJson(logEchoBodyModel, res, 200, response)
	})

	logEchoBodyArray := log.Fields{"operationId": "echo.echo_body_array", "method": "POST", "url": "/echo/body_array"}
	router.Post("/echo/body_array", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logEchoBodyArray).Info("Received request")
		var err error
		contentType := req.Header.Get("Content-Type")
		if !strings.Contains(contentType, "application/json") {
			respondBadRequest(logEchoBodyArray, res, &models.BadRequestError{Message: fmt.Sprintf("Wrong Content-type: %s", contentType), Errors: nil})
			return
		}
		var body []string
		err = json.NewDecoder(req.Body).Decode(&body)
		if err != nil {
			respondBadRequest(logEchoBodyArray, res, &models.BadRequestError{Message: fmt.Sprintf("Decoding body JSON failed: %s", err.Error()), Errors: nil})
			return
		}
		response, err := echoService.EchoBodyArray(&body)
		if err != nil {
			respondInternalServerError(logEchoBodyArray, res, &models.InternalServerError{Message: fmt.Sprintf("Error returned from service implementation: %s", err.Error())})
			return
		}
		if response == nil {
			respondInternalServerError(logEchoBodyArray, res, &models.InternalServerError{Message: "Service implementation returned nil"})
			return
		}
		respondJson(logEchoBodyArray, res, 200, response)
	})

	logEchoBodyMap := log.Fields{"operationId": "echo.echo_body_map", "method": "POST", "url": "/echo/body_map"}
	router.Post("/echo/body_map", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logEchoBodyMap).Info("Received request")
		var err error
		contentType := req.Header.Get("Content-Type")
		if !strings.Contains(contentType, "application/json") {
			respondBadRequest(logEchoBodyMap, res, &models.BadRequestError{Message: fmt.Sprintf("Wrong Content-type: %s", contentType), Errors: nil})
			return
		}
		var body map[string]string
		err = json.NewDecoder(req.Body).Decode(&body)
		if err != nil {
			respondBadRequest(logEchoBodyMap, res, &models.BadRequestError{Message: fmt.Sprintf("Decoding body JSON failed: %s", err.Error()), Errors: nil})
			return
		}
		response, err := echoService.EchoBodyMap(&body)
		if err != nil {
			respondInternalServerError(logEchoBodyMap, res, &models.InternalServerError{Message: fmt.Sprintf("Error returned from service implementation: %s", err.Error())})
			return
		}
		if response == nil {
			respondInternalServerError(logEchoBodyMap, res, &models.InternalServerError{Message: "Service implementation returned nil"})
			return
		}
		respondJson(logEchoBodyMap, res, 200, response)
	})

	logEchoQuery := log.Fields{"operationId": "echo.echo_query", "method": "GET", "url": "/echo/query"}
	router.Get("/echo/query", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logEchoQuery).Info("Received request")
		var err error
		query := NewParamsParser(req.URL.Query(), true)
		intQuery := query.Int("int_query")
		longQuery := query.Int64("long_query")
		floatQuery := query.Float32("float_query")
		doubleQuery := query.Float64("double_query")
		decimalQuery := query.Decimal("decimal_query")
		boolQuery := query.Bool("bool_query")
		stringQuery := query.String("string_query")
		stringOptQuery := query.StringNullable("string_opt_query")
		stringDefaultedQuery := query.StringDefaulted("string_defaulted_query", "the default value")
		stringArrayQuery := query.StringArray("string_array_query")
		uuidQuery := query.Uuid("uuid_query")
		dateQuery := query.Date("date_query")
		dateArrayQuery := query.DateArray("date_array_query")
		datetimeQuery := query.DateTime("datetime_query")
		enumQuery := models.Choice(query.StringEnum("enum_query", models.ChoiceValuesStrings))
		if len(query.Errors) > 0 {
			respondBadRequest(logEchoQuery, res, &models.BadRequestError{Message: "Can't parse query", Errors: query.Errors})
			return
		}
		response, err := echoService.EchoQuery(intQuery, longQuery, floatQuery, doubleQuery, decimalQuery, boolQuery, stringQuery, stringOptQuery, stringDefaultedQuery, stringArrayQuery, uuidQuery, dateQuery, dateArrayQuery, datetimeQuery, enumQuery)
		if err != nil {
			respondInternalServerError(logEchoQuery, res, &models.InternalServerError{Message: fmt.Sprintf("Error returned from service implementation: %s", err.Error())})
			return
		}
		if response == nil {
			respondInternalServerError(logEchoQuery, res, &models.InternalServerError{Message: "Service implementation returned nil"})
			return
		}
		respondJson(logEchoQuery, res, 200, response)
	})

	logEchoHeader := log.Fields{"operationId": "echo.echo_header", "method": "GET", "url": "/echo/header"}
	router.Get("/echo/header", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logEchoHeader).Info("Received request")
		var err error
		headers := NewParamsParser(req.Header, true)
		intHeader := headers.Int("Int-Header")
		longHeader := headers.Int64("Long-Header")
		floatHeader := headers.Float32("Float-Header")
		doubleHeader := headers.Float64("Double-Header")
		decimalHeader := headers.Decimal("Decimal-Header")
		boolHeader := headers.Bool("Bool-Header")
		stringHeader := headers.String("String-Header")
		stringOptHeader := headers.StringNullable("String-Opt-Header")
		stringDefaultedHeader := headers.StringDefaulted("String-Defaulted-Header", "the default value")
		stringArrayHeader := headers.StringArray("String-Array-Header")
		uuidHeader := headers.Uuid("Uuid-Header")
		dateHeader := headers.Date("Date-Header")
		dateArrayHeader := headers.DateArray("Date-Array-Header")
		datetimeHeader := headers.DateTime("Datetime-Header")
		enumHeader := models.Choice(headers.StringEnum("Enum-Header", models.ChoiceValuesStrings))
		if len(headers.Errors) > 0 {
			respondBadRequest(logEchoHeader, res, &models.BadRequestError{Message: "Can't parse headers", Errors: headers.Errors})
			return
		}
		response, err := echoService.EchoHeader(intHeader, longHeader, floatHeader, doubleHeader, decimalHeader, boolHeader, stringHeader, stringOptHeader, stringDefaultedHeader, stringArrayHeader, uuidHeader, dateHeader, dateArrayHeader, datetimeHeader, enumHeader)
		if err != nil {
			respondInternalServerError(logEchoHeader, res, &models.InternalServerError{Message: fmt.Sprintf("Error returned from service implementation: %s", err.Error())})
			return
		}
		if response == nil {
			respondInternalServerError(logEchoHeader, res, &models.InternalServerError{Message: "Service implementation returned nil"})
			return
		}
		respondJson(logEchoHeader, res, 200, response)
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
			respondNotFound(logEchoUrlParams, res, &models.NotFoundError{Message: "Can't parse url parameters"})
			return
		}
		response, err := echoService.EchoUrlParams(intUrl, longUrl, floatUrl, doubleUrl, decimalUrl, boolUrl, stringUrl, uuidUrl, dateUrl, datetimeUrl, enumUrl)
		if err != nil {
			respondInternalServerError(logEchoUrlParams, res, &models.InternalServerError{Message: fmt.Sprintf("Error returned from service implementation: %s", err.Error())})
			return
		}
		if response == nil {
			respondInternalServerError(logEchoUrlParams, res, &models.InternalServerError{Message: "Service implementation returned nil"})
			return
		}
		respondJson(logEchoUrlParams, res, 200, response)
	})

	logEchoEverything := log.Fields{"operationId": "echo.echo_everything", "method": "POST", "url": "/echo/everything/:date_url/:decimal_url"}
	router.Post("/echo/everything/:date_url/:decimal_url", func(res http.ResponseWriter, req *http.Request) {
		log.WithFields(logEchoEverything).Info("Received request")
		var err error
		urlParams := NewParamsParser(req.URL.Query(), false)
		dateUrl := urlParams.Date(":date_url")
		decimalUrl := urlParams.Decimal(":decimal_url")
		if len(urlParams.Errors) > 0 {
			respondNotFound(logEchoEverything, res, &models.NotFoundError{Message: "Can't parse url parameters"})
			return
		}
		headers := NewParamsParser(req.Header, true)
		uuidHeader := headers.Uuid("Uuid-Header")
		datetimeHeader := headers.DateTime("Datetime-Header")
		if len(headers.Errors) > 0 {
			respondBadRequest(logEchoEverything, res, &models.BadRequestError{Message: "Can't parse headers", Errors: headers.Errors})
			return
		}
		query := NewParamsParser(req.URL.Query(), true)
		floatQuery := query.Float32("float_query")
		boolQuery := query.Bool("bool_query")
		if len(query.Errors) > 0 {
			respondBadRequest(logEchoEverything, res, &models.BadRequestError{Message: "Can't parse query", Errors: query.Errors})
			return
		}
		contentType := req.Header.Get("Content-Type")
		if !strings.Contains(contentType, "application/json") {
			respondBadRequest(logEchoEverything, res, &models.BadRequestError{Message: fmt.Sprintf("Wrong Content-type: %s", contentType), Errors: nil})
			return
		}
		var body models.Message
		err = json.NewDecoder(req.Body).Decode(&body)
		if err != nil {
			respondBadRequest(logEchoEverything, res, &models.BadRequestError{Message: fmt.Sprintf("Decoding body JSON failed: %s", err.Error()), Errors: nil})
			return
		}
		response, err := echoService.EchoEverything(&body, floatQuery, boolQuery, uuidHeader, datetimeHeader, dateUrl, decimalUrl)
		if err != nil {
			respondInternalServerError(logEchoEverything, res, &models.InternalServerError{Message: fmt.Sprintf("Error returned from service implementation: %s", err.Error())})
			return
		}
		if response == nil {
			respondInternalServerError(logEchoEverything, res, &models.InternalServerError{Message: "Service implementation returned nil"})
			return
		}
		if response.Ok != nil {
			respondJson(logEchoEverything, res, 200, response.Ok)
			return
		}
		if response.Forbidden != nil {
			respondEmpty(logEchoEverything, res, 403)
			return
		}
		respondInternalServerError(logEchoEverything, res, &models.InternalServerError{Message: "Result from service implementation does not have anything in it"})
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
