package spec

import "errors"

type ServiceEcho struct{}

func (service *ServiceEcho) EchoBody(body *Message) (*EchoBodyResponse, error) {
	return nil, errors.New("implementation has not added yet")
}
func (service *ServiceEcho) EchoQuery(intQuery int, stringQuery string) (*EchoQueryResponse, error) {
	return nil, errors.New("implementation has not added yet")
}
func (service *ServiceEcho) EchoHeader(intHeader int, stringHeader string) (*EchoHeaderResponse, error) {
	return nil, errors.New("implementation has not added yet")
}
func (service *ServiceEcho) EchoUrlParams(intUrl int, stringUrl string) (*EchoUrlParamsResponse, error) {
	return nil, errors.New("implementation has not added yet")
}
