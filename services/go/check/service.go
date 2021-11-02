package check

import (
	"cloud.google.com/go/civil"
	"github.com/google/uuid"
	"github.com/shopspring/decimal"
	"test-service/models"
)

type EmptyDef struct{}

var Empty = EmptyDef{}

type CheckForbiddenResponse struct {
	Ok *models.Message
	Forbidden *EmptyDef
}

type SameOperationNameResponse struct {
	Ok *EmptyDef
	Forbidden *EmptyDef
}

type Service interface {
	CheckEmpty() (*EmptyDef, error)
	CheckQuery(pString string, pStringOpt *string, pStringArray []string, pDate civil.Date, pDateArray []civil.Date, pDatetime civil.DateTime, pInt int, pLong int64, pDecimal decimal.Decimal, pEnum models.Choice, pStringDefaulted string) (*EmptyDef, error)
	CheckUrlParams(intUrl int64, stringUrl string, floatUrl float32, boolUrl bool, uuidUrl uuid.UUID, decimalUrl decimal.Decimal, dateUrl civil.Date, enumUrl models.Choice) (*EmptyDef, error)
	CheckForbidden() (*CheckForbiddenResponse, error)
	SameOperationName() (*SameOperationNameResponse, error)
}
