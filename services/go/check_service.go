package main

import "errors"
import "cloud.google.com/go/civil"
import "github.com/google/uuid"
import "github.com/shopspring/decimal"
import "/spec"

type CheckService struct{}

func (service *CheckService) CheckEmpty() (*spec.CheckEmptyResponse, error) {
	return nil, errors.New("implementation has not added yet")
}
func (service *CheckService) CheckQuery(pString string, pStringOpt *string, pStringArray []string, pDate civil.Date, pDateArray []civil.Date, pDatetime civil.DateTime, pInt int, pLong int64, pDecimal decimal.Decimal, pEnum spec.Choice, pStringDefaulted string) (*spec.CheckQueryResponse, error) {
	return nil, errors.New("implementation has not added yet")
}
func (service *CheckService) CheckUrlParams(intUrl int64, stringUrl string, floatUrl float32, boolUrl bool, uuidUrl uuid.UUID, decimalUrl decimal.Decimal, dateUrl civil.Date) (*spec.CheckUrlParamsResponse, error) {
	return nil, errors.New("implementation has not added yet")
}
func (service *CheckService) CheckForbidden() (*spec.CheckForbiddenResponse, error) {
	return nil, errors.New("implementation has not added yet")
}
