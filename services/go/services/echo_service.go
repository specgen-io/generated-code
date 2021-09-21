package services

import "errors"
import "/spec"

type EchoService struct{}

func (service *EchoService) EchoBody(body *spec.models.Message) (*spec.EchoBodyResponse, error) {
	return nil, errors.New("implementation has not added yet")
}
func (service *EchoService) EchoQuery(intQuery int, stringQuery string) (*spec.EchoQueryResponse, error) {
	return nil, errors.New("implementation has not added yet")
}
func (service *EchoService) EchoHeader(intHeader int, stringHeader string) (*spec.EchoHeaderResponse, error) {
	return nil, errors.New("implementation has not added yet")
}
func (service *EchoService) EchoUrlParams(intUrl int, stringUrl string) (*spec.EchoUrlParamsResponse, error) {
	return nil, errors.New("implementation has not added yet")
}
