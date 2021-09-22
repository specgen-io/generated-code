package echo

import (
	"spec/v2/models"
)

type EmptyDef struct{}

var Empty = EmptyDef{}

type EchoBodyResponse struct {
	Ok *models.Message
}

type Service interface {
	EchoBody(body *models.Message) (*EchoBodyResponse, error)
}
