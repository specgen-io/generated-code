import * as t from './../superstruct'

export const TMessage = t.type({
    bool_field: t.boolean(),
    string_field: t.string(),
})

export type Message = t.Infer<typeof TMessage>

export const TInternalServerError = t.type({
    message: t.string(),
})

export type InternalServerError = t.Infer<typeof TInternalServerError>

export const TParamMessage = t.type({
    name: t.string(),
    message: t.string(),
})

export type ParamMessage = t.Infer<typeof TParamMessage>

export const TBadRequestError = t.type({
    message: t.string(),
    params: t.array(TParamMessage),
})

export type BadRequestError = t.Infer<typeof TBadRequestError>
