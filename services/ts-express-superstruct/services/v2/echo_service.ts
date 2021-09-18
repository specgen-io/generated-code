import * as service from './../../v2/echo_service'

export let echoService = (): service.EchoService => {
    let echoBody = async (params: service.EchoBodyParams): Promise<service.EchoBodyResponse> => {
        throw new Error('Not Implemented')
    }

    return {echoBody}
}
