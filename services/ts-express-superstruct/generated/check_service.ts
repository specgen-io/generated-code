import * as models from './models'

export interface CheckEmptyResponseParams {
    body: models.Message,
}

export type CheckForbiddenResponse =
    | { status: "ok", data: models.Message }
    | { status: "forbidden" }

export type SameOperationNameResponse =
    | { status: "ok" }
    | { status: "forbidden" }

export interface CheckService {
    checkEmpty(): Promise<void>
    checkEmptyResponse(params: CheckEmptyResponseParams): Promise<void>
    checkForbidden(): Promise<CheckForbiddenResponse>
    sameOperationName(): Promise<SameOperationNameResponse>
}
