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

var logEchoBody = log.Fields{"operationId": "echo.echo_body", "method": "POST", "url": "/v2/echo/body"}
func (client *Client) EchoBody(body *models.Message) (*models.Message, error) {
	bodyData, err := json.Marshal(body)
	req, err := http.NewRequest("POST", client.baseUrl+"/v2/echo/body", bytes.NewBuffer(bodyData))
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

		var result models.Message
		err = json.Unmarshal(responseBody, &result)
		if err != nil {
			log.WithFields(logEchoBody).Error("Failed to parse response JSON", err.Error())
			return nil, err
		}
		return &result, nil
	}

	msg := fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode)
	log.WithFields(logEchoBody).Error(msg)
	err = errors.New(msg)
	return nil, err
}
