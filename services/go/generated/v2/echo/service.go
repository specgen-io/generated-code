package echo

import (
	"test-service/generated/v2/models"
)

type Service interface {
	EchoBodyModel(body *models.Message) (*models.Message, error)
}
