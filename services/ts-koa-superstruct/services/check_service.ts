import * as service from './../check_service'
import * as models from './../models'

export let checkService = (): service.CheckService => {
    let checkEmpty = async (): Promise<void> => {
        throw new Error('Not Implemented')
    }

    let checkForbidden = async (): Promise<service.CheckForbiddenResponse> => {
        throw new Error('Not Implemented')
    }

    let sameOperationName = async (): Promise<service.SameOperationNameResponse> => {
        throw new Error('Not Implemented')
    }

    return {checkEmpty, checkForbidden, sameOperationName}
}
