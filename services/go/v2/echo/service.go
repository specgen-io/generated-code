package echo

import (
	"test-service/v2/models"
)

type EmptyDef struct{}

var Empty = EmptyDef{}

type Service interface {
	EchoBody(body *models.Message) (*models.Message, error)
}
