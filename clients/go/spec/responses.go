package spec

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
