package v2

import (
	"errors"
	"spec/v2/echo"
	"spec/v2/models"
)

type EchoService struct{}

func (service *EchoService) EchoBody(body *models.Message) (*echo.EchoBodyResponse, error) {
	return nil, errors.New("implementation has not added yet")
}
