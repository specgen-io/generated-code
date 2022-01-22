import { URL, URLSearchParams } from 'url'
import fetch from 'node-fetch'
import { strParamsItems, stringify } from './../params'
import * as t from './../io-ts'
import * as models from './models'

export const client = (config: {baseURL: string}) => {
    return {
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
    }
}
