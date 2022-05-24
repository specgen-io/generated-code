package echo

import (
	"bytes"
	"cloud.google.com/go/civil"
	"encoding/json"
	"errors"
	"fmt"
	"github.com/google/uuid"
	"github.com/shopspring/decimal"
	log "github.com/sirupsen/logrus"
	"io/ioutil"
	"net/http"
	"test-client/empty"
	"test-client/models"
)

type EchoEverythingResponse struct {
	Ok        *models.Everything
	Forbidden *empty.Type
}

type SameOperationNameResponse struct {
	Ok        *empty.Type
	Forbidden *empty.Type
}

type EchoSuccessResponse struct {
	Ok       *models.OkResult
	Created  *models.CreatedResult
	Accepted *models.AcceptedResult
}

type Client struct {
	baseUrl string
}

func NewClient(baseUrl string) *Client {
	return &Client{baseUrl}
}

var logEchoBodyString = log.Fields{"operationId": "echo.echo_body_string", "method": "POST", "url": "/echo/body_string"}
func (client *Client) EchoBodyString(body string) (*string, error) {
	bodyData := []byte(body)
	req, err := http.NewRequest("POST", client.baseUrl+"/echo/body_string", bytes.NewBuffer(bodyData))
	if err != nil {
		log.WithFields(logEchoBodyString).Error("Failed to create HTTP request", err.Error())
		return nil, err
	}
	req.Header.Set("Content-Type", "text/plain")

	log.WithFields(logEchoBodyString).Info("Sending request")
	resp, err := http.DefaultClient.Do(req)
	if err != nil {
		log.WithFields(logEchoBodyString).Error("Request failed", err.Error())
		return nil, err
	}

	if resp.StatusCode == 200 {
		log.WithFields(logEchoBodyString).WithField("status", 200).Info("Received response")
		responseBody, err := ioutil.ReadAll(resp.Body)
		err = resp.Body.Close()
		if err != nil {
			log.WithFields(logEchoBodyString).Error("Reading request body failed", err.Error())
			return nil, err
		}
		result := string(responseBody)
		return &result, nil
	}

	msg := fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode)
	log.WithFields(logEchoBodyString).Error(msg)
	err = errors.New(msg)
	return nil, err
}

var logEchoBodyModel = log.Fields{"operationId": "echo.echo_body_model", "method": "POST", "url": "/echo/body_model"}
func (client *Client) EchoBodyModel(body *models.Message) (*models.Message, error) {
	bodyData, err := json.Marshal(body)
	req, err := http.NewRequest("POST", client.baseUrl+"/echo/body_model", bytes.NewBuffer(bodyData))
	if err != nil {
		log.WithFields(logEchoBodyModel).Error("Failed to create HTTP request", err.Error())
		return nil, err
	}
	req.Header.Set("Content-Type", "application/json")

	log.WithFields(logEchoBodyModel).Info("Sending request")
	resp, err := http.DefaultClient.Do(req)
	if err != nil {
		log.WithFields(logEchoBodyModel).Error("Request failed", err.Error())
		return nil, err
	}

	if resp.StatusCode == 200 {
		log.WithFields(logEchoBodyModel).WithField("status", 200).Info("Received response")
		responseBody, err := ioutil.ReadAll(resp.Body)
		err = resp.Body.Close()
		var result models.Message
		err = json.Unmarshal(responseBody, &result)
		if err != nil {
			log.WithFields(logEchoBodyModel).Error("Failed to parse response JSON", err.Error())
			return nil, err
		}
		return &result, nil
	}

	msg := fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode)
	log.WithFields(logEchoBodyModel).Error(msg)
	err = errors.New(msg)
	return nil, err
}

