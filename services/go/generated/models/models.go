package models

import (
	"cloud.google.com/go/civil"
	"encoding/json"
	"errors"
	"github.com/google/uuid"
	"github.com/shopspring/decimal"
)

type Message struct {
	IntField    int    `json:"int_field"`
	StringField string `json:"string_field"`
}

type message Message

var messageRequiredFields = []string{"int_field", "string_field"}

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

type Choice string

const (
	ChoiceFirstChoice  Choice = "FIRST_CHOICE"
	ChoiceSecondChoice Choice = "SECOND_CHOICE"
	ChoiceThirdChoice  Choice = "THIRD_CHOICE"
)

var ChoiceValuesStrings = []string{string(ChoiceFirstChoice), string(ChoiceSecondChoice), string(ChoiceThirdChoice)}
var ChoiceValues = []Choice{ChoiceFirstChoice, ChoiceSecondChoice, ChoiceThirdChoice}

func (self *Choice) UnmarshalJSON(b []byte) error {
	str, err := readEnumStringValue(b, ChoiceValuesStrings)
	if err != nil {
		return err
	}
	*self = Choice(str)
	return nil
}

type Parameters struct {
	IntField             int             `json:"int_field"`
	LongField            int64           `json:"long_field"`
	FloatField           float32         `json:"float_field"`
	DoubleField          float64         `json:"double_field"`
	DecimalField         decimal.Decimal `json:"decimal_field"`
	BoolField            bool            `json:"bool_field"`
	StringField          string          `json:"string_field"`
	StringOptField       *string         `json:"string_opt_field,omitempty"`
	StringDefaultedField string          `json:"string_defaulted_field"`
	StringArrayField     []string        `json:"string_array_field"`
	UuidField            uuid.UUID       `json:"uuid_field"`
	DateField            civil.Date      `json:"date_field"`
	DateArrayField       []civil.Date    `json:"date_array_field"`
	DatetimeField        civil.DateTime  `json:"datetime_field"`
	EnumField            Choice          `json:"enum_field"`
}

type parameters Parameters

var parametersRequiredFields = []string{"int_field", "long_field", "float_field", "double_field", "decimal_field", "bool_field", "string_field", "string_defaulted_field", "string_array_field", "uuid_field", "date_field", "date_array_field", "datetime_field", "enum_field"}

func (obj Parameters) MarshalJSON() ([]byte, error) {
	data, err := json.Marshal(parameters(obj))
	if err != nil {
		return nil, err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	for _, name := range parametersRequiredFields {
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

func (obj *Parameters) UnmarshalJSON(data []byte) error {
	jsonObj := parameters(*obj)
	err := json.Unmarshal(data, &jsonObj)
	if err != nil {
		return err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	if err != nil {
		return errors.New("failed to check fields in json: " + err.Error())
	}
	for _, name := range parametersRequiredFields {
		value, found := rawMap[name]
		if !found {
			return errors.New("required field missing: " + name)
		}
		if string(value) == "null" {
			return errors.New("required field doesn't have value: " + name)
		}
	}
	*obj = Parameters(jsonObj)
	return nil
}

type UrlParameters struct {
	IntField      int             `json:"int_field"`
	LongField     int64           `json:"long_field"`
	FloatField    float32         `json:"float_field"`
	DoubleField   float64         `json:"double_field"`
	DecimalField  decimal.Decimal `json:"decimal_field"`
	BoolField     bool            `json:"bool_field"`
	StringField   string          `json:"string_field"`
	UuidField     uuid.UUID       `json:"uuid_field"`
	DateField     civil.Date      `json:"date_field"`
	DatetimeField civil.DateTime  `json:"datetime_field"`
	EnumField     Choice          `json:"enum_field"`
}

type urlParameters UrlParameters

var urlParametersRequiredFields = []string{"int_field", "long_field", "float_field", "double_field", "decimal_field", "bool_field", "string_field", "uuid_field", "date_field", "datetime_field", "enum_field"}

func (obj UrlParameters) MarshalJSON() ([]byte, error) {
	data, err := json.Marshal(urlParameters(obj))
	if err != nil {
		return nil, err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	for _, name := range urlParametersRequiredFields {
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

func (obj *UrlParameters) UnmarshalJSON(data []byte) error {
	jsonObj := urlParameters(*obj)
	err := json.Unmarshal(data, &jsonObj)
	if err != nil {
		return err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	if err != nil {
		return errors.New("failed to check fields in json: " + err.Error())
	}
	for _, name := range urlParametersRequiredFields {
		value, found := rawMap[name]
		if !found {
			return errors.New("required field missing: " + name)
		}
		if string(value) == "null" {
			return errors.New("required field doesn't have value: " + name)
		}
	}
	*obj = UrlParameters(jsonObj)
	return nil
}

type Everything struct {
	BodyField      Message         `json:"body_field"`
	FloatQuery     float32         `json:"float_query"`
	BoolQuery      bool            `json:"bool_query"`
	UuidHeader     uuid.UUID       `json:"uuid_header"`
	DatetimeHeader civil.DateTime  `json:"datetime_header"`
	DateUrl        civil.Date      `json:"date_url"`
	DecimalUrl     decimal.Decimal `json:"decimal_url"`
}

type everything Everything

var everythingRequiredFields = []string{"body_field", "float_query", "bool_query", "uuid_header", "datetime_header", "date_url", "decimal_url"}

func (obj Everything) MarshalJSON() ([]byte, error) {
	data, err := json.Marshal(everything(obj))
	if err != nil {
		return nil, err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	for _, name := range everythingRequiredFields {
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

func (obj *Everything) UnmarshalJSON(data []byte) error {
	jsonObj := everything(*obj)
	err := json.Unmarshal(data, &jsonObj)
	if err != nil {
		return err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	if err != nil {
		return errors.New("failed to check fields in json: " + err.Error())
	}
	for _, name := range everythingRequiredFields {
		value, found := rawMap[name]
		if !found {
			return errors.New("required field missing: " + name)
		}
		if string(value) == "null" {
			return errors.New("required field doesn't have value: " + name)
		}
	}
	*obj = Everything(jsonObj)
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
