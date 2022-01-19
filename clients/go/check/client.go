package check

import (
	"encoding/json"
	"errors"
	"fmt"
	log "github.com/sirupsen/logrus"
	"io/ioutil"
	"net/http"
	"test-client/empty"
	"test-client/models"
)

type CheckForbiddenResponse struct {
	Ok        *models.Message
	Forbidden *empty.Type
}

type SameOperationNameResponse struct {
	Ok        *empty.Type
	Forbidden *empty.Type
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
		return &CheckForbiddenResponse{Forbidden: &empty.Type{}}, nil
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
