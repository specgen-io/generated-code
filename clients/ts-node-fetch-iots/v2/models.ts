/* eslint-disable @typescript-eslint/camelcase */
/* eslint-disable @typescript-eslint/no-magic-numbers */
import * as t from './../io-ts'

export const TMessage = t.interface({
    bool_field: t.boolean,
    string_field: t.string,
})

export type Message = t.TypeOf<typeof TMessage>

export enum ErrorLocation {
    QUERY = "query",
    HEADER = "header",
    BODY = "body",
}

export const TErrorLocation = t.enum(ErrorLocation)

export const TValidationError = t.intersection([
    t.interface({
        path: t.string,
        code: t.string,
    }),
    t.partial({
        message: t.union([t.string, t.null]),
    })
])

export type ValidationError = t.TypeOf<typeof TValidationError>

export const TBadRequestError = t.intersection([
    t.interface({
        message: t.string,
        location: TErrorLocation,
    }),
    t.partial({
        errors: t.union([t.array(TValidationError), t.null]),
    })
])

export type BadRequestError = t.TypeOf<typeof TBadRequestError>

export const TNotFoundError = t.interface({
    message: t.string,
})

export type NotFoundError = t.TypeOf<typeof TNotFoundError>

export const TInternalServerError = t.interface({
    message: t.string,
})

export type InternalServerError = t.TypeOf<typeof TInternalServerError>
