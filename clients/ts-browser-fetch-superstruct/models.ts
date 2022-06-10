import * as t from './superstruct'

export const TMessage = t.type({
    int_field: t.number(),
    string_field: t.string(),
})

export type Message = t.Infer<typeof TMessage>

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

export const TParameters = t.type({
    int_field: t.number(),
    long_field: t.number(),
    float_field: t.number(),
    double_field: t.number(),
    decimal_field: t.number(),
    bool_field: t.boolean(),
    string_field: t.string(),
    string_opt_field: t.optional(t.nullable(t.string())),
    string_defaulted_field: t.string(),
    string_array_field: t.array(t.string()),
    uuid_field: t.string(),
    date_field: t.string(),
    date_array_field: t.array(t.string()),
    datetime_field: t.StrDateTime,
    enum_field: TChoice,
})

export type Parameters = t.Infer<typeof TParameters>

export const TUrlParameters = t.type({
    int_field: t.number(),
    long_field: t.number(),
    float_field: t.number(),
    double_field: t.number(),
    decimal_field: t.number(),
    bool_field: t.boolean(),
    string_field: t.string(),
    uuid_field: t.string(),
    date_field: t.string(),
    datetime_field: t.StrDateTime,
    enum_field: TChoice,
})

export type UrlParameters = t.Infer<typeof TUrlParameters>

export const TEverything = t.type({
    body_field: TMessage,
    float_query: t.number(),
    bool_query: t.boolean(),
    uuid_header: t.string(),
    datetime_header: t.StrDateTime,
    date_url: t.string(),
    decimal_url: t.number(),
})

export type Everything = t.Infer<typeof TEverything>

export const TOkResult = t.type({
    ok_result: t.string(),
})

export type OkResult = t.Infer<typeof TOkResult>

export const TCreatedResult = t.type({
    created_result: t.string(),
})

export type CreatedResult = t.Infer<typeof TCreatedResult>

export const TAcceptedResult = t.type({
    accepted_result: t.string(),
})

export type AcceptedResult = t.Infer<typeof TAcceptedResult>

export const TErrorLocation = t.enums ([
    "query",
    "header",
    "body",
])

export type ErrorLocation = t.Infer<typeof TErrorLocation>

export const ErrorLocation = {
    QUERY: <ErrorLocation>"query",
    HEADER: <ErrorLocation>"header",
    BODY: <ErrorLocation>"body",
}

export const TValidationError = t.type({
    path: t.string(),
    code: t.string(),
    message: t.optional(t.nullable(t.string())),
})

export type ValidationError = t.Infer<typeof TValidationError>

export const TBadRequestError = t.type({
    message: t.string(),
    location: TErrorLocation,
    errors: t.optional(t.nullable(t.array(TValidationError))),
})

export type BadRequestError = t.Infer<typeof TBadRequestError>

export const TNotFoundError = t.type({
    message: t.string(),
})

export type NotFoundError = t.Infer<typeof TNotFoundError>

export const TInternalServerError = t.type({
    message: t.string(),
})

export type InternalServerError = t.Infer<typeof TInternalServerError>
