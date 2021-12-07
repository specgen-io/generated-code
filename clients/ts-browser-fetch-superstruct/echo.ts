import { params, stringify } from './params'
import * as t from './superstruct'
import * as models from './models'

export const client = (config: {baseURL: string}) => {
    return {
        echoBodyString: async (parameters: {body: string}): Promise<string> => {
            const url = config.baseURL+`/echo/body_string`
            const response = await fetch(url, {method: 'POST', body: parameters.body})
            switch (response.status) {
                case 200:
                    return Promise.resolve(await response.text())
                default:
                    throw new Error(`Unexpected status code ${ response.status }`)
            }
        },

        echoBody: async (parameters: {body: models.Message}): Promise<models.Message> => {
            const url = config.baseURL+`/echo/body`
            const bodyJson = t.encode(models.TMessage, parameters.body)
            const response = await fetch(url, {method: 'POST', body: JSON.stringify(bodyJson)})
            switch (response.status) {
                case 200:
                    return Promise.resolve(t.decode(models.TMessage, await response.json()))
                default:
                    throw new Error(`Unexpected status code ${ response.status }`)
            }
        },

        echoQuery: async (parameters: {intQuery: number, stringQuery: string}): Promise<models.Message> => {
            const query = params({
                "int_query": parameters.intQuery,
                "string_query": parameters.stringQuery,
            })
            const url = config.baseURL+`/echo/query?${new URLSearchParams(query)}`
            const response = await fetch(url, {method: 'GET'})
            switch (response.status) {
                case 200:
                    return Promise.resolve(t.decode(models.TMessage, await response.json()))
                default:
                    throw new Error(`Unexpected status code ${ response.status }`)
            }
        },

        echoHeader: async (parameters: {intHeader: number, stringHeader: string}): Promise<models.Message> => {
            const headers = params({
                "Int-Header": parameters.intHeader,
                "String-Header": parameters.stringHeader,
            })
            const url = config.baseURL+`/echo/header`
            const response = await fetch(url, {method: 'GET', headers: headers})
            switch (response.status) {
                case 200:
                    return Promise.resolve(t.decode(models.TMessage, await response.json()))
                default:
                    throw new Error(`Unexpected status code ${ response.status }`)
            }
        },

        echoUrlParams: async (parameters: {intUrl: number, stringUrl: string}): Promise<models.Message> => {
            const url = config.baseURL+`/echo/url_params/${stringify(parameters.intUrl)}/${stringify(parameters.stringUrl)}`
            const response = await fetch(url, {method: 'GET'})
            switch (response.status) {
                case 200:
                    return Promise.resolve(t.decode(models.TMessage, await response.json()))
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

export type SameOperationNameResponse =
    | { status: "ok" }
    | { status: "forbidden" }
