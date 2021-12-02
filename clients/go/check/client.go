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
func (client *Client) CheckEmpty() error {
	req, err := http.NewRequest("GET", client.baseUrl+"/check/empty", nil)
	if err != nil {
		log.WithFields(logCheckEmpty).Error("Failed to create HTTP request", err.Error())
		return err
	}

	log.WithFields(logCheckEmpty).Info("Sending request")
	resp, err := http.DefaultClient.Do(req)
	if err != nil {
		log.WithFields(logCheckEmpty).Error("Request failed", err.Error())
		return err
	}

	if resp.StatusCode == 200 {
		log.WithFields(logCheckEmpty).WithField("status", 200).Info("Received response")
		return nil
	}

	msg := fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode)
	log.WithFields(logCheckEmpty).Error(msg)
	err = errors.New(msg)
	return err
}

var logCheckQuery = log.Fields{"operationId": "check.check_query", "method": "GET", "url": "/check/query"}
func (client *Client) CheckQuery(pString string, pStringOpt *string, pStringArray []string, pDate civil.Date, pDateArray []civil.Date, pDatetime civil.DateTime, pInt int, pLong int64, pDecimal decimal.Decimal, pEnum models.Choice, pStringDefaulted string) error {
	req, err := http.NewRequest("GET", client.baseUrl+"/check/query", nil)
	if err != nil {
		log.WithFields(logCheckQuery).Error("Failed to create HTTP request", err.Error())
		return err
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
		return err
	}

	if resp.StatusCode == 200 {
		log.WithFields(logCheckQuery).WithField("status", 200).Info("Received response")
		return nil
	}

	msg := fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode)
	log.WithFields(logCheckQuery).Error(msg)
	err = errors.New(msg)
	return err
}

var logCheckUrlParams = log.Fields{"operationId": "check.check_url_params", "method": "GET", "url": "/check/url_params/{int_url}/{string_url}/{float_url}/{bool_url}/{uuid_url}/{decimal_url}/{date_url}/{enum_url}"}
func (client *Client) CheckUrlParams(intUrl int64, stringUrl string, floatUrl float32, boolUrl bool, uuidUrl uuid.UUID, decimalUrl decimal.Decimal, dateUrl civil.Date, enumUrl models.Choice) error {
	req, err := http.NewRequest("GET", client.baseUrl+fmt.Sprintf("/check/url_params/%s/%s/%s/%s/%s/%s/%s/%s", convertInt64(intUrl), stringUrl, convertFloat32(floatUrl), convertBool(boolUrl), convertUuid(uuidUrl), convertDecimal(decimalUrl), convertDate(dateUrl), convertStringEnum(enumUrl)), nil)
	if err != nil {
		log.WithFields(logCheckUrlParams).Error("Failed to create HTTP request", err.Error())
		return err
	}

	log.WithFields(logCheckUrlParams).Info("Sending request")
	resp, err := http.DefaultClient.Do(req)
	if err != nil {
		log.WithFields(logCheckUrlParams).Error("Request failed", err.Error())
		return err
	}

	if resp.StatusCode == 200 {
		log.WithFields(logCheckUrlParams).WithField("status", 200).Info("Received response")
		return nil
	}

	msg := fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode)
	log.WithFields(logCheckUrlParams).Error(msg)
	err = errors.New(msg)
	return err
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

		var result models.Message
		err = json.Unmarshal(responseBody, &result)
		if err != nil {
			log.WithFields(logCheckForbidden).Error("Failed to parse response JSON", err.Error())
			return nil, err
		}
		return &CheckForbiddenResponse{Ok: &result}, nil
	}

	if resp.StatusCode == 403 {
		log.WithFields(logCheckForbidden).WithField("status", 403).Info("Received response")
		return &CheckForbiddenResponse{Forbidden: &EmptyDef{}}, nil
	}

	msg := fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode)
	log.WithFields(logCheckForbidden).Error(msg)
	err = errors.New(msg)
	return nil, err
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
		return &SameOperationNameResponse{Ok: &EmptyDef{}}, nil
	}

	if resp.StatusCode == 403 {
		log.WithFields(logSameOperationName).WithField("status", 403).Info("Received response")
		return &SameOperationNameResponse{Forbidden: &EmptyDef{}}, nil
	}

	msg := fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode)
	log.WithFields(logSameOperationName).Error(msg)
	err = errors.New(msg)
	return nil, err
}
