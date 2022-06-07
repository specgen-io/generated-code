import Router from '@koa/router'
import { ExtendableContext } from 'koa'
import {zipHeaders} from './../params'
import * as t from './../superstruct'
import * as models from './models'
import {EchoService} from './echo'

export const echoRouter = (service: EchoService) => {
    const respondInternalServerError = (ctx: ExtendableContext, error: models.InternalServerError) => {
        const body = t.encode(models.TInternalServerError, error)
        ctx.status = 500
        ctx.body = body
    }
    
    const respondNotFound = (ctx: ExtendableContext, error: models.NotFoundError) => {
        const body = t.encode(models.TNotFoundError, error)
        ctx.status = 404
        ctx.body = body
    }
    
    const respondBadRequest = (ctx: ExtendableContext, error: models.BadRequestError) => {
        const body = t.encode(models.TBadRequestError, error)
        ctx.status = 400
        ctx.body = body
    }
    
    const assertContentType = (ctx: ExtendableContext, contentType: string): boolean => {
        if (ctx.request.type != contentType) {
            const message = `Expected Content-Type header: ${contentType}`
            const error = {
                message, 
                location: models.ErrorLocation.HEADER,
                errors: [{path: "Content-Type", code: "wrong_value", message}]
            }
            respondBadRequest(ctx, error)
            return false
        }
        return true
    }

    const router = new Router()

    router.post('/echo/body_model', async (ctx) => {
        try {
            if (!assertContentType(ctx, "application/json")) {
                return
            }
            const bodyDecode = t.decodeR(models.TMessage, ctx.request.body)
            if (bodyDecode.error) {
                respondBadRequest(ctx, { message: "Failed to parse body JSON", location: models.ErrorLocation.BODY, errors: bodyDecode.error })
                return
            }
            const body = bodyDecode.value
            const result = await service.echoBodyModel({body})
            ctx.status = 200
            ctx.body = t.encode(models.TMessage, result)
            return
        } catch (error) {
            respondInternalServerError(ctx, { message: error instanceof Error ? error.message : "Unknown error" })
            return
        }
    })

    return router
}
