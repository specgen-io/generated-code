import {Router} from 'express'
import {Request, Response} from 'express'
import * as t from './../superstruct'
import * as models from './models'
import {EchoService} from './echo_service'

export let echoRouter = (service: EchoService) => {
    let router = Router()

    router.post('/echo/body', async (request: Request, response: Response) => {
        var body: models.Message
        try {
            body = t.decode(models.TMessage, request.body)
        } catch (error) {
            response.status(400).send()
            return
        }
        try {
            let result = await service.echoBody({body})
            switch (result.status) {
                case 'ok':
                    response.status(200).type('json').send(JSON.stringify(t.encode(models.TMessage, result.data)))
            }
        } catch (error) {
            response.status(500).send()
        }
    })

    return router
}