import * as models from './models'

export interface EchoBodyStringParams {
    body: string,
}

export interface EchoBodyModelParams {
    body: models.Message,
}

export interface EchoBodyArrayParams {
    body: string[],
}

export interface EchoBodyMapParams {
    body: Record<string, string>,
}

export interface EchoQueryParams {
    int_query: number,
    long_query: number,
    float_query: number,
    double_query: number,
    decimal_query: number,
    bool_query: boolean,
    string_query: string,
    string_opt_query?: string | undefined,
    string_defaulted_query: string,
    string_array_query: string[],
    uuid_query: string,
    date_query: string,
    date_array_query: string[],
    datetime_query: Date,
    enum_query: models.Choice,
}

export interface EchoHeaderParams {
    'Int-Header': number,
    'Long-Header': number,
    'Float-Header': number,
    'Double-Header': number,
    'Decimal-Header': number,
    'Bool-Header': boolean,
    'String-Header': string,
    'String-Opt-Header'?: string | undefined,
    'String-Defaulted-Header': string,
    'String-Array-Header': string[],
    'Uuid-Header': string,
    'Date-Header': string,
    'Date-Array-Header': string[],
    'Datetime-Header': Date,
    'Enum-Header': models.Choice,
}

export interface EchoUrlParamsParams {
    int_url: number,
    long_url: number,
    float_url: number,
    double_url: number,
    decimal_url: number,
    bool_url: boolean,
    string_url: string,
    uuid_url: string,
    date_url: string,
    datetime_url: Date,
    enum_url: models.Choice,
}

export interface EchoEverythingParams {
    'Uuid-Header': string,
    'Datetime-Header': Date,
    date_url: string,
    decimal_url: number,
    float_query: number,
    bool_query: boolean,
    body: models.Message,
}

export type EchoEverythingResponse =
    | { status: "ok", data: models.Everything }
    | { status: "forbidden" }

export type SameOperationNameResponse =
    | { status: "ok" }
    | { status: "forbidden" }

export interface EchoService {
    echoBodyString(params: EchoBodyStringParams): Promise<string>
    echoBodyModel(params: EchoBodyModelParams): Promise<models.Message>
    echoBodyArray(params: EchoBodyArrayParams): Promise<string[]>
    echoBodyMap(params: EchoBodyMapParams): Promise<Record<string, string>>
    echoQuery(params: EchoQueryParams): Promise<models.Parameters>
    echoHeader(params: EchoHeaderParams): Promise<models.Parameters>
    echoUrlParams(params: EchoUrlParamsParams): Promise<models.UrlParameters>
    echoEverything(params: EchoEverythingParams): Promise<EchoEverythingResponse>
    sameOperationName(): Promise<SameOperationNameResponse>
}
