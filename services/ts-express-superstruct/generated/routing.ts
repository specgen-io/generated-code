import {Router} from 'express'
import {Request, Response} from 'express'
import {zipHeaders} from './params'
import * as t from './superstruct'
import * as models from './models'
import {EchoService} from './echo'
import {CheckService} from './check'

const TEchoQueryQueryParams = t.type({
    int_query: t.StrInteger,
    long_query: t.StrInteger,
    float_query: t.StrFloat,
    double_query: t.StrFloat,
    decimal_query: t.StrFloat,
    bool_query: t.StrBoolean,
    string_query: t.string(),
    string_opt_query: t.optional(t.string()),
    string_defaulted_query: t.defaulted(t.string(), "the default value"),
    string_array_query: t.array(t.string()),
    uuid_query: t.string(),
    date_query: t.string(),
    date_array_query: t.array(t.string()),
    datetime_query: t.StrDateTime,
    enum_query: models.TChoice,
})

type EchoQueryQueryParams = t.Infer<typeof TEchoQueryQueryParams>

const TEchoHeaderHeaderParams = t.type({
    'Int-Header': t.StrInteger,
    'Long-Header': t.StrInteger,
    'Float-Header': t.StrFloat,
    'Double-Header': t.StrFloat,
    'Decimal-Header': t.StrFloat,
    'Bool-Header': t.StrBoolean,
    'String-Header': t.string(),
    'String-Opt-Header': t.optional(t.string()),
    'String-Defaulted-Header': t.defaulted(t.string(), "the default value"),
    'String-Array-Header': t.array(t.string()),
    'Uuid-Header': t.string(),
    'Date-Header': t.string(),
    'Date-Array-Header': t.array(t.string()),
    'Datetime-Header': t.StrDateTime,
    'Enum-Header': models.TChoice,
})

type EchoHeaderHeaderParams = t.Infer<typeof TEchoHeaderHeaderParams>

const TEchoUrlParamsUrlParams = t.type({
    int_url: t.StrInteger,
    long_url: t.StrInteger,
    float_url: t.StrFloat,
    double_url: t.StrFloat,
    decimal_url: t.StrFloat,
    bool_url: t.StrBoolean,
    string_url: t.string(),
    uuid_url: t.string(),
    date_url: t.string(),
    datetime_url: t.StrDateTime,
    enum_url: models.TChoice,
})

type EchoUrlParamsUrlParams = t.Infer<typeof TEchoUrlParamsUrlParams>

const TEchoEverythingHeaderParams = t.type({
    'Uuid-Header': t.string(),
    'Datetime-Header': t.StrDateTime,
})

type EchoEverythingHeaderParams = t.Infer<typeof TEchoEverythingHeaderParams>

const TEchoEverythingUrlParams = t.type({
    date_url: t.string(),
    decimal_url: t.StrFloat,
})

type EchoEverythingUrlParams = t.Infer<typeof TEchoEverythingUrlParams>

const TEchoEverythingQueryParams = t.type({
    float_query: t.StrFloat,
    bool_query: t.StrBoolean,
})

