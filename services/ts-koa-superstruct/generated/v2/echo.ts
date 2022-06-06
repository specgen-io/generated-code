import * as models from './models'

export interface EchoBodyModelParams {
    body: models.Message,
}

export interface EchoService {
    echoBodyModel(params: EchoBodyModelParams): Promise<models.Message>
}
