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

type ParamMessage struct {
	Name    string `json:"name"`
	Message string `json:"message"`
}

type paramMessage ParamMessage

var paramMessageRequiredFields = []string{"name", "message"}

func (obj ParamMessage) MarshalJSON() ([]byte, error) {
	data, err := json.Marshal(paramMessage(obj))
	if err != nil {
		return nil, err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	for _, name := range paramMessageRequiredFields {
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

func (obj *ParamMessage) UnmarshalJSON(data []byte) error {
	jsonObj := paramMessage(*obj)
	err := json.Unmarshal(data, &jsonObj)
	if err != nil {
		return err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	if err != nil {
		return errors.New("failed to check fields in json: " + err.Error())
	}
	for _, name := range paramMessageRequiredFields {
		value, found := rawMap[name]
		if !found {
			return errors.New("required field missing: " + name)
		}
		if string(value) == "null" {
			return errors.New("required field doesn't have value: " + name)
		}
	}
	*obj = ParamMessage(jsonObj)
	return nil
}

type BadRequestError struct {
	Message string         `json:"message"`
	Params  []ParamMessage `json:"params"`
}

type badRequestError BadRequestError

var badRequestErrorRequiredFields = []string{"message", "params"}

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
