import * as models from './models'

export interface EchoBodyParams {
    body: models.Message,
}

export type EchoBodyResponse =
    | { status: "ok", data: models.Message }

export interface EchoService {
    echoBody(params: EchoBodyParams): Promise<EchoBodyResponse>
}
