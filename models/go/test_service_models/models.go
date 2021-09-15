package test_service_models

import "cloud.google.com/go/civil"
import "encoding/json"
import "github.com/google/uuid"
import "github.com/shopspring/decimal"
import "errors"
import "fmt"

type Message struct {
	Field int `json:"field"`
}

type Nested struct {
	Field string `json:"field"`
}

type Parent struct {
	Field string `json:"field"`
	Nested Nested `json:"nested"`
}

type Choice string

const (
	ChoiceFirstChoice Choice = "FIRST_CHOICE"
	ChoiceSecondChoice Choice = "SECOND_CHOICE"
	ChoiceThirdChoice Choice = "THIRD_CHOICE"
)

var ChoiceValuesStrings = []string{string(ChoiceFirstChoice), string(ChoiceSecondChoice), string(ChoiceThirdChoice)}
var ChoiceValues = []Choice{ChoiceFirstChoice, ChoiceSecondChoice, ChoiceThirdChoice}

func (self *Choice) UnmarshalJSON(b []byte) error {
	str, err := readEnumStringValue(b, ChoiceValuesStrings)
	if err != nil { return err }
	*self = Choice(str)
	return nil
}

type EnumFields struct {
	EnumField Choice `json:"enum_field"`
}

type NumericFields struct {
	IntField int `json:"int_field"`
	LongField int64 `json:"long_field"`
	FloatField float32 `json:"float_field"`
	DoubleField float64 `json:"double_field"`
	DecimalField decimal.Decimal `json:"decimal_field"`
}

type NonNumericFields struct {
	BooleanField bool `json:"boolean_field"`
	StringField string `json:"string_field"`
	UuidField uuid.UUID `json:"uuid_field"`
	DateField civil.Date `json:"date_field"`
	DatetimeField civil.DateTime `json:"datetime_field"`
}

type ArrayFields struct {
	IntArrayField []int `json:"int_array_field"`
	StringArrayField []string `json:"string_array_field"`
}

type MapFields struct {
	IntMapField map[string]int `json:"int_map_field"`
	StringMapField map[string]string `json:"string_map_field"`
}

type OptionalFields struct {
	IntOptionField *int `json:"int_option_field"`
	StringOptionField *string `json:"string_option_field"`
}

type RawJsonField struct {
	JsonField json.RawMessage `json:"json_field"`
}

type OrderCreated struct {
	Id uuid.UUID `json:"id"`
	Sku string `json:"sku"`
	Quantity int `json:"quantity"`
}

type OrderChanged struct {
	Id uuid.UUID `json:"id"`
	Quantity int `json:"quantity"`
}

type OrderCanceled struct {
	Id uuid.UUID `json:"id"`
}

type OrderEvent struct {
	Created *OrderCreated `json:"created,omitempty"`
	Changed *OrderChanged `json:"changed,omitempty"`
	Canceled *OrderCanceled `json:"canceled,omitempty"`
}

type OrderEventDiscriminated struct {
	Created *OrderCreated `json:"created,omitempty"`
	Changed *OrderChanged `json:"changed,omitempty"`
	Canceled *OrderCanceled `json:"canceled,omitempty"`
}

func (u OrderEventDiscriminated) MarshalJSON() ([]byte, error) {
	if u.Created != nil {
		return json.Marshal(&struct {
			Discriminator string `json:"_type"`
			*OrderCreated
		}{
			Discriminator: "created",
			OrderCreated: u.Created,
		})
	}
	if u.Changed != nil {
		return json.Marshal(&struct {
			Discriminator string `json:"_type"`
			*OrderChanged
		}{
			Discriminator: "changed",
			OrderChanged: u.Changed,
		})
	}
	if u.Canceled != nil {
		return json.Marshal(&struct {
			Discriminator string `json:"_type"`
			*OrderCanceled
		}{
			Discriminator: "canceled",
			OrderCanceled: u.Canceled,
		})
	}
	return nil, errors.New("union case not set")
}

func (u *OrderEventDiscriminated) UnmarshalJSON(data []byte) error {
	var discriminator struct {
		Value string `json:"_type"`
	}
	err := json.Unmarshal(data, &discriminator)
	if err != nil { return err }

	switch discriminator.Value {
		case "created":
			unionCase := OrderCreated{}
			err := json.Unmarshal(data, &unionCase)
			if err != nil { return err }
			u.Created = &unionCase
		case "changed":
			unionCase := OrderChanged{}
			err := json.Unmarshal(data, &unionCase)
			if err != nil { return err }
			u.Changed = &unionCase
		case "canceled":
			unionCase := OrderCanceled{}
			err := json.Unmarshal(data, &unionCase)
			if err != nil { return err }
			u.Canceled = &unionCase
		default:
			return errors.New(fmt.Sprintf("unexpected union discriminator field _type value: %s", discriminator.Value))
	}
	return nil
}

type MessageCamelCase struct {
	FieldInt int `json:"fieldInt"`
}

type OrderEventCamelCase struct {
	CreatedOrder *OrderCreated `json:"createdOrder,omitempty"`
	ChangedOrder *OrderChanged `json:"changedOrder,omitempty"`
	CanceledOrder *OrderCanceled `json:"canceledOrder,omitempty"`
}
