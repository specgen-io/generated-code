import { AxiosInstance, AxiosRequestConfig } from 'axios'
import { params, stringify } from './../params'
import * as t from './../superstruct'
import * as models from './models'

export const client = (axiosInstance: AxiosInstance) => {
    return {
        axiosInstance,

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
    }
}