var logEchoBodyArray = log.Fields{"operationId": "echo.echo_body_array", "method": "POST", "url": "/echo/body_array"}
func (client *Client) EchoBodyArray(body *[]string) (*[]string, error) {
	bodyData, err := json.Marshal(body)
	req, err := http.NewRequest("POST", client.baseUrl+"/echo/body_array", bytes.NewBuffer(bodyData))
	if err != nil {
		log.WithFields(logEchoBodyArray).Error("Failed to create HTTP request", err.Error())
		return nil, err
	}
	req.Header.Set("Content-Type", "application/json")

	log.WithFields(logEchoBodyArray).Info("Sending request")
	resp, err := http.DefaultClient.Do(req)
	if err != nil {
		log.WithFields(logEchoBodyArray).Error("Request failed", err.Error())
		return nil, err
	}

	if resp.StatusCode == 200 {
		log.WithFields(logEchoBodyArray).WithField("status", 200).Info("Received response")
		responseBody, err := ioutil.ReadAll(resp.Body)
		err = resp.Body.Close()
		var result []string
		err = json.Unmarshal(responseBody, &result)
		if err != nil {
			log.WithFields(logEchoBodyArray).Error("Failed to parse response JSON", err.Error())
			return nil, err
		}
		return &result, nil
	}

	msg := fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode)
	log.WithFields(logEchoBodyArray).Error(msg)
	err = errors.New(msg)
	return nil, err
}

var logEchoBodyMap = log.Fields{"operationId": "echo.echo_body_map", "method": "POST", "url": "/echo/body_map"}
func (client *Client) EchoBodyMap(body *map[string]string) (*map[string]string, error) {
	bodyData, err := json.Marshal(body)
	req, err := http.NewRequest("POST", client.baseUrl+"/echo/body_map", bytes.NewBuffer(bodyData))
	if err != nil {
		log.WithFields(logEchoBodyMap).Error("Failed to create HTTP request", err.Error())
		return nil, err
	}
	req.Header.Set("Content-Type", "application/json")

	log.WithFields(logEchoBodyMap).Info("Sending request")
	resp, err := http.DefaultClient.Do(req)
	if err != nil {
		log.WithFields(logEchoBodyMap).Error("Request failed", err.Error())
		return nil, err
	}

	if resp.StatusCode == 200 {
		log.WithFields(logEchoBodyMap).WithField("status", 200).Info("Received response")
		responseBody, err := ioutil.ReadAll(resp.Body)
		err = resp.Body.Close()
		var result map[string]string
		err = json.Unmarshal(responseBody, &result)
		if err != nil {
			log.WithFields(logEchoBodyMap).Error("Failed to parse response JSON", err.Error())
			return nil, err
		}
		return &result, nil
	}

	msg := fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode)
	log.WithFields(logEchoBodyMap).Error(msg)
	err = errors.New(msg)
	return nil, err
}

var logEchoQuery = log.Fields{"operationId": "echo.echo_query", "method": "GET", "url": "/echo/query"}
func (client *Client) EchoQuery(intQuery int, longQuery int64, floatQuery float32, doubleQuery float64, decimalQuery decimal.Decimal, boolQuery bool, stringQuery string, stringOptQuery *string, stringDefaultedQuery string, stringArrayQuery []string, uuidQuery uuid.UUID, dateQuery civil.Date, dateArrayQuery []civil.Date, datetimeQuery civil.DateTime, enumQuery models.Choice) (*models.Parameters, error) {
	req, err := http.NewRequest("GET", client.baseUrl+"/echo/query", nil)
	if err != nil {
		log.WithFields(logEchoQuery).Error("Failed to create HTTP request", err.Error())
		return nil, err
	}

	query := req.URL.Query()
	q := NewParamsConverter(query)
	q.Int("int_query", intQuery)
	q.Int64("long_query", longQuery)
	q.Float32("float_query", floatQuery)
	q.Float64("double_query", doubleQuery)
	q.Decimal("decimal_query", decimalQuery)
	q.Bool("bool_query", boolQuery)
	q.String("string_query", stringQuery)
	q.StringNullable("string_opt_query", stringOptQuery)
	q.String("string_defaulted_query", stringDefaultedQuery)
	q.StringArray("string_array_query", stringArrayQuery)
	q.Uuid("uuid_query", uuidQuery)
	q.Date("date_query", dateQuery)
	q.DateArray("date_array_query", dateArrayQuery)
	q.DateTime("datetime_query", datetimeQuery)
	q.StringEnum("enum_query", enumQuery)
	req.URL.RawQuery = query.Encode()

	log.WithFields(logEchoQuery).Info("Sending request")
	resp, err := http.DefaultClient.Do(req)
	if err != nil {
		log.WithFields(logEchoQuery).Error("Request failed", err.Error())
		return nil, err
	}

	if resp.StatusCode == 200 {
		log.WithFields(logEchoQuery).WithField("status", 200).Info("Received response")
		responseBody, err := ioutil.ReadAll(resp.Body)
		err = resp.Body.Close()
		var result models.Parameters
		err = json.Unmarshal(responseBody, &result)
		if err != nil {
			log.WithFields(logEchoQuery).Error("Failed to parse response JSON", err.Error())
			return nil, err
		}
		return &result, nil
	}

	msg := fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode)
	log.WithFields(logEchoQuery).Error(msg)
	err = errors.New(msg)
	return nil, err
}

