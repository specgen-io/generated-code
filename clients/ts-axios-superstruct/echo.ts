import { AxiosInstance, AxiosRequestConfig } from 'axios'
import { strParamsItems, strParamsObject, stringify } from './params'
import * as t from './superstruct'
import * as models from './models'

export const client = (axiosInstance: AxiosInstance) => {
    return {
        axiosInstance,

        echoBodyString: async (parameters: {body: string}): Promise<string> => {
            const response = await axiosInstance.post(`/echo/body_string`, parameters.body, {})
            switch (response.status) {
                case 200:
                    return Promise.resolve(response.data)
                default:
                    throw new Error(`Unexpected status code ${ response.status }`)
            }
        },

        echoBody: async (parameters: {body: models.Message}): Promise<models.Message> => {
            const bodyJson = t.encode(models.TMessage, parameters.body)
            const response = await axiosInstance.post(`/echo/body`, bodyJson, {})
            switch (response.status) {
                case 200:
                    return Promise.resolve(t.decode(models.TMessage, response.data))
                default:
                    throw new Error(`Unexpected status code ${ response.status }`)
            }
        },

        echoQuery: async (parameters: {intQuery: number, stringQuery: string}): Promise<models.Message> => {
            const query = strParamsItems({
                "int_query": parameters.intQuery,
                "string_query": parameters.stringQuery,
            })
            const response = await axiosInstance.get(`/echo/query`, {params: new URLSearchParams(query)})
            switch (response.status) {
                case 200:
                    return Promise.resolve(t.decode(models.TMessage, response.data))
                default:
                    throw new Error(`Unexpected status code ${ response.status }`)
            }
        },

        echoHeader: async (parameters: {intHeader: number, stringHeader: string}): Promise<models.Message> => {
            const headers = strParamsObject({
                "Int-Header": parameters.intHeader,
                "String-Header": parameters.stringHeader,
            })
            const response = await axiosInstance.get(`/echo/header`, {headers: headers})
            switch (response.status) {
                case 200:
                    return Promise.resolve(t.decode(models.TMessage, response.data))
                default:
                    throw new Error(`Unexpected status code ${ response.status }`)
            }
        },

        echoUrlParams: async (parameters: {intUrl: number, stringUrl: string}): Promise<models.Message> => {
            const response = await axiosInstance.get(`/echo/url_params/${stringify(parameters.intUrl)}/${stringify(parameters.stringUrl)}`, {})
            switch (response.status) {
                case 200:
                    return Promise.resolve(t.decode(models.TMessage, response.data))
                default:
                    throw new Error(`Unexpected status code ${ response.status }`)
            }
        },

        sameOperationName: async (): Promise<SameOperationNameResponse> => {
            const response = await axiosInstance.get(`/echo/same_operation_name`, {})
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
