import * as models from './models'

export interface EchoBodyParams {
    body: models.Message,
}

export interface EchoService {
    echoBody(params: EchoBodyParams): Promise<models.Message>
}
