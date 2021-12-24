package models

import (
	"cloud.google.com/go/civil"
	"github.com/google/uuid"
	"github.com/shopspring/decimal"
	"test-service/empty"
)

type Message struct {
	IntField int `json:"int_field"`
	StringField string `json:"string_field"`
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

type Parameters struct {
	IntField int `json:"int_field"`
	LongField int64 `json:"long_field"`
	FloatField float32 `json:"float_field"`
	DoubleField float64 `json:"double_field"`
	DecimalField decimal.Decimal `json:"decimal_field"`
	BoolField bool `json:"bool_field"`
	StringField string `json:"string_field"`
	StringOptField *string `json:"string_opt_field"`
	StringDefaultedField string `json:"string_defaulted_field"`
	StringArrayField []string `json:"string_array_field"`
	UuidField uuid.UUID `json:"uuid_field"`
	DateField civil.Date `json:"date_field"`
	DateArrayField []civil.Date `json:"date_array_field"`
	DatetimeField civil.DateTime `json:"datetime_field"`
	EnumField Choice `json:"enum_field"`
}

type UrlParameters struct {
	IntField int `json:"int_field"`
	LongField int64 `json:"long_field"`
	FloatField float32 `json:"float_field"`
	DoubleField float64 `json:"double_field"`
	DecimalField decimal.Decimal `json:"decimal_field"`
	BoolField bool `json:"bool_field"`
	StringField string `json:"string_field"`
	UuidField uuid.UUID `json:"uuid_field"`
	DateField civil.Date `json:"date_field"`
	DatetimeField civil.DateTime `json:"datetime_field"`
	EnumField Choice `json:"enum_field"`
}

type Everything struct {
	BodyField Message `json:"body_field"`
	FloatQuery float32 `json:"float_query"`
	BoolQuery bool `json:"bool_query"`
	UuidHeader uuid.UUID `json:"uuid_header"`
	DatetimeHeader civil.DateTime `json:"datetime_header"`
	DateUrl civil.Date `json:"date_url"`
	DecimalUrl decimal.Decimal `json:"decimal_url"`
}
