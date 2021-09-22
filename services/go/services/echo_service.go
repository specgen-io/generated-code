package services

import (
	"./echo"
	"./models"
	"errors"
)

type EchoService struct{}

func (service *EchoService) EchoBody(body *models.Message) (*echo.EchoBodyResponse, error) {
	return nil, errors.New("implementation has not added yet")
}
func (service *EchoService) EchoQuery(intQuery int, stringQuery string) (*echo.EchoQueryResponse, error) {
	return nil, errors.New("implementation has not added yet")
}
func (service *EchoService) EchoHeader(intHeader int, stringHeader string) (*echo.EchoHeaderResponse, error) {
	return nil, errors.New("implementation has not added yet")
}
func (service *EchoService) EchoUrlParams(intUrl int, stringUrl string) (*echo.EchoUrlParamsResponse, error) {
	return nil, errors.New("implementation has not added yet")
}
func (service *EchoService) SameOperationName() (*echo.SameOperationNameResponse, error) {
	return nil, errors.New("implementation has not added yet")
}
