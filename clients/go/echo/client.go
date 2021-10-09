package echo

import (
	"bytes"
	"encoding/json"
	"errors"
	"fmt"
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

func (client *Client) EchoBody(body *models.Message) (*EchoBodyResponse, error) {
	bodyJSON, err := json.Marshal(body)
	req, err := http.NewRequest("POST", client.baseUrl+"/echo/body", bytes.NewBuffer(bodyJSON))
	if err != nil { return nil, err }

	resp, err := http.DefaultClient.Do(req)
	if err != nil { return nil, err }

	if resp.StatusCode == 200 {
		responseBody, err := ioutil.ReadAll(resp.Body)
		err = resp.Body.Close()

		err = json.Unmarshal(responseBody, &body)
		if err != nil { return nil, err }

		return &EchoBodyResponse{Ok: body}, nil
	}

	return nil, errors.New(fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode))
}

func (client *Client) EchoQuery(intQuery int, stringQuery string) (*EchoQueryResponse, error) {
	req, err := http.NewRequest("GET", client.baseUrl+"/echo/query", nil)
	if err != nil { return nil, err }

	query := req.URL.Query()
	q := NewParamsConverter(query)
	q.Int("int_query", intQuery)
	q.String("string_query", stringQuery)
	req.URL.RawQuery = query.Encode()

	resp, err := http.DefaultClient.Do(req)
	if err != nil { return nil, err }

	if resp.StatusCode == 200 {
		responseBody, err := ioutil.ReadAll(resp.Body)
		err = resp.Body.Close()

		var body *models.Message
		err = json.Unmarshal(responseBody, &body)
		if err != nil { return nil, err }

		return &EchoQueryResponse{Ok: body}, nil
	}

	return nil, errors.New(fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode))
}

func (client *Client) EchoHeader(intHeader int, stringHeader string) (*EchoHeaderResponse, error) {
	req, err := http.NewRequest("GET", client.baseUrl+"/echo/header", nil)
	if err != nil { return nil, err }

	header := req.Header
	h := NewParamsConverter(header)
	h.Int("Int-Header", intHeader)
	h.String("String-Header", stringHeader)

	resp, err := http.DefaultClient.Do(req)
	if err != nil { return nil, err }

	if resp.StatusCode == 200 {
		responseBody, err := ioutil.ReadAll(resp.Body)
		err = resp.Body.Close()

		var body *models.Message
		err = json.Unmarshal(responseBody, &body)
		if err != nil { return nil, err }

		return &EchoHeaderResponse{Ok: body}, nil
	}

	return nil, errors.New(fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode))
}

func (client *Client) EchoUrlParams(intUrl int, stringUrl string) (*EchoUrlParamsResponse, error) {
	req, err := http.NewRequest("GET", client.baseUrl+fmt.Sprintf("/echo/url_params/%s/%s", convertInt(intUrl), stringUrl), nil)
	if err != nil { return nil, err }

	resp, err := http.DefaultClient.Do(req)
	if err != nil { return nil, err }

	if resp.StatusCode == 200 {
		responseBody, err := ioutil.ReadAll(resp.Body)
		err = resp.Body.Close()

		var body *models.Message
		err = json.Unmarshal(responseBody, &body)
		if err != nil { return nil, err }

		return &EchoUrlParamsResponse{Ok: body}, nil
	}

	return nil, errors.New(fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode))
}

func (client *Client) SameOperationName() (*SameOperationNameResponse, error) {
	req, err := http.NewRequest("GET", client.baseUrl+"/echo/same_operation_name", nil)
	if err != nil { return nil, err }

	resp, err := http.DefaultClient.Do(req)
	if err != nil { return nil, err }

	if resp.StatusCode == 200 {
		body := &Empty

		return &SameOperationNameResponse{Ok: body}, nil
	}

	if resp.StatusCode == 403 {
		body := &Empty

		return &SameOperationNameResponse{Forbidden: body}, nil
	}

	return nil, errors.New(fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode))
}
