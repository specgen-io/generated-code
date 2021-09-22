package services

import (
	"./check"
	"./models"
	"cloud.google.com/go/civil"
	"errors"
	"github.com/google/uuid"
	"github.com/shopspring/decimal"
)

type CheckService struct{}

func (service *CheckService) CheckEmpty() (*check.CheckEmptyResponse, error) {
	return nil, errors.New("implementation has not added yet")
}
func (service *CheckService) CheckQuery(pString string, pStringOpt *string, pStringArray []string, pDate civil.Date, pDateArray []civil.Date, pDatetime civil.DateTime, pInt int, pLong int64, pDecimal decimal.Decimal, pEnum models.Choice, pStringDefaulted string) (*check.CheckQueryResponse, error) {
	return nil, errors.New("implementation has not added yet")
}
func (service *CheckService) CheckUrlParams(intUrl int64, stringUrl string, floatUrl float32, boolUrl bool, uuidUrl uuid.UUID, decimalUrl decimal.Decimal, dateUrl civil.Date, enumUrl models.Choice) (*check.CheckUrlParamsResponse, error) {
	return nil, errors.New("implementation has not added yet")
}
func (service *CheckService) CheckForbidden() (*check.CheckForbiddenResponse, error) {
	return nil, errors.New("implementation has not added yet")
}
func (service *CheckService) SameOperationName() (*check.SameOperationNameResponse, error) {
	return nil, errors.New("implementation has not added yet")
}
