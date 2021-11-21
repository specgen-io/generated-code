import * as service from './../check_service'

export let checkService = (): service.CheckService => {
    let checkEmpty = async (): Promise<service.CheckEmptyResponse> => {
        throw new Error('Not Implemented')
    }

    let checkQuery = async (params: service.CheckQueryParams): Promise<service.CheckQueryResponse> => {
        throw new Error('Not Implemented')
    }

    let checkUrlParams = async (params: service.CheckUrlParamsParams): Promise<service.CheckUrlParamsResponse> => {
        throw new Error('Not Implemented')
    }

    let checkForbidden = async (): Promise<service.CheckForbiddenResponse> => {
        throw new Error('Not Implemented')
    }

    let sameOperationName = async (): Promise<service.SameOperationNameResponse> => {
        throw new Error('Not Implemented')
    }

    return {checkEmpty, checkQuery, checkUrlParams, checkForbidden, sameOperationName}
}
