package spec_v2

import "errors"

type ServiceEcho struct{}

func (service *ServiceEcho) EchoBody(body *Message) (*EchoBodyResponse, error) {
	return nil, errors.New("implementation has not added yet")
}
