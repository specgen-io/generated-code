package v2

import (
	"errors"
	"v2/echo"
	"v2/models"
)

type EchoService struct{}

func (service *EchoService) EchoBody(body *models.Message) (*echo.EchoBodyResponse, error) {
	return nil, errors.New("implementation has not added yet")
}
