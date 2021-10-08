package echo

import (
	"bytes"
	"encoding/json"
	"errors"
	"fmt"
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
