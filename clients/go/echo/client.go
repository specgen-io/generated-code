package echo

import (
	"bytes"
	"encoding/json"
	"errors"
	"fmt"
	log "github.com/sirupsen/logrus"
	"io/ioutil"
	"net/http"
	"test-client/models"
)

type EmptyDef struct{}

var Empty = EmptyDef{}

type EchoBodyResponse struct {
	Ok *models.Message
}

type EchoQueryResponse struct {
	Ok *models.Message
}

type EchoHeaderResponse struct {
	Ok *models.Message
}

type EchoUrlParamsResponse struct {
	Ok *models.Message
}

type SameOperationNameResponse struct {
	Ok *EmptyDef
	Forbidden *EmptyDef
}

type Client struct {
	baseUrl string
}

func NewClient(baseUrl string) *Client {
	return &Client{baseUrl}
}

var logEchoBody = log.Fields{"operationId": "echo.echo_body", "method": "POST", "url": "/echo/body"}
func (client *Client) EchoBody(body *models.Message) (*EchoBodyResponse, error) {
	bodyJSON, err := json.Marshal(body)
	req, err := http.NewRequest("POST", client.baseUrl+"/echo/body", bytes.NewBuffer(bodyJSON))
	if err != nil {
		log.WithFields(logEchoBody).Error("Failed to create HTTP request", err.Error())
		return nil, err
	}

	log.WithFields(logEchoBody).Info("Sending request")
	resp, err := http.DefaultClient.Do(req)
	if err != nil {
		log.WithFields(logEchoBody).Error("Request failed", err.Error())
		return nil, err
	}

	if resp.StatusCode == 200 {
		log.WithFields(logEchoBody).WithField("status", 200).Info("Received response")
		responseBody, err := ioutil.ReadAll(resp.Body)
		err = resp.Body.Close()

		err = json.Unmarshal(responseBody, &body)
		if err != nil {
			log.WithFields(logEchoBody).Error("Failed to parse response JSON", err.Error())
			return nil, err
		}

		return &EchoBodyResponse{Ok: body}, nil
	}

	msg := fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode)
	log.WithFields(logEchoBody).Error(msg)
	return nil, errors.New(msg)
}

var logEchoQuery = log.Fields{"operationId": "echo.echo_query", "method": "GET", "url": "/echo/query"}
func (client *Client) EchoQuery(intQuery int, stringQuery string) (*EchoQueryResponse, error) {
	req, err := http.NewRequest("GET", client.baseUrl+"/echo/query", nil)
	if err != nil {
		log.WithFields(logEchoQuery).Error("Failed to create HTTP request", err.Error())
		return nil, err
	}

	query := req.URL.Query()
	q := NewParamsConverter(query)
	q.Int("int_query", intQuery)
	q.String("string_query", stringQuery)
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

		var body *models.Message
		err = json.Unmarshal(responseBody, &body)
		if err != nil {
			log.WithFields(logEchoQuery).Error("Failed to parse response JSON", err.Error())
			return nil, err
		}

		return &EchoQueryResponse{Ok: body}, nil
	}

	msg := fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode)
	log.WithFields(logEchoQuery).Error(msg)
	return nil, errors.New(msg)
}

var logEchoHeader = log.Fields{"operationId": "echo.echo_header", "method": "GET", "url": "/echo/header"}
func (client *Client) EchoHeader(intHeader int, stringHeader string) (*EchoHeaderResponse, error) {
	req, err := http.NewRequest("GET", client.baseUrl+"/echo/header", nil)
	if err != nil {
		log.WithFields(logEchoHeader).Error("Failed to create HTTP request", err.Error())
		return nil, err
	}

	header := req.Header
	h := NewParamsConverter(header)
	h.Int("Int-Header", intHeader)
	h.String("String-Header", stringHeader)

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

		var body *models.Message
		err = json.Unmarshal(responseBody, &body)
		if err != nil {
			log.WithFields(logEchoHeader).Error("Failed to parse response JSON", err.Error())
			return nil, err
		}

		return &EchoHeaderResponse{Ok: body}, nil
	}

	msg := fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode)
	log.WithFields(logEchoHeader).Error(msg)
	return nil, errors.New(msg)
}

var logEchoUrlParams = log.Fields{"operationId": "echo.echo_url_params", "method": "GET", "url": "/echo/url_params/{int_url}/{string_url}"}
func (client *Client) EchoUrlParams(intUrl int, stringUrl string) (*EchoUrlParamsResponse, error) {
	req, err := http.NewRequest("GET", client.baseUrl+fmt.Sprintf("/echo/url_params/%s/%s", convertInt(intUrl), stringUrl), nil)
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

		var body *models.Message
		err = json.Unmarshal(responseBody, &body)
		if err != nil {
			log.WithFields(logEchoUrlParams).Error("Failed to parse response JSON", err.Error())
			return nil, err
		}

		return &EchoUrlParamsResponse{Ok: body}, nil
	}

	msg := fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode)
	log.WithFields(logEchoUrlParams).Error(msg)
	return nil, errors.New(msg)
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
		body := &Empty

		return &SameOperationNameResponse{Ok: body}, nil
	}

	if resp.StatusCode == 403 {
		log.WithFields(logSameOperationName).WithField("status", 403).Info("Received response")
		body := &Empty

		return &SameOperationNameResponse{Forbidden: body}, nil
	}

	msg := fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode)
	log.WithFields(logSameOperationName).Error(msg)
	return nil, errors.New(msg)
}
