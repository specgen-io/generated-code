import * as service from './../../v2/echo_service'
import * as models from './../../v2/models'

export let echoService = (): service.EchoService => {
    let echoBody = async (params: service.EchoBodyParams): Promise<models.Message> => {
        throw new Error('Not Implemented')
    }

    return {echoBody}
}