type EchoEverythingQueryParams = t.Infer<typeof TEchoEverythingQueryParams>

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

    router.post('/echo/body_string', async (request: Request, response: Response) => {
        try {
            if (!assertContentType(request, response, "text/plain")) {
                return
            }
            const body: string = request.body
            const result = await service.echoBodyString({body})
            response.status(200).type('text').send(result)
            return
        } catch (error) {
            respondInternalServerError(response, { message: error instanceof Error ? error.message : "Unknown error" })
            return
        }
    })

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

    router.post('/echo/body_array', async (request: Request, response: Response) => {
        try {
            if (!assertContentType(request, response, "application/json")) {
                return
            }
            const bodyDecode = t.decodeR(t.array(t.string()), request.body)
            if (bodyDecode.error) {
                respondBadRequest(response, { message: "Failed to parse body", location: models.ErrorLocation.BODY, errors: bodyDecode.error })
                return
            }
            const body = bodyDecode.value
            const result = await service.echoBodyArray({body})
            response.status(200).type('json').send(JSON.stringify(t.encode(t.array(t.string()), result)))
            return
        } catch (error) {
            respondInternalServerError(response, { message: error instanceof Error ? error.message : "Unknown error" })
            return
        }
    })

    router.post('/echo/body_map', async (request: Request, response: Response) => {
        try {
            if (!assertContentType(request, response, "application/json")) {
                return
            }
            const bodyDecode = t.decodeR(t.record(t.string(), t.string()), request.body)
            if (bodyDecode.error) {
                respondBadRequest(response, { message: "Failed to parse body", location: models.ErrorLocation.BODY, errors: bodyDecode.error })
                return
            }
            const body = bodyDecode.value
            const result = await service.echoBodyMap({body})
            response.status(200).type('json').send(JSON.stringify(t.encode(t.record(t.string(), t.string()), result)))
            return
        } catch (error) {
            respondInternalServerError(response, { message: error instanceof Error ? error.message : "Unknown error" })
            return
        }
    })

    router.get('/echo/query', async (request: Request, response: Response) => {
        try {
            const queryParamsDecode = t.decodeR(TEchoQueryQueryParams, request.query)
            if (queryParamsDecode.error) {
                respondBadRequest(response, { message: "Failed to parse query", location: models.ErrorLocation.QUERY, errors: queryParamsDecode.error })
                return
            }
            const queryParams = queryParamsDecode.value
            const result = await service.echoQuery({...queryParams})
            response.status(200).type('json').send(JSON.stringify(t.encode(models.TParameters, result)))
            return
        } catch (error) {
            respondInternalServerError(response, { message: error instanceof Error ? error.message : "Unknown error" })
            return
        }
    })

    router.get('/echo/header', async (request: Request, response: Response) => {
        try {
            const headerParamsDecode = t.decodeR(TEchoHeaderHeaderParams, zipHeaders(request.rawHeaders))
            if (headerParamsDecode.error) {
                respondBadRequest(response, { message: "Failed to parse header", location: models.ErrorLocation.HEADER, errors: headerParamsDecode.error })
                return
            }
            const headerParams = headerParamsDecode.value
            const result = await service.echoHeader({...headerParams})
            response.status(200).type('json').send(JSON.stringify(t.encode(models.TParameters, result)))
            return
        } catch (error) {
            respondInternalServerError(response, { message: error instanceof Error ? error.message : "Unknown error" })
            return
        }
    })

    router.get('/echo/url_params/:int_url/:long_url/:float_url/:double_url/:decimal_url/:bool_url/:string_url/:uuid_url/:date_url/:datetime_url/:enum_url', async (request: Request, response: Response) => {
        try {
            const urlParamsDecode = t.decodeR(TEchoUrlParamsUrlParams, request.params)
            if (urlParamsDecode.error) {
                respondNotFound(response, { message: "Failed to parse url parameters" })
                return
            }
            const urlParams = urlParamsDecode.value
            const result = await service.echoUrlParams({...urlParams})
            response.status(200).type('json').send(JSON.stringify(t.encode(models.TUrlParameters, result)))
            return
        } catch (error) {
            respondInternalServerError(response, { message: error instanceof Error ? error.message : "Unknown error" })
            return
        }
    })

    router.post('/echo/everything/:date_url/:decimal_url', async (request: Request, response: Response) => {
        try {
            const urlParamsDecode = t.decodeR(TEchoEverythingUrlParams, request.params)
            if (urlParamsDecode.error) {
                respondNotFound(response, { message: "Failed to parse url parameters" })
                return
            }
            const urlParams = urlParamsDecode.value
            if (!assertContentType(request, response, "application/json")) {
                return
            }
            const headerParamsDecode = t.decodeR(TEchoEverythingHeaderParams, zipHeaders(request.rawHeaders))
            if (headerParamsDecode.error) {
                respondBadRequest(response, { message: "Failed to parse header", location: models.ErrorLocation.HEADER, errors: headerParamsDecode.error })
                return
            }
            const headerParams = headerParamsDecode.value
            const queryParamsDecode = t.decodeR(TEchoEverythingQueryParams, request.query)
            if (queryParamsDecode.error) {
                respondBadRequest(response, { message: "Failed to parse query", location: models.ErrorLocation.QUERY, errors: queryParamsDecode.error })
                return
            }
            const queryParams = queryParamsDecode.value
            const bodyDecode = t.decodeR(models.TMessage, request.body)
            if (bodyDecode.error) {
                respondBadRequest(response, { message: "Failed to parse body", location: models.ErrorLocation.BODY, errors: bodyDecode.error })
                return
            }
            const body = bodyDecode.value
            const result = await service.echoEverything({body, ...urlParams, ...headerParams, ...queryParams})
            switch (result.status) {
                case 'ok':
                    response.status(200).type('json').send(JSON.stringify(t.encode(models.TEverything, result.data)))
                    return
                case 'forbidden':
                    response.status(403).send()
                    return
            }
        } catch (error) {
            respondInternalServerError(response, { message: error instanceof Error ? error.message : "Unknown error" })
            return
        }
    })

    router.get('/echo/same_operation_name', async (request: Request, response: Response) => {
        try {
            const result = await service.sameOperationName()
            switch (result.status) {
                case 'ok':
                    response.status(200).send()
                    return
                case 'forbidden':
                    response.status(403).send()
                    return
            }
        } catch (error) {
            respondInternalServerError(response, { message: error instanceof Error ? error.message : "Unknown error" })
            return
        }
    })

    return router
}

export const checkRouter = (service: CheckService) => {
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

    router.get('/check/empty', async (request: Request, response: Response) => {
        try {
            await service.checkEmpty()
            response.status(200).send()
            return
        } catch (error) {
            respondInternalServerError(response, { message: error instanceof Error ? error.message : "Unknown error" })
            return
        }
    })

    router.post('/check/empty_response', async (request: Request, response: Response) => {
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
            await service.checkEmptyResponse({body})
            response.status(200).send()
            return
        } catch (error) {
            respondInternalServerError(response, { message: error instanceof Error ? error.message : "Unknown error" })
            return
        }
    })

    router.get('/check/forbidden', async (request: Request, response: Response) => {
        try {
            const result = await service.checkForbidden()
            switch (result.status) {
                case 'ok':
                    response.status(200).type('json').send(JSON.stringify(t.encode(models.TMessage, result.data)))
                    return
                case 'forbidden':
                    response.status(403).send()
                    return
            }
        } catch (error) {
            respondInternalServerError(response, { message: error instanceof Error ? error.message : "Unknown error" })
            return
        }
    })

    router.get('/check/same_operation_name', async (request: Request, response: Response) => {
        try {
            const result = await service.sameOperationName()
            switch (result.status) {
                case 'ok':
                    response.status(200).send()
                    return
                case 'forbidden':
                    response.status(403).send()
                    return
            }
        } catch (error) {
            respondInternalServerError(response, { message: error instanceof Error ? error.message : "Unknown error" })
            return
        }
    })

    return router
}
