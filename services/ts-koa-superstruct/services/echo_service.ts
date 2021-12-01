import * as service from './../echo_service'
import * as models from './../models'

export let echoService = (): service.EchoService => {
    let echoBodyString = async (params: service.EchoBodyStringParams): Promise<string> => {
        throw new Error('Not Implemented')
    }

    let echoBody = async (params: service.EchoBodyParams): Promise<models.Message> => {
        throw new Error('Not Implemented')
    }

    let echoQuery = async (params: service.EchoQueryParams): Promise<models.Message> => {
        throw new Error('Not Implemented')
    }

    let echoHeader = async (params: service.EchoHeaderParams): Promise<models.Message> => {
        throw new Error('Not Implemented')
    }

    let echoUrlParams = async (params: service.EchoUrlParamsParams): Promise<models.Message> => {
        throw new Error('Not Implemented')
    }

    let sameOperationName = async (): Promise<service.SameOperationNameResponse> => {
        throw new Error('Not Implemented')
    }

    return {echoBodyString, echoBody, echoQuery, echoHeader, echoUrlParams, sameOperationName}
}
