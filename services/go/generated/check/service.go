package check

import (
	"test-service/empty"
	"test-service/generated/models"
)

type CheckForbiddenResponse struct {
	Ok *models.Message
	Forbidden *empty.Type
}

type SameOperationNameResponse struct {
	Ok *empty.Type
	Forbidden *empty.Type
}

type Service interface {
	CheckEmpty() error
	CheckEmptyResponse(body *models.Message) error
	CheckForbidden() (*CheckForbiddenResponse, error)
	SameOperationName() (*SameOperationNameResponse, error)
}
