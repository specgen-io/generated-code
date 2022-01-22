package v2

import (
	"errors"
	"test-service/generated/v2/echo"
	"test-service/generated/v2/models"
)

type EchoService struct{}

func (service *EchoService) EchoBodyModel(body *models.Message) (*models.Message, error) {
	return nil, errors.New("implementation has not added yet")
}
