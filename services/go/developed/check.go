package developed

import (
	"errors"
	"test-service/generated/check"
	"test-service/generated/models"
)

type CheckService struct{}

func (service *CheckService) CheckEmpty() error {
	return errors.New("implementation has not added yet")
}
func (service *CheckService) CheckEmptyResponse(body *models.Message) error {
	return errors.New("implementation has not added yet")
}
func (service *CheckService) CheckForbidden() (*check.CheckForbiddenResponse, error) {
	return nil, errors.New("implementation has not added yet")
}
func (service *CheckService) SameOperationName() (*check.SameOperationNameResponse, error) {
	return nil, errors.New("implementation has not added yet")
}
