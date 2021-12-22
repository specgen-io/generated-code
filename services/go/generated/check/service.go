package check

import (
	"test-service/generated/models"
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
	CheckEmpty() error
	CheckForbidden() (*CheckForbiddenResponse, error)
	SameOperationName() (*SameOperationNameResponse, error)
}
