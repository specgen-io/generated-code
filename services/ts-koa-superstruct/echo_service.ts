import * as models from './models'

export interface EchoBodyParams {
    body: models.Message,
}

export type EchoBodyResponse =
    | { status: "ok", data: models.Message }

export interface EchoQueryParams {
    int_query: number,
    string_query: string,
}

export type EchoQueryResponse =
    | { status: "ok", data: models.Message }

export interface EchoHeaderParams {
    'int-header': number,
    'string-header': string,
}

export type EchoHeaderResponse =
    | { status: "ok", data: models.Message }

export interface EchoUrlParamsParams {
    int_url: number,
    string_url: string,
}

export type EchoUrlParamsResponse =
    | { status: "ok", data: models.Message }

export type SameOperationNameResponse =
    | { status: "ok" }

export interface EchoService {
    echoBody(params: EchoBodyParams): Promise<EchoBodyResponse>
    echoQuery(params: EchoQueryParams): Promise<EchoQueryResponse>
    echoHeader(params: EchoHeaderParams): Promise<EchoHeaderResponse>
    echoUrlParams(params: EchoUrlParamsParams): Promise<EchoUrlParamsResponse>
    sameOperationName(): Promise<SameOperationNameResponse>
}
