import * as models from './models'

export interface EchoBodyParams {
    body: models.Message,
}

export interface EchoQueryParams {
    int_query: number,
    string_query: string,
}

export interface EchoHeaderParams {
    'int-header': number,
    'string-header': string,
}

export interface EchoUrlParamsParams {
    int_url: number,
    string_url: string,
}

export type SameOperationNameResponse =
    | { status: "ok" }
    | { status: "forbidden" }

export interface EchoService {
    echoBody(params: EchoBodyParams): Promise<models.Message>
    echoQuery(params: EchoQueryParams): Promise<models.Message>
    echoHeader(params: EchoHeaderParams): Promise<models.Message>
    echoUrlParams(params: EchoUrlParamsParams): Promise<models.Message>
    sameOperationName(): Promise<SameOperationNameResponse>
}
