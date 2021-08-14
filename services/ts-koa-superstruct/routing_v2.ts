import Router from '@koa/router'
import * as t from './superstruct'
import * as models from './models_v2'
import * as services from './services_v2'

export let echoRouter = (service: services.EchoService) => {
    let router = new Router()

    router.post('/echo/body', async (ctx) => {
        var body: models.Message
        try {
            body = t.decode(models.TMessage, ctx.request.body)
        } catch (error) {
            ctx.throw(400, error)
            return
        }
        try {
            let result = await service.echoBody({body})
            switch (result.status) {
                case 'ok':
                    ctx.status = 200
        ctx.body = t.encode(models.TMessage, result.data)
            }
        } catch (error) {
            ctx.throw(500)
        }
    })

    return router
}
