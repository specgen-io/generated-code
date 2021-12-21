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

        checkQuery: async (parameters: {pString: string, pStringArray: string[], pDate: string, pDateArray: string[], pDatetime: Date, pInt: number, pLong: number, pDecimal: number, pEnum: models.Choice, pStringOpt?: string | undefined, pStringDefaulted?: string | undefined}): Promise<void> => {
            const query = strParamsItems({
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
            })
            const url = config.baseURL+`/check/query?${new URLSearchParams(query)}`
            const response = await fetch(url, {method: 'GET'})
            switch (response.status) {
                case 200:
                    return Promise.resolve()
                default:
                    throw new Error(`Unexpected status code ${ response.status }`)
            }
        },

        checkUrlParams: async (parameters: {intUrl: number, stringUrl: string, floatUrl: number, boolUrl: boolean, uuidUrl: string, decimalUrl: number, dateUrl: string, enumUrl: models.Choice}): Promise<void> => {
            const url = config.baseURL+`/check/url_params/${stringify(parameters.intUrl)}/${stringify(parameters.stringUrl)}/${stringify(parameters.floatUrl)}/${stringify(parameters.boolUrl)}/${stringify(parameters.uuidUrl)}/${stringify(parameters.decimalUrl)}/${stringify(parameters.dateUrl)}/${stringify(parameters.enumUrl)}`
            const response = await fetch(url, {method: 'GET'})
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
