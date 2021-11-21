import { AxiosInstance, AxiosRequestConfig } from 'axios'
import * as t from './superstruct'
import * as models from './models'

export const client = (axiosInstance: AxiosInstance) => {
    return {
        axiosInstance,

        checkEmpty: async (): Promise<CheckEmptyResponse> => {
            const config: AxiosRequestConfig = {}
            const response = await axiosInstance.get(`/check/empty`, config)
            switch (response.status) {
                case 200:
                    return Promise.resolve({ status: "ok" })
                default:
                    throw new Error(`Unexpected status code ${ response.status }`)
            }
        },

        checkQuery: async (parameters: {pString: string, pStringArray: string[], pDate: string, pDateArray: string[], pDatetime: Date, pInt: number, pLong: number, pDecimal: number, pEnum: models.Choice, pStringOpt?: string | undefined, pStringDefaulted?: string | undefined}): Promise<CheckQueryResponse> => {
            const params = {
                "p_string": parameters.pString,
                "p_string_opt": parameters.pStringOpt,
                "p_string_array": parameters.pStringArray,
                "p_date": parameters.pDate,
                "p_date_array": parameters.pDateArray,
                "p_datetime": parameters.pDatetime,
                "p_int": parameters.pInt,
                "p_long": parameters.pLong,
                "p_decimal": parameters.pDecimal,
                "p_enum": parameters.pEnum,
                "p_string_defaulted": parameters.pStringDefaulted,
            }
            const config: AxiosRequestConfig = {params: params,}
            const response = await axiosInstance.get(`/check/query`, config)
            switch (response.status) {
                case 200:
                    return Promise.resolve({ status: "ok" })
                default:
                    throw new Error(`Unexpected status code ${ response.status }`)
            }
        },

        checkUrlParams: async (parameters: {intUrl: number, stringUrl: string, floatUrl: number, boolUrl: boolean, uuidUrl: string, decimalUrl: number, dateUrl: string}): Promise<CheckUrlParamsResponse> => {
            const config: AxiosRequestConfig = {}
            const response = await axiosInstance.get(`/check/url_params/${parameters.intUrl}/${parameters.stringUrl}/${parameters.floatUrl}/${parameters.boolUrl}/${parameters.uuidUrl}/${parameters.decimalUrl}/${parameters.dateUrl}`, config)
            switch (response.status) {
                case 200:
                    return Promise.resolve({ status: "ok" })
                default:
                    throw new Error(`Unexpected status code ${ response.status }`)
            }
        },

        checkForbidden: async (): Promise<CheckForbiddenResponse> => {
            const config: AxiosRequestConfig = {}
            const response = await axiosInstance.get(`/check/forbidden`, config)
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
            const config: AxiosRequestConfig = {}
            const response = await axiosInstance.get(`/check/same_operation_name`, config)
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

export type CheckEmptyResponse =
    | { status: "ok" }

export type CheckQueryResponse =
    | { status: "ok" }

export type CheckUrlParamsResponse =
    | { status: "ok" }

export type CheckForbiddenResponse =
    | { status: "ok", data: models.Message }
    | { status: "forbidden" }

export type SameOperationNameResponse =
    | { status: "ok" }
    | { status: "forbidden" }
