/* eslint-disable @typescript-eslint/camelcase */
/* eslint-disable @typescript-eslint/no-magic-numbers */
import * as t from './io-ts'

export const TMessage = t.interface({
    field: t.number,
})

export type Message = t.TypeOf<typeof TMessage>

export const TMessageCases = t.interface({
    snake_case: t.string,
    camelCase: t.string,
})

export type MessageCases = t.TypeOf<typeof TMessageCases>

export const TParent = t.interface({
    field: t.string,
    nested: TMessage,
})

export type Parent = t.TypeOf<typeof TParent>

export enum Choice {
    FIRST_CHOICE = "One",
    SECOND_CHOICE = "Two",
    THIRD_CHOICE = "Three",
}

export const TChoice = t.enum(Choice)

export const TEnumFields = t.interface({
    enum_field: TChoice,
})

export type EnumFields = t.TypeOf<typeof TEnumFields>

export const TNumericFields = t.interface({
    int_field: t.number,
    long_field: t.number,
    float_field: t.number,
    double_field: t.number,
    decimal_field: t.number,
})

export type NumericFields = t.TypeOf<typeof TNumericFields>

export const TNonNumericFields = t.interface({
    boolean_field: t.boolean,
    string_field: t.string,
    uuid_field: t.string,
    date_field: t.string,
    datetime_field: t.DateISOStringNoTimezone,
})

export type NonNumericFields = t.TypeOf<typeof TNonNumericFields>

export const TArrayFields = t.interface({
    int_array_field: t.array(t.number),
    string_array_field: t.array(t.string),
})

export type ArrayFields = t.TypeOf<typeof TArrayFields>

export const TMapFields = t.interface({
    int_map_field: t.record(t.string, t.number),
    string_map_field: t.record(t.string, t.string),
})

export type MapFields = t.TypeOf<typeof TMapFields>

export const TOptionalFields = t.partial({
    int_option_field: t.union([t.number, t.null]),
    string_option_field: t.union([t.string, t.null]),
})

export type OptionalFields = t.TypeOf<typeof TOptionalFields>

export const TRawJsonField = t.interface({
    json_field: t.unknown,
})

export type RawJsonField = t.TypeOf<typeof TRawJsonField>

export const TOrderCreated = t.interface({
    id: t.string,
    sku: t.string,
    quantity: t.number,
})

export type OrderCreated = t.TypeOf<typeof TOrderCreated>

export const TOrderChanged = t.interface({
    id: t.string,
    quantity: t.number,
})

export type OrderChanged = t.TypeOf<typeof TOrderChanged>

export const TOrderCanceled = t.interface({
    id: t.string,
})

export type OrderCanceled = t.TypeOf<typeof TOrderCanceled>

export const TOrderEventWrapper = t.union([
    t.interface({created: TOrderCreated}),
    t.interface({changed: TOrderChanged}),
    t.interface({canceled: TOrderCanceled}),
])

export type OrderEventWrapper = t.TypeOf<typeof TOrderEventWrapper>

export const TOrderEventDiscriminator = t.union([
    t.intersection([t.type({_type: t.literal('created')}), TOrderCreated]),
    t.intersection([t.type({_type: t.literal('changed')}), TOrderChanged]),
    t.intersection([t.type({_type: t.literal('canceled')}), TOrderCanceled]),
])

export type OrderEventDiscriminator = t.TypeOf<typeof TOrderEventDiscriminator>
