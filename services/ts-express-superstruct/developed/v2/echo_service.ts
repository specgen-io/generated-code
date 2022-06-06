import * as service from './../../generated/v2/echo'
import * as models from './../../generated/v2/models'

export const echoService = (): service.EchoService => {
    const echoBodyModel = async (params: service.EchoBodyModelParams): Promise<models.Message> => {
        throw new Error('Not Implemented')
    }

    return {echoBodyModel}
}
