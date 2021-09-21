package check
import (
	"cloud.google.com/go/civil"
	"github.com/google/uuid"
	"github.com/shopspring/decimal"
	"spec/models"
)

type EmptyDef struct{}

var Empty = EmptyDef{}

type CheckEmptyResponse struct {
	Ok *EmptyDef
}

type CheckQueryResponse struct {
	Ok *EmptyDef
}

type CheckUrlParamsResponse struct {
	Ok *EmptyDef
}

type CheckForbiddenResponse struct {
	Ok *models.Message
	Forbidden *EmptyDef
}

type Service interface {
	CheckEmpty() (*CheckEmptyResponse, error)
	CheckQuery(pString string, pStringOpt *string, pStringArray []string, pDate civil.Date, pDateArray []civil.Date, pDatetime civil.DateTime, pInt int, pLong int64, pDecimal decimal.Decimal, pEnum models.Choice, pStringDefaulted string) (*CheckQueryResponse, error)
	CheckUrlParams(intUrl int64, stringUrl string, floatUrl float32, boolUrl bool, uuidUrl uuid.UUID, decimalUrl decimal.Decimal, dateUrl civil.Date, enumUrl models.Choice) (*CheckUrlParamsResponse, error)
	CheckForbidden() (*CheckForbiddenResponse, error)
}
