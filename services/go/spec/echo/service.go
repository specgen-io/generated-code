package echo

import (
	"spec/models"
)

type EmptyDef struct{}

var Empty = EmptyDef{}

type EchoBodyResponse struct {
	Ok *models.Message
}

type EchoQueryResponse struct {
	Ok *models.Message
}

type EchoHeaderResponse struct {
	Ok *models.Message
}

type EchoUrlParamsResponse struct {
	Ok *models.Message
}

type SameOperationNameResponse struct {
	Ok *EmptyDef
}

type Service interface {
	EchoBody(body *models.Message) (*EchoBodyResponse, error)
	EchoQuery(intQuery int, stringQuery string) (*EchoQueryResponse, error)
	EchoHeader(intHeader int, stringHeader string) (*EchoHeaderResponse, error)
	EchoUrlParams(intUrl int, stringUrl string) (*EchoUrlParamsResponse, error)
	SameOperationName() (*SameOperationNameResponse, error)
}