var logEchoHeader = log.Fields{"operationId": "echo.echo_header", "method": "GET", "url": "/echo/header"}
func (client *Client) EchoHeader(intHeader int, longHeader int64, floatHeader float32, doubleHeader float64, decimalHeader decimal.Decimal, boolHeader bool, stringHeader string, stringOptHeader *string, stringDefaultedHeader string, stringArrayHeader []string, uuidHeader uuid.UUID, dateHeader civil.Date, dateArrayHeader []civil.Date, datetimeHeader civil.DateTime, enumHeader models.Choice) (*models.Parameters, error) {
	req, err := http.NewRequest("GET", client.baseUrl+"/echo/header", nil)
	if err != nil {
		log.WithFields(logEchoHeader).Error("Failed to create HTTP request", err.Error())
		return nil, err
	}

	header := req.Header
	h := NewParamsConverter(header)
	h.Int("Int-Header", intHeader)
	h.Int64("Long-Header", longHeader)
	h.Float32("Float-Header", floatHeader)
	h.Float64("Double-Header", doubleHeader)
	h.Decimal("Decimal-Header", decimalHeader)
	h.Bool("Bool-Header", boolHeader)
	h.String("String-Header", stringHeader)
	h.StringNullable("String-Opt-Header", stringOptHeader)
	h.String("String-Defaulted-Header", stringDefaultedHeader)
	h.StringArray("String-Array-Header", stringArrayHeader)
	h.Uuid("Uuid-Header", uuidHeader)
	h.Date("Date-Header", dateHeader)
	h.DateArray("Date-Array-Header", dateArrayHeader)
	h.DateTime("Datetime-Header", datetimeHeader)
	h.StringEnum("Enum-Header", enumHeader)

	log.WithFields(logEchoHeader).Info("Sending request")
	resp, err := http.DefaultClient.Do(req)
	if err != nil {
		log.WithFields(logEchoHeader).Error("Request failed", err.Error())
		return nil, err
	}

	if resp.StatusCode == 200 {
		log.WithFields(logEchoHeader).WithField("status", 200).Info("Received response")
		responseBody, err := ioutil.ReadAll(resp.Body)
		err = resp.Body.Close()
		var result models.Parameters
		err = json.Unmarshal(responseBody, &result)
		if err != nil {
			log.WithFields(logEchoHeader).Error("Failed to parse response JSON", err.Error())
			return nil, err
		}
		return &result, nil
	}

	msg := fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode)
	log.WithFields(logEchoHeader).Error(msg)
	err = errors.New(msg)
	return nil, err
}

