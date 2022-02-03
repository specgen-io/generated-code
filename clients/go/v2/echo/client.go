package echo

import (
	"bytes"
	"encoding/json"
	"errors"
	"fmt"
	log "github.com/sirupsen/logrus"
	"io/ioutil"
	"net/http"
	"test-client/v2/models"
)

type Client struct {
	baseUrl string
}

func NewClient(baseUrl string) *Client {
	return &Client{baseUrl}
}

var logEchoBodyModel = log.Fields{"operationId": "echo.echo_body_model", "method": "POST", "url": "/v2/echo/body_model"}
func (client *Client) EchoBodyModel(body *models.Message) (*models.Message, error) {
	bodyData, err := json.Marshal(body)
	req, err := http.NewRequest("POST", client.baseUrl+"/v2/echo/body_model", bytes.NewBuffer(bodyData))
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
		if err != nil {
			log.WithFields(logEchoBodyModel).Error("Reading request body failed", err.Error())
			return nil, err
		}
		result := string(responseBody)
		return &result, nil
	}

	msg := fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode)
	log.WithFields(logEchoBodyModel).Error(msg)
	err = errors.New(msg)
	return nil, err
}
