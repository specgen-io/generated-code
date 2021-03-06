package models

import (
	"encoding/json"
	"errors"
)

type Message struct {
	BoolField   bool   `json:"bool_field"`
	StringField string `json:"string_field"`
}

type message Message

var messageRequiredFields = []string{"bool_field", "string_field"}

func (obj Message) MarshalJSON() ([]byte, error) {
	data, err := json.Marshal(message(obj))
	if err != nil {
		return nil, err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	for _, name := range messageRequiredFields {
		value, found := rawMap[name]
		if !found {
			return nil, errors.New("required field missing: " + name)
		}
		if string(value) == "null" {
			return nil, errors.New("required field doesn't have value: " + name)
		}
	}
	return data, nil
}

func (obj *Message) UnmarshalJSON(data []byte) error {
	jsonObj := message(*obj)
	err := json.Unmarshal(data, &jsonObj)
	if err != nil {
		return err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	if err != nil {
		return errors.New("failed to check fields in json: " + err.Error())
	}
	for _, name := range messageRequiredFields {
		value, found := rawMap[name]
		if !found {
			return errors.New("required field missing: " + name)
		}
		if string(value) == "null" {
			return errors.New("required field doesn't have value: " + name)
		}
	}
	*obj = Message(jsonObj)
	return nil
}

type ErrorLocation string

const (
	ErrorLocationQuery  ErrorLocation = "query"
	ErrorLocationHeader ErrorLocation = "header"
	ErrorLocationBody   ErrorLocation = "body"
)

var ErrorLocationValuesStrings = []string{string(ErrorLocationQuery), string(ErrorLocationHeader), string(ErrorLocationBody)}
var ErrorLocationValues = []ErrorLocation{ErrorLocationQuery, ErrorLocationHeader, ErrorLocationBody}

func (self *ErrorLocation) UnmarshalJSON(b []byte) error {
	str, err := readEnumStringValue(b, ErrorLocationValuesStrings)
	if err != nil {
		return err
	}
	*self = ErrorLocation(str)
	return nil
}

type ValidationError struct {
	Path    string  `json:"path"`
	Code    string  `json:"code"`
	Message *string `json:"message,omitempty"`
}

type validationError ValidationError

var validationErrorRequiredFields = []string{"path", "code"}

func (obj ValidationError) MarshalJSON() ([]byte, error) {
	data, err := json.Marshal(validationError(obj))
	if err != nil {
		return nil, err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	for _, name := range validationErrorRequiredFields {
		value, found := rawMap[name]
		if !found {
			return nil, errors.New("required field missing: " + name)
		}
		if string(value) == "null" {
			return nil, errors.New("required field doesn't have value: " + name)
		}
	}
	return data, nil
}

func (obj *ValidationError) UnmarshalJSON(data []byte) error {
	jsonObj := validationError(*obj)
	err := json.Unmarshal(data, &jsonObj)
	if err != nil {
		return err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	if err != nil {
		return errors.New("failed to check fields in json: " + err.Error())
	}
	for _, name := range validationErrorRequiredFields {
		value, found := rawMap[name]
		if !found {
			return errors.New("required field missing: " + name)
		}
		if string(value) == "null" {
			return errors.New("required field doesn't have value: " + name)
		}
	}
	*obj = ValidationError(jsonObj)
	return nil
}

type BadRequestError struct {
	Message  string            `json:"message"`
	Location ErrorLocation     `json:"location"`
	Errors   []ValidationError `json:"errors,omitempty"`
}

type badRequestError BadRequestError

var badRequestErrorRequiredFields = []string{"message", "location"}

func (obj BadRequestError) MarshalJSON() ([]byte, error) {
	data, err := json.Marshal(badRequestError(obj))
	if err != nil {
		return nil, err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	for _, name := range badRequestErrorRequiredFields {
		value, found := rawMap[name]
		if !found {
			return nil, errors.New("required field missing: " + name)
		}
		if string(value) == "null" {
			return nil, errors.New("required field doesn't have value: " + name)
		}
	}
	return data, nil
}

func (obj *BadRequestError) UnmarshalJSON(data []byte) error {
	jsonObj := badRequestError(*obj)
	err := json.Unmarshal(data, &jsonObj)
	if err != nil {
		return err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	if err != nil {
		return errors.New("failed to check fields in json: " + err.Error())
	}
	for _, name := range badRequestErrorRequiredFields {
		value, found := rawMap[name]
		if !found {
			return errors.New("required field missing: " + name)
		}
		if string(value) == "null" {
			return errors.New("required field doesn't have value: " + name)
		}
	}
	*obj = BadRequestError(jsonObj)
	return nil
}

type NotFoundError struct {
	Message string `json:"message"`
}

type notFoundError NotFoundError

var notFoundErrorRequiredFields = []string{"message"}

func (obj NotFoundError) MarshalJSON() ([]byte, error) {
	data, err := json.Marshal(notFoundError(obj))
	if err != nil {
		return nil, err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	for _, name := range notFoundErrorRequiredFields {
		value, found := rawMap[name]
		if !found {
			return nil, errors.New("required field missing: " + name)
		}
		if string(value) == "null" {
			return nil, errors.New("required field doesn't have value: " + name)
		}
	}
	return data, nil
}

func (obj *NotFoundError) UnmarshalJSON(data []byte) error {
	jsonObj := notFoundError(*obj)
	err := json.Unmarshal(data, &jsonObj)
	if err != nil {
		return err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	if err != nil {
		return errors.New("failed to check fields in json: " + err.Error())
	}
	for _, name := range notFoundErrorRequiredFields {
		value, found := rawMap[name]
		if !found {
			return errors.New("required field missing: " + name)
		}
		if string(value) == "null" {
			return errors.New("required field doesn't have value: " + name)
		}
	}
	*obj = NotFoundError(jsonObj)
	return nil
}

type InternalServerError struct {
	Message string `json:"message"`
}

type internalServerError InternalServerError

var internalServerErrorRequiredFields = []string{"message"}

func (obj InternalServerError) MarshalJSON() ([]byte, error) {
	data, err := json.Marshal(internalServerError(obj))
	if err != nil {
		return nil, err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	for _, name := range internalServerErrorRequiredFields {
		value, found := rawMap[name]
		if !found {
			return nil, errors.New("required field missing: " + name)
		}
		if string(value) == "null" {
			return nil, errors.New("required field doesn't have value: " + name)
		}
	}
	return data, nil
}

func (obj *InternalServerError) UnmarshalJSON(data []byte) error {
	jsonObj := internalServerError(*obj)
	err := json.Unmarshal(data, &jsonObj)
	if err != nil {
		return err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	if err != nil {
		return errors.New("failed to check fields in json: " + err.Error())
	}
	for _, name := range internalServerErrorRequiredFields {
		value, found := rawMap[name]
		if !found {
			return errors.New("required field missing: " + name)
		}
		if string(value) == "null" {
			return errors.New("required field doesn't have value: " + name)
		}
	}
	*obj = InternalServerError(jsonObj)
	return nil
}
