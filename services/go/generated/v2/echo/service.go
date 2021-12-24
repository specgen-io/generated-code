package echo

import (
	"test-service/generated/v2/models"
)

type Service interface {
	EchoBody(body *models.Message) (*models.Message, error)
}
