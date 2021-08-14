import * as services from './spec/services'

export let echoService = (): services.EchoService => {
    let echoBody = async (params: services.EchoBodyParams): Promise<services.EchoBodyResponse> => {
        throw new Error('Not Implemented')
    }

    let echoQuery = async (params: services.EchoQueryParams): Promise<services.EchoQueryResponse> => {
        throw new Error('Not Implemented')
    }

    let echoHeader = async (params: services.EchoHeaderParams): Promise<services.EchoHeaderResponse> => {
        throw new Error('Not Implemented')
    }

    let echoUrlParams = async (params: services.EchoUrlParamsParams): Promise<services.EchoUrlParamsResponse> => {
        throw new Error('Not Implemented')
    }

    return {echoBody, echoQuery, echoHeader, echoUrlParams}
}
