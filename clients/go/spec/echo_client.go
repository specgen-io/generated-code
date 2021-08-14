package spec

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

func (client *echoClient) EchoQuery(intQuery int, stringQuery string) (*EchoQueryResponse, error) {
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

		var body *Message
		err = json.Unmarshal(responseBody, &body)
		if err != nil { return nil, err }

		return &EchoQueryResponse{Ok: body}, nil
	}

	return nil, errors.New(fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode))
}

func (client *echoClient) EchoHeader(intHeader int, stringHeader string) (*EchoHeaderResponse, error) {
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

		var body *Message
		err = json.Unmarshal(responseBody, &body)
		if err != nil { return nil, err }

		return &EchoHeaderResponse{Ok: body}, nil
	}

	return nil, errors.New(fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode))
}

func (client *echoClient) EchoUrlParams(intUrl int, stringUrl string) (*EchoUrlParamsResponse, error) {
	req, err := http.NewRequest("GET", client.baseUrl+fmt.Sprintf("/echo/url_params/%s/%s", convertInt(intUrl), stringUrl), nil)
	if err != nil { return nil, err }

	resp, err := http.DefaultClient.Do(req)
	if err != nil { return nil, err }

	if resp.StatusCode == 200 {
		responseBody, err := ioutil.ReadAll(resp.Body)
		err = resp.Body.Close()

		var body *Message
		err = json.Unmarshal(responseBody, &body)
		if err != nil { return nil, err }

		return &EchoUrlParamsResponse{Ok: body}, nil
	}

	return nil, errors.New(fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode))
}
