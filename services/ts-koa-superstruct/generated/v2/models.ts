import * as t from './../superstruct'

export const TMessage = t.type({
    bool_field: t.boolean(),
    string_field: t.string(),
})

export type Message = t.Infer<typeof TMessage>

export const TErrorLocation = t.enums ([
    "query",
    "header",
    "body",
    "unknown",
])

export type ErrorLocation = t.Infer<typeof TErrorLocation>

export const ErrorLocation = {
    QUERY: <ErrorLocation>"query",
    HEADER: <ErrorLocation>"header",
    BODY: <ErrorLocation>"body",
    UNKNOWN: <ErrorLocation>"unknown",
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
    errors: t.array(TValidationError),
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
