package v2

import "errors"
import "/spec/v2"

type EchoService struct{}

func (service *EchoService) EchoBody(body *v2.Message) (*v2.EchoBodyResponse, error) {
	return nil, errors.New("implementation has not added yet")
}
