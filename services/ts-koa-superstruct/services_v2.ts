import * as models from './models_v2'

export interface EchoBodyParams {
    body: models.Message,
}

export type EchoBodyResponse =
    | { status: "ok", data: models.Message }

export interface EchoService {
    echoBody(params: EchoBodyParams): Promise<EchoBodyResponse>
}
