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

type EmptyDef struct{}

var Empty = EmptyDef{}

type EchoBodyResponse struct {
	Ok *models.Message
}

type Client struct {
	baseUrl string
}

func NewClient(baseUrl string) *Client {
	return &Client{baseUrl}
}

var logEchoBody = log.Fields{"operationId": "echo.echo_body", "method": "POST", "url": "/v2/echo/body"}
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
