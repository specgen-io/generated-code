package check

import (
	"cloud.google.com/go/civil"
	"encoding/json"
	"errors"
	"fmt"
	"github.com/google/uuid"
	"github.com/shopspring/decimal"
	log "github.com/sirupsen/logrus"
	"io/ioutil"
	"net/http"
	"test-client/models"
)

type EmptyDef struct{}

var Empty = EmptyDef{}

type CheckEmptyResponse struct {
	Ok *EmptyDef
}

type CheckQueryResponse struct {
	Ok *EmptyDef
}

type CheckUrlParamsResponse struct {
	Ok *EmptyDef
}

type CheckForbiddenResponse struct {
	Ok *models.Message
	Forbidden *EmptyDef
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

var logCheckEmpty = log.Fields{"operationId": "check.check_empty", "method": "GET", "url": "/check/empty"}
func (client *Client) CheckEmpty() (*CheckEmptyResponse, error) {
	req, err := http.NewRequest("GET", client.baseUrl+"/check/empty", nil)
	if err != nil {
		log.WithFields(logCheckEmpty).Error("Failed to create HTTP request", err.Error())
		return nil, err
	}

	log.WithFields(logCheckEmpty).Info("Sending request")
	resp, err := http.DefaultClient.Do(req)
	if err != nil {
		log.WithFields(logCheckEmpty).Error("Request failed", err.Error())
		return nil, err
	}

	if resp.StatusCode == 200 {
		log.WithFields(logCheckEmpty).WithField("status", 200).Info("Received response")
		body := &Empty

		return &CheckEmptyResponse{Ok: body}, nil
	}

	msg := fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode)
	log.WithFields(logCheckEmpty).Error(msg)
	return nil, errors.New(msg)
}

var logCheckQuery = log.Fields{"operationId": "check.check_query", "method": "GET", "url": "/check/query"}
func (client *Client) CheckQuery(pString string, pStringOpt *string, pStringArray []string, pDate civil.Date, pDateArray []civil.Date, pDatetime civil.DateTime, pInt int, pLong int64, pDecimal decimal.Decimal, pEnum models.Choice, pStringDefaulted string) (*CheckQueryResponse, error) {
	req, err := http.NewRequest("GET", client.baseUrl+"/check/query", nil)
	if err != nil {
		log.WithFields(logCheckQuery).Error("Failed to create HTTP request", err.Error())
		return nil, err
	}

	query := req.URL.Query()
	q := NewParamsConverter(query)
	q.String("p_string", pString)
	q.StringNullable("p_string_opt", pStringOpt)
	q.StringArray("p_string_array", pStringArray)
	q.Date("p_date", pDate)
	q.DateArray("p_date_array", pDateArray)
	q.DateTime("p_datetime", pDatetime)
	q.Int("p_int", pInt)
	q.Int64("p_long", pLong)
	q.Decimal("p_decimal", pDecimal)
	q.StringEnum("p_enum", pEnum)
	q.String("p_string_defaulted", pStringDefaulted)
	req.URL.RawQuery = query.Encode()

	log.WithFields(logCheckQuery).Info("Sending request")
	resp, err := http.DefaultClient.Do(req)
	if err != nil {
		log.WithFields(logCheckQuery).Error("Request failed", err.Error())
		return nil, err
	}

	if resp.StatusCode == 200 {
		log.WithFields(logCheckQuery).WithField("status", 200).Info("Received response")
		body := &Empty

		return &CheckQueryResponse{Ok: body}, nil
	}

	msg := fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode)
	log.WithFields(logCheckQuery).Error(msg)
	return nil, errors.New(msg)
}

var logCheckUrlParams = log.Fields{"operationId": "check.check_url_params", "method": "GET", "url": "/check/url_params/{int_url}/{string_url}/{float_url}/{bool_url}/{uuid_url}/{decimal_url}/{date_url}"}
func (client *Client) CheckUrlParams(intUrl int64, stringUrl string, floatUrl float32, boolUrl bool, uuidUrl uuid.UUID, decimalUrl decimal.Decimal, dateUrl civil.Date) (*CheckUrlParamsResponse, error) {
	req, err := http.NewRequest("GET", client.baseUrl+fmt.Sprintf("/check/url_params/%s/%s/%s/%s/%s/%s/%s", convertInt64(intUrl), stringUrl, convertFloat32(floatUrl), convertBool(boolUrl), convertUuid(uuidUrl), convertDecimal(decimalUrl), convertDate(dateUrl)), nil)
	if err != nil {
		log.WithFields(logCheckUrlParams).Error("Failed to create HTTP request", err.Error())
		return nil, err
	}

	log.WithFields(logCheckUrlParams).Info("Sending request")
	resp, err := http.DefaultClient.Do(req)
	if err != nil {
		log.WithFields(logCheckUrlParams).Error("Request failed", err.Error())
		return nil, err
	}

	if resp.StatusCode == 200 {
		log.WithFields(logCheckUrlParams).WithField("status", 200).Info("Received response")
		body := &Empty

		return &CheckUrlParamsResponse{Ok: body}, nil
	}

	msg := fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode)
	log.WithFields(logCheckUrlParams).Error(msg)
	return nil, errors.New(msg)
}

var logCheckForbidden = log.Fields{"operationId": "check.check_forbidden", "method": "GET", "url": "/check/forbidden"}
func (client *Client) CheckForbidden() (*CheckForbiddenResponse, error) {
	req, err := http.NewRequest("GET", client.baseUrl+"/check/forbidden", nil)
	if err != nil {
		log.WithFields(logCheckForbidden).Error("Failed to create HTTP request", err.Error())
		return nil, err
	}

	log.WithFields(logCheckForbidden).Info("Sending request")
	resp, err := http.DefaultClient.Do(req)
	if err != nil {
		log.WithFields(logCheckForbidden).Error("Request failed", err.Error())
		return nil, err
	}

	if resp.StatusCode == 200 {
		log.WithFields(logCheckForbidden).WithField("status", 200).Info("Received response")
		responseBody, err := ioutil.ReadAll(resp.Body)
		err = resp.Body.Close()

		var body *models.Message
		err = json.Unmarshal(responseBody, &body)
		if err != nil {
			log.WithFields(logCheckForbidden).Error("Failed to parse response JSON", err.Error())
			return nil, err
		}

		return &CheckForbiddenResponse{Ok: body}, nil
	}

	if resp.StatusCode == 403 {
		log.WithFields(logCheckForbidden).WithField("status", 403).Info("Received response")
		body := &Empty

		return &CheckForbiddenResponse{Forbidden: body}, nil
	}

	msg := fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode)
	log.WithFields(logCheckForbidden).Error(msg)
	return nil, errors.New(msg)
}

var logSameOperationName = log.Fields{"operationId": "check.same_operation_name", "method": "GET", "url": "/check/same_operation_name"}
func (client *Client) SameOperationName() (*SameOperationNameResponse, error) {
	req, err := http.NewRequest("GET", client.baseUrl+"/check/same_operation_name", nil)
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
