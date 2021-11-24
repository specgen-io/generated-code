import * as models from './models'

export interface CheckQueryParams {
    p_string: string,
    p_string_opt?: string | undefined,
    p_string_array: string[],
    p_date: string,
    p_date_array: string[],
    p_datetime: Date,
    p_int: number,
    p_long: number,
    p_decimal: number,
    p_enum: models.Choice,
    p_string_defaulted: string,
}

export interface CheckUrlParamsParams {
    int_url: number,
    string_url: string,
    float_url: number,
    bool_url: boolean,
    uuid_url: string,
    decimal_url: number,
    date_url: string,
    enum_url: models.Choice,
}

export type CheckForbiddenResponse =
    | { status: "ok", data: models.Message }
    | { status: "forbidden" }

export type SameOperationNameResponse =
    | { status: "ok" }
    | { status: "forbidden" }

export interface CheckService {
    checkEmpty(): Promise<void>
    checkQuery(params: CheckQueryParams): Promise<void>
    checkUrlParams(params: CheckUrlParamsParams): Promise<void>
    checkForbidden(): Promise<CheckForbiddenResponse>
    sameOperationName(): Promise<SameOperationNameResponse>
}
