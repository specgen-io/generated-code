package echo

import (
	"test-service/models"
)

type EmptyDef struct{}

var Empty = EmptyDef{}

type SameOperationNameResponse struct {
	Ok *EmptyDef
	Forbidden *EmptyDef
}

type Service interface {
	EchoBody(body *models.Message) (*models.Message, error)
	EchoQuery(intQuery int, stringQuery string) (*models.Message, error)
	EchoHeader(intHeader int, stringHeader string) (*models.Message, error)
	EchoUrlParams(intUrl int, stringUrl string) (*models.Message, error)
	SameOperationName() (*SameOperationNameResponse, error)
}
