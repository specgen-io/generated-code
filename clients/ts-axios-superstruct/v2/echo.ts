import { AxiosInstance, AxiosRequestConfig } from 'axios'
import { strParamsItems, strParamsObject, stringify } from './../params'
import * as t from './../superstruct'
import * as models from './models'

export const client = (axiosInstance: AxiosInstance) => {
    return {
        axiosInstance,

        echoBodyModel: async (parameters: {body: models.Message}): Promise<models.Message> => {
            const headers = strParamsObject({
                "Content-Type": "application/json"
            })
            const bodyJson = t.encode(models.TMessage, parameters.body)
            const response = await axiosInstance.post(`/echo/body_model`, bodyJson, {headers: headers})
            switch (response.status) {
                case 200:
                    return Promise.resolve(t.decode(models.TMessage, response.data))
                default:
                    throw new Error(`Unexpected status code ${ response.status }`)
            }
        },
    }
}
