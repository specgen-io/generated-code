package v2

type EmptyDef struct{}

var Empty = EmptyDef{}

type EchoBodyResponse struct {
	Ok *Message
}

type IEchoClient interface {
	EchoBody(body *Message) (*EchoBodyResponse, error)
}
