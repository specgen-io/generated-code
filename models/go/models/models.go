package models

import (
	"cloud.google.com/go/civil"
	"encoding/json"
	"errors"
	"github.com/google/uuid"
	"github.com/shopspring/decimal"
)

type Message struct {
	Field int `json:"field"`
}

type message Message

var messageRequiredFields = []string{"field"}

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

type MessageCases struct {
	SnakeCase string `json:"snake_case"`
	CamelCase string `json:"camelCase"`
}

type messageCases MessageCases

var messageCasesRequiredFields = []string{"snake_case", "camelCase"}

func (obj MessageCases) MarshalJSON() ([]byte, error) {
	data, err := json.Marshal(messageCases(obj))
	if err != nil {
		return nil, err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	for _, name := range messageCasesRequiredFields {
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

func (obj *MessageCases) UnmarshalJSON(data []byte) error {
	jsonObj := messageCases(*obj)
	err := json.Unmarshal(data, &jsonObj)
	if err != nil {
		return err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	if err != nil {
		return errors.New("failed to check fields in json: " + err.Error())
	}
	for _, name := range messageCasesRequiredFields {
		value, found := rawMap[name]
		if !found {
			return errors.New("required field missing: " + name)
		}
		if string(value) == "null" {
			return errors.New("required field doesn't have value: " + name)
		}
	}
	*obj = MessageCases(jsonObj)
	return nil
}

type Parent struct {
	Field  string  `json:"field"`
	Nested Message `json:"nested"`
}

type parent Parent

var parentRequiredFields = []string{"field", "nested"}

func (obj Parent) MarshalJSON() ([]byte, error) {
	data, err := json.Marshal(parent(obj))
	if err != nil {
		return nil, err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	for _, name := range parentRequiredFields {
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

func (obj *Parent) UnmarshalJSON(data []byte) error {
	jsonObj := parent(*obj)
	err := json.Unmarshal(data, &jsonObj)
	if err != nil {
		return err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	if err != nil {
		return errors.New("failed to check fields in json: " + err.Error())
	}
	for _, name := range parentRequiredFields {
		value, found := rawMap[name]
		if !found {
			return errors.New("required field missing: " + name)
		}
		if string(value) == "null" {
			return errors.New("required field doesn't have value: " + name)
		}
	}
	*obj = Parent(jsonObj)
	return nil
}

type Choice string

const (
	ChoiceFirstChoice  Choice = "One"
	ChoiceSecondChoice Choice = "Two"
	ChoiceThirdChoice  Choice = "Three"
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

type EnumFields struct {
	EnumField Choice `json:"enum_field"`
}

type enumFields EnumFields

var enumFieldsRequiredFields = []string{"enum_field"}

func (obj EnumFields) MarshalJSON() ([]byte, error) {
	data, err := json.Marshal(enumFields(obj))
	if err != nil {
		return nil, err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	for _, name := range enumFieldsRequiredFields {
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

func (obj *EnumFields) UnmarshalJSON(data []byte) error {
	jsonObj := enumFields(*obj)
	err := json.Unmarshal(data, &jsonObj)
	if err != nil {
		return err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	if err != nil {
		return errors.New("failed to check fields in json: " + err.Error())
	}
	for _, name := range enumFieldsRequiredFields {
		value, found := rawMap[name]
		if !found {
			return errors.New("required field missing: " + name)
		}
		if string(value) == "null" {
			return errors.New("required field doesn't have value: " + name)
		}
	}
	*obj = EnumFields(jsonObj)
	return nil
}

type NumericFields struct {
	IntField     int             `json:"int_field"`
	LongField    int64           `json:"long_field"`
	FloatField   float32         `json:"float_field"`
	DoubleField  float64         `json:"double_field"`
	DecimalField decimal.Decimal `json:"decimal_field"`
}

type numericFields NumericFields

var numericFieldsRequiredFields = []string{"int_field", "long_field", "float_field", "double_field", "decimal_field"}

func (obj NumericFields) MarshalJSON() ([]byte, error) {
	data, err := json.Marshal(numericFields(obj))
	if err != nil {
		return nil, err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	for _, name := range numericFieldsRequiredFields {
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

func (obj *NumericFields) UnmarshalJSON(data []byte) error {
	jsonObj := numericFields(*obj)
	err := json.Unmarshal(data, &jsonObj)
	if err != nil {
		return err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	if err != nil {
		return errors.New("failed to check fields in json: " + err.Error())
	}
	for _, name := range numericFieldsRequiredFields {
		value, found := rawMap[name]
		if !found {
			return errors.New("required field missing: " + name)
		}
		if string(value) == "null" {
			return errors.New("required field doesn't have value: " + name)
		}
	}
	*obj = NumericFields(jsonObj)
	return nil
}

type NonNumericFields struct {
	BooleanField  bool           `json:"boolean_field"`
	StringField   string         `json:"string_field"`
	UuidField     uuid.UUID      `json:"uuid_field"`
	DateField     civil.Date     `json:"date_field"`
	DatetimeField civil.DateTime `json:"datetime_field"`
}

type nonNumericFields NonNumericFields

var nonNumericFieldsRequiredFields = []string{"boolean_field", "string_field", "uuid_field", "date_field", "datetime_field"}

func (obj NonNumericFields) MarshalJSON() ([]byte, error) {
	data, err := json.Marshal(nonNumericFields(obj))
	if err != nil {
		return nil, err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	for _, name := range nonNumericFieldsRequiredFields {
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

func (obj *NonNumericFields) UnmarshalJSON(data []byte) error {
	jsonObj := nonNumericFields(*obj)
	err := json.Unmarshal(data, &jsonObj)
	if err != nil {
		return err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	if err != nil {
		return errors.New("failed to check fields in json: " + err.Error())
	}
	for _, name := range nonNumericFieldsRequiredFields {
		value, found := rawMap[name]
		if !found {
			return errors.New("required field missing: " + name)
		}
		if string(value) == "null" {
			return errors.New("required field doesn't have value: " + name)
		}
	}
	*obj = NonNumericFields(jsonObj)
	return nil
}

type ArrayFields struct {
	IntArrayField    []int    `json:"int_array_field"`
	StringArrayField []string `json:"string_array_field"`
}

type arrayFields ArrayFields

var arrayFieldsRequiredFields = []string{"int_array_field", "string_array_field"}

func (obj ArrayFields) MarshalJSON() ([]byte, error) {
	data, err := json.Marshal(arrayFields(obj))
	if err != nil {
		return nil, err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	for _, name := range arrayFieldsRequiredFields {
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

func (obj *ArrayFields) UnmarshalJSON(data []byte) error {
	jsonObj := arrayFields(*obj)
	err := json.Unmarshal(data, &jsonObj)
	if err != nil {
		return err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	if err != nil {
		return errors.New("failed to check fields in json: " + err.Error())
	}
	for _, name := range arrayFieldsRequiredFields {
		value, found := rawMap[name]
		if !found {
			return errors.New("required field missing: " + name)
		}
		if string(value) == "null" {
			return errors.New("required field doesn't have value: " + name)
		}
	}
	*obj = ArrayFields(jsonObj)
	return nil
}

type MapFields struct {
	IntMapField    map[string]int    `json:"int_map_field"`
	StringMapField map[string]string `json:"string_map_field"`
}

type mapFields MapFields

var mapFieldsRequiredFields = []string{"int_map_field", "string_map_field"}

func (obj MapFields) MarshalJSON() ([]byte, error) {
	data, err := json.Marshal(mapFields(obj))
	if err != nil {
		return nil, err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	for _, name := range mapFieldsRequiredFields {
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

func (obj *MapFields) UnmarshalJSON(data []byte) error {
	jsonObj := mapFields(*obj)
	err := json.Unmarshal(data, &jsonObj)
	if err != nil {
		return err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	if err != nil {
		return errors.New("failed to check fields in json: " + err.Error())
	}
	for _, name := range mapFieldsRequiredFields {
		value, found := rawMap[name]
		if !found {
			return errors.New("required field missing: " + name)
		}
		if string(value) == "null" {
			return errors.New("required field doesn't have value: " + name)
		}
	}
	*obj = MapFields(jsonObj)
	return nil
}

type OptionalFields struct {
	IntOptionField    *int    `json:"int_option_field,omitempty"`
	StringOptionField *string `json:"string_option_field,omitempty"`
}

type optionalFields OptionalFields

var optionalFieldsRequiredFields = []string{}

func (obj OptionalFields) MarshalJSON() ([]byte, error) {
	data, err := json.Marshal(optionalFields(obj))
	if err != nil {
		return nil, err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	for _, name := range optionalFieldsRequiredFields {
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

func (obj *OptionalFields) UnmarshalJSON(data []byte) error {
	jsonObj := optionalFields(*obj)
	err := json.Unmarshal(data, &jsonObj)
	if err != nil {
		return err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	if err != nil {
		return errors.New("failed to check fields in json: " + err.Error())
	}
	for _, name := range optionalFieldsRequiredFields {
		value, found := rawMap[name]
		if !found {
			return errors.New("required field missing: " + name)
		}
		if string(value) == "null" {
			return errors.New("required field doesn't have value: " + name)
		}
	}
	*obj = OptionalFields(jsonObj)
	return nil
}

type RawJsonField struct {
	JsonField json.RawMessage `json:"json_field"`
}

type rawJsonField RawJsonField

var rawJsonFieldRequiredFields = []string{"json_field"}

func (obj RawJsonField) MarshalJSON() ([]byte, error) {
	data, err := json.Marshal(rawJsonField(obj))
	if err != nil {
		return nil, err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	for _, name := range rawJsonFieldRequiredFields {
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

func (obj *RawJsonField) UnmarshalJSON(data []byte) error {
	jsonObj := rawJsonField(*obj)
	err := json.Unmarshal(data, &jsonObj)
	if err != nil {
		return err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	if err != nil {
		return errors.New("failed to check fields in json: " + err.Error())
	}
	for _, name := range rawJsonFieldRequiredFields {
		value, found := rawMap[name]
		if !found {
			return errors.New("required field missing: " + name)
		}
		if string(value) == "null" {
			return errors.New("required field doesn't have value: " + name)
		}
	}
	*obj = RawJsonField(jsonObj)
	return nil
}

type OrderCreated struct {
	Id       uuid.UUID `json:"id"`
	Sku      string    `json:"sku"`
	Quantity int       `json:"quantity"`
}

type orderCreated OrderCreated

var orderCreatedRequiredFields = []string{"id", "sku", "quantity"}

func (obj OrderCreated) MarshalJSON() ([]byte, error) {
	data, err := json.Marshal(orderCreated(obj))
	if err != nil {
		return nil, err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	for _, name := range orderCreatedRequiredFields {
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

func (obj *OrderCreated) UnmarshalJSON(data []byte) error {
	jsonObj := orderCreated(*obj)
	err := json.Unmarshal(data, &jsonObj)
	if err != nil {
		return err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	if err != nil {
		return errors.New("failed to check fields in json: " + err.Error())
	}
	for _, name := range orderCreatedRequiredFields {
		value, found := rawMap[name]
		if !found {
			return errors.New("required field missing: " + name)
		}
		if string(value) == "null" {
			return errors.New("required field doesn't have value: " + name)
		}
	}
	*obj = OrderCreated(jsonObj)
	return nil
}

type OrderChanged struct {
	Id       uuid.UUID `json:"id"`
	Quantity int       `json:"quantity"`
}

type orderChanged OrderChanged

var orderChangedRequiredFields = []string{"id", "quantity"}

func (obj OrderChanged) MarshalJSON() ([]byte, error) {
	data, err := json.Marshal(orderChanged(obj))
	if err != nil {
		return nil, err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	for _, name := range orderChangedRequiredFields {
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

func (obj *OrderChanged) UnmarshalJSON(data []byte) error {
	jsonObj := orderChanged(*obj)
	err := json.Unmarshal(data, &jsonObj)
	if err != nil {
		return err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	if err != nil {
		return errors.New("failed to check fields in json: " + err.Error())
	}
	for _, name := range orderChangedRequiredFields {
		value, found := rawMap[name]
		if !found {
			return errors.New("required field missing: " + name)
		}
		if string(value) == "null" {
			return errors.New("required field doesn't have value: " + name)
		}
	}
	*obj = OrderChanged(jsonObj)
	return nil
}

type OrderCanceled struct {
	Id uuid.UUID `json:"id"`
}

type orderCanceled OrderCanceled

var orderCanceledRequiredFields = []string{"id"}

func (obj OrderCanceled) MarshalJSON() ([]byte, error) {
	data, err := json.Marshal(orderCanceled(obj))
	if err != nil {
		return nil, err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	for _, name := range orderCanceledRequiredFields {
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

func (obj *OrderCanceled) UnmarshalJSON(data []byte) error {
	jsonObj := orderCanceled(*obj)
	err := json.Unmarshal(data, &jsonObj)
	if err != nil {
		return err
	}
	var rawMap map[string]json.RawMessage
	err = json.Unmarshal(data, &rawMap)
	if err != nil {
		return errors.New("failed to check fields in json: " + err.Error())
	}
	for _, name := range orderCanceledRequiredFields {
		value, found := rawMap[name]
		if !found {
			return errors.New("required field missing: " + name)
		}
		if string(value) == "null" {
			return errors.New("required field doesn't have value: " + name)
		}
	}
	*obj = OrderCanceled(jsonObj)
	return nil
}

type OrderEventWrapper struct {
	Created  *OrderCreated  `json:"created,omitempty"`
	Changed  *OrderChanged  `json:"changed,omitempty"`
	Canceled *OrderCanceled `json:"canceled,omitempty"`
}

type orderEventWrapper OrderEventWrapper

func (u OrderEventWrapper) MarshalJSON() ([]byte, error) {
	if u.Created == nil && u.Changed == nil && u.Canceled == nil {
		return nil, errors.New("union case is not set")
	}
	return json.Marshal(orderEventWrapper(u))
}

func (u *OrderEventWrapper) UnmarshalJSON(data []byte) error {
	jsonObj := orderEventWrapper(*u)
	err := json.Unmarshal(data, &jsonObj)
	if err != nil {
		return err
	}
	*u = OrderEventWrapper(jsonObj)
	if u.Created == nil && u.Changed == nil && u.Canceled == nil {
		return errors.New("union case is not set")
	}
	return nil
}

type OrderEventDiscriminator struct {
	Created  *OrderCreated
	Changed  *OrderChanged
	Canceled *OrderCanceled
}

func (u OrderEventDiscriminator) MarshalJSON() ([]byte, error) {
	if u.Created != nil {
		data, err := json.Marshal(u.Created)
		if err != nil {
			return nil, err
		}
		var rawMap map[string]json.RawMessage
		json.Unmarshal(data, &rawMap)
		rawMap["_type"] = []byte(`"created"`)
		return json.Marshal(rawMap)
	}
	if u.Changed != nil {
		data, err := json.Marshal(u.Changed)
		if err != nil {
			return nil, err
		}
		var rawMap map[string]json.RawMessage
		json.Unmarshal(data, &rawMap)
		rawMap["_type"] = []byte(`"changed"`)
		return json.Marshal(rawMap)
	}
	if u.Canceled != nil {
		data, err := json.Marshal(u.Canceled)
		if err != nil {
			return nil, err
		}
		var rawMap map[string]json.RawMessage
		json.Unmarshal(data, &rawMap)
		rawMap["_type"] = []byte(`"canceled"`)
		return json.Marshal(rawMap)
	}
	return nil, errors.New("union case is not set")
}

func (u *OrderEventDiscriminator) UnmarshalJSON(data []byte) error {
	var discriminator struct {
		Value *string `json:"_type"`
	}
	err := json.Unmarshal(data, &discriminator)
	if err != nil {
		return errors.New("failed to parse discriminator field _type: " + err.Error())
	}
	if discriminator.Value == nil {
		return errors.New("discriminator field _type not found")
	}

	switch *discriminator.Value {
	case "created":
		unionCase := OrderCreated{}
		err := json.Unmarshal(data, &unionCase)
		if err != nil {
			return err
		}
		u.Created = &unionCase
	case "changed":
		unionCase := OrderChanged{}
		err := json.Unmarshal(data, &unionCase)
		if err != nil {
			return err
		}
		u.Changed = &unionCase
	case "canceled":
		unionCase := OrderCanceled{}
		err := json.Unmarshal(data, &unionCase)
		if err != nil {
			return err
		}
		u.Canceled = &unionCase
	default:
		return errors.New("unexpected union discriminator field _type value: " + *discriminator.Value)
	}
	return nil
}
