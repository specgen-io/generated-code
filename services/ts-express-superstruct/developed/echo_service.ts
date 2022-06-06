import * as service from './../generated/echo'
import * as models from './../generated/models'

export const echoService = (): service.EchoService => {
    const echoBodyString = async (params: service.EchoBodyStringParams): Promise<string> => {
        throw new Error('Not Implemented')
    }

    const echoBodyModel = async (params: service.EchoBodyModelParams): Promise<models.Message> => {
        throw new Error('Not Implemented')
    }

    const echoBodyArray = async (params: service.EchoBodyArrayParams): Promise<string[]> => {
        throw new Error('Not Implemented')
    }

    const echoBodyMap = async (params: service.EchoBodyMapParams): Promise<Record<string, string>> => {
        throw new Error('Not Implemented')
    }

    const echoQuery = async (params: service.EchoQueryParams): Promise<models.Parameters> => {
        throw new Error('Not Implemented')
    }

    const echoHeader = async (params: service.EchoHeaderParams): Promise<models.Parameters> => {
        throw new Error('Not Implemented')
    }

    const echoUrlParams = async (params: service.EchoUrlParamsParams): Promise<models.UrlParameters> => {
        throw new Error('Not Implemented')
    }

    const echoEverything = async (params: service.EchoEverythingParams): Promise<service.EchoEverythingResponse> => {
        throw new Error('Not Implemented')
    }

    const sameOperationName = async (): Promise<service.SameOperationNameResponse> => {
        throw new Error('Not Implemented')
    }

    return {echoBodyString, echoBodyModel, echoBodyArray, echoBodyMap, echoQuery, echoHeader, echoUrlParams, echoEverything, sameOperationName}
}
