import {Router} from 'express'
import {Request, Response} from 'express'
import {zipHeaders} from './../params'
import * as t from './../superstruct'
import * as models from './models'
import {EchoService} from './echo_service'

export let echoRouter = (service: EchoService) => {
    let router = Router()

    router.post('/echo/body_model', async (request: Request, response: Response) => {
        if (!request.is('application/json')) {
            response.status(400).send()
            return
        }
        var body: models.Message
        try {
            body = t.decode(models.TMessage, request.body)
        } catch (error) {
            response.status(400).send()
            return
        }
        try {
            let result = await service.echoBodyModel({body})
            response.status(200).type('json').send(JSON.stringify(t.encode(models.TMessage, result)))
            return
        } catch (error) {
            response.status(500).send()
        }
    })

    return router
}