var logEchoUrlParams = log.Fields{"operationId": "echo.echo_url_params", "method": "GET", "url": "/echo/url_params/{int_url}/{long_url}/{float_url}/{double_url}/{decimal_url}/{bool_url}/{string_url}/{uuid_url}/{date_url}/{datetime_url}/{enum_url}"}
func (client *Client) EchoUrlParams(intUrl int, longUrl int64, floatUrl float32, doubleUrl float64, decimalUrl decimal.Decimal, boolUrl bool, stringUrl string, uuidUrl uuid.UUID, dateUrl civil.Date, datetimeUrl civil.DateTime, enumUrl models.Choice) (*models.UrlParameters, error) {
	req, err := http.NewRequest("GET", client.baseUrl+fmt.Sprintf("/echo/url_params/%s/%s/%s/%s/%s/%s/%s/%s/%s/%s/%s", Int(intUrl), Int64(longUrl), Float32(floatUrl), Float64(doubleUrl), Decimal(decimalUrl), Bool(boolUrl), stringUrl, Uuid(uuidUrl), Date(dateUrl), DateTime(datetimeUrl), StringEnum(enumUrl)), nil)
	if err != nil {
		log.WithFields(logEchoUrlParams).Error("Failed to create HTTP request", err.Error())
		return nil, err
	}

	log.WithFields(logEchoUrlParams).Info("Sending request")
	resp, err := http.DefaultClient.Do(req)
	if err != nil {
		log.WithFields(logEchoUrlParams).Error("Request failed", err.Error())
		return nil, err
	}

	if resp.StatusCode == 200 {
		log.WithFields(logEchoUrlParams).WithField("status", 200).Info("Received response")
		responseBody, err := ioutil.ReadAll(resp.Body)
		err = resp.Body.Close()
		var result models.UrlParameters
		err = json.Unmarshal(responseBody, &result)
		if err != nil {
			log.WithFields(logEchoUrlParams).Error("Failed to parse response JSON", err.Error())
			return nil, err
		}
		return &result, nil
	}

	msg := fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode)
	log.WithFields(logEchoUrlParams).Error(msg)
	err = errors.New(msg)
	return nil, err
}

var logEchoEverything = log.Fields{"operationId": "echo.echo_everything", "method": "POST", "url": "/echo/everything/{date_url}/{decimal_url}"}
func (client *Client) EchoEverything(body *models.Message, floatQuery float32, boolQuery bool, uuidHeader uuid.UUID, datetimeHeader civil.DateTime, dateUrl civil.Date, decimalUrl decimal.Decimal) (*EchoEverythingResponse, error) {
	bodyData, err := json.Marshal(body)
	req, err := http.NewRequest("POST", client.baseUrl+fmt.Sprintf("/echo/everything/%s/%s", Date(dateUrl), Decimal(decimalUrl)), bytes.NewBuffer(bodyData))
	if err != nil {
		log.WithFields(logEchoEverything).Error("Failed to create HTTP request", err.Error())
		return nil, err
	}
	req.Header.Set("Content-Type", "application/json")

	query := req.URL.Query()
	q := NewParamsConverter(query)
	q.Float32("float_query", floatQuery)
	q.Bool("bool_query", boolQuery)
	req.URL.RawQuery = query.Encode()

	header := req.Header
	h := NewParamsConverter(header)
	h.Uuid("Uuid-Header", uuidHeader)
	h.DateTime("Datetime-Header", datetimeHeader)

	log.WithFields(logEchoEverything).Info("Sending request")
	resp, err := http.DefaultClient.Do(req)
	if err != nil {
		log.WithFields(logEchoEverything).Error("Request failed", err.Error())
		return nil, err
	}

	if resp.StatusCode == 200 {
		log.WithFields(logEchoEverything).WithField("status", 200).Info("Received response")
		responseBody, err := ioutil.ReadAll(resp.Body)
		err = resp.Body.Close()
		var result models.Everything
		err = json.Unmarshal(responseBody, &result)
		if err != nil {
			log.WithFields(logEchoEverything).Error("Failed to parse response JSON", err.Error())
			return nil, err
		}
		return &EchoEverythingResponse{Ok: &result}, nil
	}

	if resp.StatusCode == 403 {
		log.WithFields(logEchoEverything).WithField("status", 403).Info("Received response")
		return &EchoEverythingResponse{Forbidden: &empty.Type{}}, nil
	}

	msg := fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode)
	log.WithFields(logEchoEverything).Error(msg)
	err = errors.New(msg)
	return nil, err
}

