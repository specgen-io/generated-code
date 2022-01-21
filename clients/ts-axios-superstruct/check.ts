import { AxiosInstance, AxiosRequestConfig } from 'axios'
import { strParamsItems, strParamsObject, stringify } from './params'
import * as t from './superstruct'
import * as models from './models'

export const client = (axiosInstance: AxiosInstance) => {
    return {
        axiosInstance,

        checkEmpty: async (): Promise<void> => {
            const response = await axiosInstance.get(`/check/empty`, {})
            switch (response.status) {
                case 200:
                    return Promise.resolve()
                default:
                    throw new Error(`Unexpected status code ${ response.status }`)
            }
        },

        checkEmptyResponse: async (parameters: {body: models.Message}): Promise<void> => {
            const bodyJson = t.encode(models.TMessage, parameters.body)
            const response = await axiosInstance.post(`/check/empty_response`, bodyJson, {})
            switch (response.status) {
                case 200:
                    return Promise.resolve()
                default:
                    throw new Error(`Unexpected status code ${ response.status }`)
            }
        },

        checkForbidden: async (): Promise<CheckForbiddenResponse> => {
            const response = await axiosInstance.get(`/check/forbidden`, {})
            switch (response.status) {
                case 200:
                    return Promise.resolve({ status: "ok", data: t.decode(models.TMessage, response.data) })
                case 403:
                    return Promise.resolve({ status: "forbidden" })
                default:
                    throw new Error(`Unexpected status code ${ response.status }`)
            }
        },

        sameOperationName: async (): Promise<SameOperationNameResponse> => {
            const response = await axiosInstance.get(`/check/same_operation_name`, {})
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
