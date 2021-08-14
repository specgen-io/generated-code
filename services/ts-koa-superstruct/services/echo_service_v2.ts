import * as services from './spec/services_v2'

export let echoService = (): services.EchoService => {
    let echoBody = async (params: services.EchoBodyParams): Promise<services.EchoBodyResponse> => {
        throw new Error('Not Implemented')
    }

    return {echoBody}
}