var logSameOperationName = log.Fields{"operationId": "echo.same_operation_name", "method": "GET", "url": "/echo/same_operation_name"}
func (client *Client) SameOperationName() (*SameOperationNameResponse, error) {
	req, err := http.NewRequest("GET", client.baseUrl+"/echo/same_operation_name", nil)
	if err != nil {
		log.WithFields(logSameOperationName).Error("Failed to create HTTP request", err.Error())
		return nil, err
	}

	log.WithFields(logSameOperationName).Info("Sending request")
	resp, err := http.DefaultClient.Do(req)
	if err != nil {
		log.WithFields(logSameOperationName).Error("Request failed", err.Error())
		return nil, err
	}

	if resp.StatusCode == 200 {
		log.WithFields(logSameOperationName).WithField("status", 200).Info("Received response")
		return &SameOperationNameResponse{Ok: &empty.Type{}}, nil
	}

	if resp.StatusCode == 403 {
		log.WithFields(logSameOperationName).WithField("status", 403).Info("Received response")
		return &SameOperationNameResponse{Forbidden: &empty.Type{}}, nil
	}

	msg := fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode)
	log.WithFields(logSameOperationName).Error(msg)
	err = errors.New(msg)
	return nil, err
}

var logEchoSuccess = log.Fields{"operationId": "echo.echo_success", "method": "GET", "url": "/echo/success"}
func (client *Client) EchoSuccess(resultStatus string) (*EchoSuccessResponse, error) {
	req, err := http.NewRequest("GET", client.baseUrl+"/echo/success", nil)
	if err != nil {
		log.WithFields(logEchoSuccess).Error("Failed to create HTTP request", err.Error())
		return nil, err
	}

	query := req.URL.Query()
	q := NewParamsConverter(query)
	q.String("result_status", resultStatus)
	req.URL.RawQuery = query.Encode()

	log.WithFields(logEchoSuccess).Info("Sending request")
	resp, err := http.DefaultClient.Do(req)
	if err != nil {
		log.WithFields(logEchoSuccess).Error("Request failed", err.Error())
		return nil, err
	}

	if resp.StatusCode == 200 {
		log.WithFields(logEchoSuccess).WithField("status", 200).Info("Received response")
		responseBody, err := ioutil.ReadAll(resp.Body)
		err = resp.Body.Close()
		var result models.OkResult
		err = json.Unmarshal(responseBody, &result)
		if err != nil {
			log.WithFields(logEchoSuccess).Error("Failed to parse response JSON", err.Error())
			return nil, err
		}
		return &EchoSuccessResponse{Ok: &result}, nil
	}

	if resp.StatusCode == 201 {
		log.WithFields(logEchoSuccess).WithField("status", 201).Info("Received response")
		responseBody, err := ioutil.ReadAll(resp.Body)
		err = resp.Body.Close()
		var result models.CreatedResult
		err = json.Unmarshal(responseBody, &result)
		if err != nil {
			log.WithFields(logEchoSuccess).Error("Failed to parse response JSON", err.Error())
			return nil, err
		}
		return &EchoSuccessResponse{Created: &result}, nil
	}

	if resp.StatusCode == 202 {
		log.WithFields(logEchoSuccess).WithField("status", 202).Info("Received response")
		responseBody, err := ioutil.ReadAll(resp.Body)
		err = resp.Body.Close()
		var result models.AcceptedResult
		err = json.Unmarshal(responseBody, &result)
		if err != nil {
			log.WithFields(logEchoSuccess).Error("Failed to parse response JSON", err.Error())
			return nil, err
		}
		return &EchoSuccessResponse{Accepted: &result}, nil
	}

	msg := fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode)
	log.WithFields(logEchoSuccess).Error(msg)
	err = errors.New(msg)
	return nil, err
}
