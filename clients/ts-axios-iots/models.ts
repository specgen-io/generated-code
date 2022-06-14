/* eslint-disable @typescript-eslint/camelcase */
/* eslint-disable @typescript-eslint/no-magic-numbers */
import * as t from './io-ts'

export const TMessage = t.interface({
    int_field: t.number,
    string_field: t.string,
})

export type Message = t.TypeOf<typeof TMessage>

export enum Choice {
    FIRST_CHOICE = "FIRST_CHOICE",
    SECOND_CHOICE = "SECOND_CHOICE",
    THIRD_CHOICE = "THIRD_CHOICE",
}

export const TChoice = t.enum(Choice)

export const TParameters = t.intersection([
    t.interface({
        int_field: t.number,
        long_field: t.number,
        float_field: t.number,
        double_field: t.number,
        decimal_field: t.number,
        bool_field: t.boolean,
        string_field: t.string,
        string_defaulted_field: t.string,
        string_array_field: t.array(t.string),
        uuid_field: t.string,
        date_field: t.string,
        date_array_field: t.array(t.string),
        datetime_field: t.DateISOStringNoTimezone,
        enum_field: TChoice,
    }),
    t.partial({
        string_opt_field: t.union([t.string, t.null]),
    })
])

export type Parameters = t.TypeOf<typeof TParameters>

export const TUrlParameters = t.interface({
    int_field: t.number,
    long_field: t.number,
    float_field: t.number,
    double_field: t.number,
    decimal_field: t.number,
    bool_field: t.boolean,
    string_field: t.string,
    uuid_field: t.string,
    date_field: t.string,
    datetime_field: t.DateISOStringNoTimezone,
    enum_field: TChoice,
})

export type UrlParameters = t.TypeOf<typeof TUrlParameters>

export const TEverything = t.interface({
    body_field: TMessage,
    float_query: t.number,
    bool_query: t.boolean,
    uuid_header: t.string,
    datetime_header: t.DateISOStringNoTimezone,
    date_url: t.string,
    decimal_url: t.number,
})

export type Everything = t.TypeOf<typeof TEverything>

export const TOkResult = t.interface({
    ok_result: t.string,
})

export type OkResult = t.TypeOf<typeof TOkResult>

export const TCreatedResult = t.interface({
    created_result: t.string,
})

export type CreatedResult = t.TypeOf<typeof TCreatedResult>

export const TAcceptedResult = t.interface({
    accepted_result: t.string,
})

export type AcceptedResult = t.TypeOf<typeof TAcceptedResult>

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
