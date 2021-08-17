import * as t from './superstruct'

export const TMessage = t.type({
    field: t.number(),
})

export type Message = t.Infer<typeof TMessage>

export const TNested = t.type({
    field: t.string(),
})

export type Nested = t.Infer<typeof TNested>

export const TParent = t.type({
    field: t.string(),
    nested: TNested,
})

export type Parent = t.Infer<typeof TParent>

export const TChoice = t.enums ([
    "FIRST_CHOICE",
    "SECOND_CHOICE",
    "THIRD_CHOICE",
])

export type Choice = t.Infer<typeof TChoice>

export const Choice = {
    FIRST_CHOICE: <Choice>"FIRST_CHOICE",
    SECOND_CHOICE: <Choice>"SECOND_CHOICE",
    THIRD_CHOICE: <Choice>"THIRD_CHOICE",
}

export const TEnumFields = t.type({
    enum_field: TChoice,
})

export type EnumFields = t.Infer<typeof TEnumFields>

export const TNumericFields = t.type({
    int_field: t.number(),
    long_field: t.number(),
    float_field: t.number(),
    double_field: t.number(),
    decimal_field: t.number(),
})

export type NumericFields = t.Infer<typeof TNumericFields>

export const TNonNumericFields = t.type({
    boolean_field: t.boolean(),
    string_field: t.string(),
    uuid_field: t.string(),
    date_field: t.string(),
    datetime_field: t.StrDateTime,
})

export type NonNumericFields = t.Infer<typeof TNonNumericFields>

export const TArrayFields = t.type({
    int_array_field: t.array(t.number()),
    string_array_field: t.array(t.string()),
})

export type ArrayFields = t.Infer<typeof TArrayFields>

export const TMapFields = t.type({
    int_map_field: t.record(t.string(), t.number()),
    string_map_field: t.record(t.string(), t.string()),
})

export type MapFields = t.Infer<typeof TMapFields>

export const TOptionalFields = t.type({
    int_option_field: t.optional(t.nullable(t.number())),
    string_option_field: t.optional(t.nullable(t.string())),
})

export type OptionalFields = t.Infer<typeof TOptionalFields>

export const TRawJsonField = t.type({
    json_field: t.unknown(),
})

export type RawJsonField = t.Infer<typeof TRawJsonField>

export const TOrderCreated = t.type({
    id: t.string(),
    sku: t.string(),
    quantity: t.number(),
})

export type OrderCreated = t.Infer<typeof TOrderCreated>

export const TOrderChanged = t.type({
    id: t.string(),
    quantity: t.number(),
})

export type OrderChanged = t.Infer<typeof TOrderChanged>

export const TOrderCanceled = t.type({
    id: t.string(),
})

export type OrderCanceled = t.Infer<typeof TOrderCanceled>

export const TOrderEvent = t.union([
    t.object({created: TOrderCreated}),
    t.object({changed: TOrderChanged}),
    t.object({canceled: TOrderCanceled}),
])

export type OrderEvent = t.Infer<typeof TOrderEvent>

export const TMessageCamelCase = t.type({
    fieldInt: t.number(),
})

export type MessageCamelCase = t.Infer<typeof TMessageCamelCase>

export const TOrderEventCamelCase = t.union([
    t.object({createdOrder: TOrderCreated}),
    t.object({changedOrder: TOrderChanged}),
    t.object({canceledOrder: TOrderCanceled}),
])

export type OrderEventCamelCase = t.Infer<typeof TOrderEventCamelCase>
