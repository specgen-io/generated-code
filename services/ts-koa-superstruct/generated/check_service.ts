import * as models from './models'

export type CheckForbiddenResponse =
    | { status: "ok", data: models.Message }
    | { status: "forbidden" }

export type SameOperationNameResponse =
    | { status: "ok" }
    | { status: "forbidden" }

export interface CheckService {
    checkEmpty(): Promise<void>
    checkForbidden(): Promise<CheckForbiddenResponse>
    sameOperationName(): Promise<SameOperationNameResponse>
}
