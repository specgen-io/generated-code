import { URL, URLSearchParams } from 'url'
import fetch from 'node-fetch'
import { strParamsItems, stringify } from './params'
import * as t from './io-ts'
import * as models from './models'

export const client = (config: {baseURL: string}) => {
    return {
        echoBodyString: async (parameters: {body: string}): Promise<string> => {
            const headers = strParamsItems({
                "Content-Type": "text/plain"
            })
            const url = config.baseURL+`/echo/body_string`
            const response = await fetch(url, {method: 'POST', headers: headers, body: parameters.body})
            switch (response.status) {
                case 200:
                    return Promise.resolve(await response.text())
                default:
                    throw new Error(`Unexpected status code ${ response.status }`)
            }
        },

        echoBodyModel: async (parameters: {body: models.Message}): Promise<models.Message> => {
            const headers = strParamsItems({
                "Content-Type": "application/json"
            })
            const url = config.baseURL+`/echo/body_model`
            const bodyJson = t.encode(models.TMessage, parameters.body)
            const response = await fetch(url, {method: 'POST', headers: headers, body: JSON.stringify(bodyJson)})
            switch (response.status) {
                case 200:
                    return Promise.resolve(t.decode(models.TMessage, await response.json()))
                default:
                    throw new Error(`Unexpected status code ${ response.status }`)
            }
        },

        echoBodyArray: async (parameters: {body: string[]}): Promise<string[]> => {
            const headers = strParamsItems({
                "Content-Type": "application/json"
            })
            const url = config.baseURL+`/echo/body_array`
            const bodyJson = t.encode(t.array(t.string), parameters.body)
            const response = await fetch(url, {method: 'POST', headers: headers, body: JSON.stringify(bodyJson)})
            switch (response.status) {
                case 200:
                    return Promise.resolve(t.decode(t.array(t.string), await response.json()))
                default:
                    throw new Error(`Unexpected status code ${ response.status }`)
            }
        },

        echoBodyMap: async (parameters: {body: Record<string, string>}): Promise<Record<string, string>> => {
            const headers = strParamsItems({
                "Content-Type": "application/json"
            })
            const url = config.baseURL+`/echo/body_map`
            const bodyJson = t.encode(t.record(t.string, t.string), parameters.body)
            const response = await fetch(url, {method: 'POST', headers: headers, body: JSON.stringify(bodyJson)})
            switch (response.status) {
                case 200:
                    return Promise.resolve(t.decode(t.record(t.string, t.string), await response.json()))
                default:
                    throw new Error(`Unexpected status code ${ response.status }`)
            }
        },

        echoQuery: async (parameters: {intQuery: number, longQuery: number, floatQuery: number, doubleQuery: number, decimalQuery: number, boolQuery: boolean, stringQuery: string, stringArrayQuery: string[], uuidQuery: string, dateQuery: string, dateArrayQuery: string[], datetimeQuery: Date, enumQuery: models.Choice, stringOptQuery?: string | undefined, stringDefaultedQuery?: string | undefined}): Promise<models.Parameters> => {
            const query = strParamsItems({
                "int_query": parameters.intQuery,
                "long_query": parameters.longQuery,
                "float_query": parameters.floatQuery,
                "double_query": parameters.doubleQuery,
                "decimal_query": parameters.decimalQuery,
                "bool_query": parameters.boolQuery,
                "string_query": parameters.stringQuery,
                "string_opt_query": parameters.stringOptQuery,
                "string_defaulted_query": parameters.stringDefaultedQuery,
                "string_array_query": parameters.stringArrayQuery,
                "uuid_query": parameters.uuidQuery,
                "date_query": parameters.dateQuery,
                "date_array_query": parameters.dateArrayQuery,
                "datetime_query": parameters.datetimeQuery,
                "enum_query": parameters.enumQuery,
            })
            const url = config.baseURL+`/echo/query?${new URLSearchParams(query)}`
            const response = await fetch(url, {method: 'GET'})
            switch (response.status) {
                case 200:
                    return Promise.resolve(t.decode(models.TParameters, await response.json()))
                default:
                    throw new Error(`Unexpected status code ${ response.status }`)
            }
        },

        echoHeader: async (parameters: {intHeader: number, longHeader: number, floatHeader: number, doubleHeader: number, decimalHeader: number, boolHeader: boolean, stringHeader: string, stringArrayHeader: string[], uuidHeader: string, dateHeader: string, dateArrayHeader: string[], datetimeHeader: Date, enumHeader: models.Choice, stringOptHeader?: string | undefined, stringDefaultedHeader?: string | undefined}): Promise<models.Parameters> => {
            const headers = strParamsItems({
                "Int-Header": parameters.intHeader,
                "Long-Header": parameters.longHeader,
                "Float-Header": parameters.floatHeader,
                "Double-Header": parameters.doubleHeader,
                "Decimal-Header": parameters.decimalHeader,
                "Bool-Header": parameters.boolHeader,
                "String-Header": parameters.stringHeader,
                "String-Opt-Header": parameters.stringOptHeader,
                "String-Defaulted-Header": parameters.stringDefaultedHeader,
                "String-Array-Header": parameters.stringArrayHeader,
                "Uuid-Header": parameters.uuidHeader,
                "Date-Header": parameters.dateHeader,
                "Date-Array-Header": parameters.dateArrayHeader,
                "Datetime-Header": parameters.datetimeHeader,
                "Enum-Header": parameters.enumHeader,
            })
            const url = config.baseURL+`/echo/header`
            const response = await fetch(url, {method: 'GET', headers: headers})
            switch (response.status) {
                case 200:
                    return Promise.resolve(t.decode(models.TParameters, await response.json()))
                default:
                    throw new Error(`Unexpected status code ${ response.status }`)
            }
        },

        echoUrlParams: async (parameters: {intUrl: number, longUrl: number, floatUrl: number, doubleUrl: number, decimalUrl: number, boolUrl: boolean, stringUrl: string, uuidUrl: string, dateUrl: string, datetimeUrl: Date, enumUrl: models.Choice}): Promise<models.UrlParameters> => {
            const url = config.baseURL+`/echo/url_params/${stringify(parameters.intUrl)}/${stringify(parameters.longUrl)}/${stringify(parameters.floatUrl)}/${stringify(parameters.doubleUrl)}/${stringify(parameters.decimalUrl)}/${stringify(parameters.boolUrl)}/${stringify(parameters.stringUrl)}/${stringify(parameters.uuidUrl)}/${stringify(parameters.dateUrl)}/${stringify(parameters.datetimeUrl)}/${stringify(parameters.enumUrl)}`
            const response = await fetch(url, {method: 'GET'})
            switch (response.status) {
                case 200:
                    return Promise.resolve(t.decode(models.TUrlParameters, await response.json()))
                default:
                    throw new Error(`Unexpected status code ${ response.status }`)
            }
        },

        echoEverything: async (parameters: {uuidHeader: string, datetimeHeader: Date, body: models.Message, dateUrl: string, decimalUrl: number, floatQuery: number, boolQuery: boolean}): Promise<EchoEverythingResponse> => {
            const query = strParamsItems({
                "float_query": parameters.floatQuery,
                "bool_query": parameters.boolQuery,
            })
            const headers = strParamsItems({
                "Uuid-Header": parameters.uuidHeader,
                "Datetime-Header": parameters.datetimeHeader,
                "Content-Type": "application/json"
            })
            const url = config.baseURL+`/echo/everything/${stringify(parameters.dateUrl)}/${stringify(parameters.decimalUrl)}?${new URLSearchParams(query)}`
            const bodyJson = t.encode(models.TMessage, parameters.body)
            const response = await fetch(url, {method: 'POST', headers: headers, body: JSON.stringify(bodyJson)})
            switch (response.status) {
                case 200:
                    return Promise.resolve({ status: "ok", data: t.decode(models.TEverything, await response.json()) })
                case 403:
                    return Promise.resolve({ status: "forbidden" })
                default:
                    throw new Error(`Unexpected status code ${ response.status }`)
            }
        },

        sameOperationName: async (): Promise<SameOperationNameResponse> => {
            const url = config.baseURL+`/echo/same_operation_name`
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

export type EchoEverythingResponse =
    | { status: "ok", data: models.Everything }
    | { status: "forbidden" }

export type SameOperationNameResponse =
    | { status: "ok" }
    | { status: "forbidden" }
