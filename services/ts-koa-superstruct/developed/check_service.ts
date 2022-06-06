import * as service from './../generated/check'
import * as models from './../generated/models'

export const checkService = (): service.CheckService => {
    const checkEmpty = async (): Promise<void> => {
        throw new Error('Not Implemented')
    }

    const checkEmptyResponse = async (params: service.CheckEmptyResponseParams): Promise<void> => {
        throw new Error('Not Implemented')
    }

    const checkForbidden = async (): Promise<service.CheckForbiddenResponse> => {
        throw new Error('Not Implemented')
    }

    const sameOperationName = async (): Promise<service.SameOperationNameResponse> => {
        throw new Error('Not Implemented')
    }

    return {checkEmpty, checkEmptyResponse, checkForbidden, sameOperationName}
}
