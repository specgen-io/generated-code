import * as services from './spec/services'

export let checkService = (): services.CheckService => {
    let checkEmpty = async (): Promise<services.CheckEmptyResponse> => {
        throw new Error('Not Implemented')
    }

    let checkQuery = async (params: services.CheckQueryParams): Promise<services.CheckQueryResponse> => {
        throw new Error('Not Implemented')
    }

    let checkUrlParams = async (params: services.CheckUrlParamsParams): Promise<services.CheckUrlParamsResponse> => {
        throw new Error('Not Implemented')
    }

    let checkForbidden = async (): Promise<services.CheckForbiddenResponse> => {
        throw new Error('Not Implemented')
    }

    return {checkEmpty, checkQuery, checkUrlParams, checkForbidden}
}
