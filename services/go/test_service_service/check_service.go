package test_service_service

import "errors"
import  "cloud.google.com/go/civil"
import "github.com/google/uuid"
import "github.com/shopspring/decimal"

type ServiceCheck struct{}

func (service *ServiceCheck) CheckEmpty() (*CheckEmptyResponse, error) {
	return nil, errors.New("implementation has not added yet")
}
func (service *ServiceCheck) CheckQuery(pString string, pStringOpt *string, pStringArray []string, pDate civil.Date, pDateArray []civil.Date, pDatetime civil.DateTime, pInt int, pLong int64, pDecimal decimal.Decimal, pEnum Choice, pStringDefaulted string) (*CheckQueryResponse, error) {
	return nil, errors.New("implementation has not added yet")
}
func (service *ServiceCheck) CheckUrlParams(intUrl int64, stringUrl string, floatUrl float32, boolUrl bool, uuidUrl uuid.UUID, decimalUrl decimal.Decimal, dateUrl civil.Date) (*CheckUrlParamsResponse, error) {
	return nil, errors.New("implementation has not added yet")
}
func (service *ServiceCheck) CheckForbidden() (*CheckForbiddenResponse, error) {
	return nil, errors.New("implementation has not added yet")
}
