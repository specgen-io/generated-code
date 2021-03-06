package echo

import (
	"cloud.google.com/go/civil"
	"github.com/google/uuid"
	"github.com/shopspring/decimal"
	"test-service/generated/empty"
	"test-service/generated/models"
)

type EchoEverythingResponse struct {
	Ok        *models.Everything
	Forbidden *empty.Type
}

type SameOperationNameResponse struct {
	Ok        *empty.Type
	Forbidden *empty.Type
}

type Service interface {
	EchoBodyString(body string) (*string, error)
	EchoBodyModel(body *models.Message) (*models.Message, error)
	EchoBodyArray(body *[]string) (*[]string, error)
	EchoBodyMap(body *map[string]string) (*map[string]string, error)
	EchoQuery(intQuery int, longQuery int64, floatQuery float32, doubleQuery float64, decimalQuery decimal.Decimal, boolQuery bool, stringQuery string, stringOptQuery *string, stringDefaultedQuery string, stringArrayQuery []string, uuidQuery uuid.UUID, dateQuery civil.Date, dateArrayQuery []civil.Date, datetimeQuery civil.DateTime, enumQuery models.Choice) (*models.Parameters, error)
	EchoHeader(intHeader int, longHeader int64, floatHeader float32, doubleHeader float64, decimalHeader decimal.Decimal, boolHeader bool, stringHeader string, stringOptHeader *string, stringDefaultedHeader string, stringArrayHeader []string, uuidHeader uuid.UUID, dateHeader civil.Date, dateArrayHeader []civil.Date, datetimeHeader civil.DateTime, enumHeader models.Choice) (*models.Parameters, error)
	EchoUrlParams(intUrl int, longUrl int64, floatUrl float32, doubleUrl float64, decimalUrl decimal.Decimal, boolUrl bool, stringUrl string, uuidUrl uuid.UUID, dateUrl civil.Date, datetimeUrl civil.DateTime, enumUrl models.Choice) (*models.UrlParameters, error)
	EchoEverything(body *models.Message, floatQuery float32, boolQuery bool, uuidHeader uuid.UUID, datetimeHeader civil.DateTime, dateUrl civil.Date, decimalUrl decimal.Decimal) (*EchoEverythingResponse, error)
	SameOperationName() (*SameOperationNameResponse, error)
}
