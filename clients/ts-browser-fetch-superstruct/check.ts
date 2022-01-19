import { strParamsItems, stringify } from './params'
import * as t from './superstruct'
import * as models from './models'

export const client = (config: {baseURL: string}) => {
    return {
        checkEmpty: async (): Promise<void> => {
            const url = config.baseURL+`/check/empty`
            const response = await fetch(url, {method: 'GET'})
            switch (response.status) {
                case 200:
                    return Promise.resolve()
                default:
                    throw new Error(`Unexpected status code ${ response.status }`)
            }
        },

        checkEmptyResponse: async (parameters: {body: models.Message}): Promise<void> => {
            const headers = strParamsItems({
                "Content-Type": "application/json"
            })
            const url = config.baseURL+`/check/empty_response`
            const bodyJson = t.encode(models.TMessage, parameters.body)
            const response = await fetch(url, {method: 'POST', headers: headers, body: JSON.stringify(bodyJson)})
            switch (response.status) {
                case 200:
                    return Promise.resolve()
                default:
                    throw new Error(`Unexpected status code ${ response.status }`)
            }
        },

        checkForbidden: async (): Promise<CheckForbiddenResponse> => {
            const url = config.baseURL+`/check/forbidden`
            const response = await fetch(url, {method: 'GET'})
            switch (response.status) {
                case 200:
                    return Promise.resolve({ status: "ok", data: t.decode(models.TMessage, await response.json()) })
                case 403:
                    return Promise.resolve({ status: "forbidden" })
                default:
                    throw new Error(`Unexpected status code ${ response.status }`)
            }
        },

        sameOperationName: async (): Promise<SameOperationNameResponse> => {
            const url = config.baseURL+`/check/same_operation_name`
            const response = await fetch(url, {method: 'GET'})
            switch (response.status) {
                case 200:
                    return Promise.resolve({ status: "ok" })
                case 403:
                    return Promise.resolve({ status: "forbidden" })
                default:
                    throw new Error(`Unexpected status code ${ response.status }`)
            }
        },
    }
}

export type CheckForbiddenResponse =
    | { status: "ok", data: models.Message }
    | { status: "forbidden" }

export type SameOperationNameResponse =
    | { status: "ok" }
    | { status: "forbidden" }
