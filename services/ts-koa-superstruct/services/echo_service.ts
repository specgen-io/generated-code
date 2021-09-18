import * as service from './../echo_service'

export let echoService = (): service.EchoService => {
    let echoBody = async (params: service.EchoBodyParams): Promise<service.EchoBodyResponse> => {
        throw new Error('Not Implemented')
    }

    let echoQuery = async (params: service.EchoQueryParams): Promise<service.EchoQueryResponse> => {
        throw new Error('Not Implemented')
    }

    let echoHeader = async (params: service.EchoHeaderParams): Promise<service.EchoHeaderResponse> => {
        throw new Error('Not Implemented')
    }

    let echoUrlParams = async (params: service.EchoUrlParamsParams): Promise<service.EchoUrlParamsResponse> => {
        throw new Error('Not Implemented')
    }

    return {echoBody, echoQuery, echoHeader, echoUrlParams}
}
