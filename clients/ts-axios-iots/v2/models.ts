/* eslint-disable @typescript-eslint/camelcase */
/* eslint-disable @typescript-eslint/no-magic-numbers */
import * as t from './../io-ts'

export const TMessage = t.interface({
    bool_field: t.boolean,
    string_field: t.string,
})

export type Message = t.TypeOf<typeof TMessage>

export const TInternalServerError = t.interface({
    message: t.string,
})

export type InternalServerError = t.TypeOf<typeof TInternalServerError>

export const TParamMessage = t.interface({
    name: t.string,
    message: t.string,
})

export type ParamMessage = t.TypeOf<typeof TParamMessage>

export const TBadRequestError = t.interface({
    message: t.string,
    params: t.array(TParamMessage),
})

export type BadRequestError = t.TypeOf<typeof TBadRequestError>
