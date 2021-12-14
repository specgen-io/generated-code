import Router from '@koa/router'
import {zipHeaders} from './../params'
import * as t from './../superstruct'
import * as models from './models'
import {EchoService} from './echo_service'

export let echoRouter = (service: EchoService) => {
    let router = new Router()

    router.post('/echo/body', async (ctx) => {
        var body: models.Message
        try {
            body = t.decode(models.TMessage, ctx.request.body)
        } catch (error) {
            ctx.throw(400)
            return
        }
        try {
            let result = await service.echoBody({body})
            ctx.status = 200
            ctx.body = t.encode(models.TMessage, result)
            return
        } catch (error) {
            ctx.throw(500)
        }
    })

    return router
}
