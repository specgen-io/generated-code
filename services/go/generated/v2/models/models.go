package models

import (
	"encoding/json"
	"errors"
)

type Message struct {
	BoolField bool `json:"bool_field"`
	StringField string `json:"string_field"`
}

type message Message

var messageRequiredFields = []string{"bool_field", "string_field"}

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
		if _, found := rawMap[name]; !found {
			return errors.New("required field missing: " + name)
		}
	}
	*obj = Message(jsonObj)
	return nil
}
