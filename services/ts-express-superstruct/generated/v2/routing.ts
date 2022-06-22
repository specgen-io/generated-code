import {Router} from 'express'
import {Request, Response} from 'express'
import {zipHeaders} from './../params'
import * as t from './../superstruct'
import * as models from './models'
import {EchoService} from './echo'

export const echoRouter = (service: EchoService) => {
    const respondInternalServerError = (response: Response, error: models.InternalServerError) => {
        const body = t.encode(models.TInternalServerError, error)
        response.status(500).type("json").send(JSON.stringify(body))
    }
    
    const respondNotFound = (response: Response, error: models.NotFoundError) => {
        const body = t.encode(models.TNotFoundError, error)
        response.status(404).type("json").send(JSON.stringify(body))
    }
    
    const respondBadRequest = (response: Response, error: models.BadRequestError) => {
        const body = t.encode(models.TBadRequestError, error)
        response.status(400).type("json").send(JSON.stringify(body))
    }
    
    const assertContentType = (request: Request, response: Response, contentType: string): boolean => {
        if (!request.is(contentType)) {
            const error = {
                message: "Failed to parse header", 
                location: models.ErrorLocation.HEADER,
                errors: [{path: "Content-Type", code: "missing", message: `Expected Content-Type header: ${contentType}`}]
            }
            respondBadRequest(response, error)
            return false
        }
        return true
    }

    const router = Router()

    router.post('/echo/body_model', async (request: Request, response: Response) => {
        try {
            if (!assertContentType(request, response, "application/json")) {
                return
            }
            const bodyDecode = t.decodeR(models.TMessage, request.body)
            if (bodyDecode.error) {
                respondBadRequest(response, { message: "Failed to parse body", location: models.ErrorLocation.BODY, errors: bodyDecode.error })
                return
            }
            const body = bodyDecode.value
            const result = await service.echoBodyModel({body})
            response.status(200).type('json').send(JSON.stringify(t.encode(models.TMessage, result)))
            return
        } catch (error) {
            respondInternalServerError(response, { message: error instanceof Error ? error.message : "Unknown error" })
            return
        }
    })

    return router
}
