import { strParamsItems, stringify } from './../params'
import * as t from './../superstruct'
import * as models from './models'

export const client = (config: {baseURL: string}) => {
    return {
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
    }
}
