package spec_v2

import "fmt"
import "errors"
import "io/ioutil"
import "net/http"
import "encoding/json"
import "bytes"

type echoClient struct {
	baseUrl string
}

func NewEchoClient(baseUrl string) *echoClient {
	return &echoClient{baseUrl}
}

func (client *echoClient) EchoBody(body *Message) (*EchoBodyResponse, error) {
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
