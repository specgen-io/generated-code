import Router from '@koa/router'
import { ExtendableContext } from 'koa'
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

    router.post('/echo/body_string', async (ctx) => {
        try {
            if (!assertContentType(ctx, "text/plain")) {
                return
            }
            const body: string = ctx.request.rawBody
            const result = await service.echoBodyString({body})
            ctx.status = 200
            ctx.body = result
            return
        } catch (error) {
            respondInternalServerError(ctx, { message: error instanceof Error ? error.message : "Unknown error" })
            return
        }
    })

    router.post('/echo/body_model', async (ctx) => {
        try {
            if (!assertContentType(ctx, "application/json")) {
                return
            }
            const bodyDecode = t.decode(models.TMessage, ctx.request.body)
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

    router.post('/echo/body_array', async (ctx) => {
        try {
            if (!assertContentType(ctx, "application/json")) {
                return
            }
            const bodyDecode = t.decode(t.array(t.string()), ctx.request.body)
            if (bodyDecode.error) {
                respondBadRequest(ctx, { message: "Failed to parse body JSON", location: models.ErrorLocation.BODY, errors: bodyDecode.error })
                return
            }
            const body = bodyDecode.value
            const result = await service.echoBodyArray({body})
            ctx.status = 200
            ctx.body = t.encode(t.array(t.string()), result)
            return
        } catch (error) {
            respondInternalServerError(ctx, { message: error instanceof Error ? error.message : "Unknown error" })
            return
        }
    })

    router.post('/echo/body_map', async (ctx) => {
        try {
            if (!assertContentType(ctx, "application/json")) {
                return
            }
            const bodyDecode = t.decode(t.record(t.string(), t.string()), ctx.request.body)
            if (bodyDecode.error) {
                respondBadRequest(ctx, { message: "Failed to parse body JSON", location: models.ErrorLocation.BODY, errors: bodyDecode.error })
                return
            }
            const body = bodyDecode.value
            const result = await service.echoBodyMap({body})
            ctx.status = 200
            ctx.body = t.encode(t.record(t.string(), t.string()), result)
            return
        } catch (error) {
            respondInternalServerError(ctx, { message: error instanceof Error ? error.message : "Unknown error" })
            return
        }
    })

    router.get('/echo/query', async (ctx) => {
        try {
            const queryParamsDecode = t.decode(TEchoQueryQueryParams, ctx.request.query)
            if (queryParamsDecode.error) {
                respondBadRequest(ctx, { message: "Failed to parse query parameters", location: models.ErrorLocation.QUERY, errors: queryParamsDecode.error })
                return
            }
            const queryParams = queryParamsDecode.value
            const result = await service.echoQuery({...queryParams})
            ctx.status = 200
            ctx.body = t.encode(models.TParameters, result)
            return
        } catch (error) {
            respondInternalServerError(ctx, { message: error instanceof Error ? error.message : "Unknown error" })
            return
        }
    })

    router.get('/echo/header', async (ctx) => {
        try {
            const headerParamsDecode = t.decode(TEchoHeaderHeaderParams, zipHeaders(ctx.req.rawHeaders))
            if (headerParamsDecode.error) {
                respondBadRequest(ctx, { message: "Failed to parse header parameters", location: models.ErrorLocation.HEADER, errors: headerParamsDecode.error })
                return
            }
            const headerParams = headerParamsDecode.value
            const result = await service.echoHeader({...headerParams})
            ctx.status = 200
            ctx.body = t.encode(models.TParameters, result)
            return
        } catch (error) {
            respondInternalServerError(ctx, { message: error instanceof Error ? error.message : "Unknown error" })
            return
        }
    })

    router.get('/echo/url_params/:int_url/:long_url/:float_url/:double_url/:decimal_url/:bool_url/:string_url/:uuid_url/:date_url/:datetime_url/:enum_url', async (ctx) => {
        try {
            const urlParamsDecode = t.decode(TEchoUrlParamsUrlParams, ctx.params)
            if (urlParamsDecode.error) {
                respondNotFound(ctx, { message: "Failed to parse url parameters" })
                return
            }
            const urlParams = urlParamsDecode.value
            const result = await service.echoUrlParams({...urlParams})
            ctx.status = 200
            ctx.body = t.encode(models.TUrlParameters, result)
            return
        } catch (error) {
            respondInternalServerError(ctx, { message: error instanceof Error ? error.message : "Unknown error" })
            return
        }
    })

    router.post('/echo/everything/:date_url/:decimal_url', async (ctx) => {
        try {
            const urlParamsDecode = t.decode(TEchoEverythingUrlParams, ctx.params)
            if (urlParamsDecode.error) {
                respondNotFound(ctx, { message: "Failed to parse url parameters" })
                return
            }
            const urlParams = urlParamsDecode.value
            if (!assertContentType(ctx, "application/json")) {
                return
            }
            const headerParamsDecode = t.decode(TEchoEverythingHeaderParams, zipHeaders(ctx.req.rawHeaders))
            if (headerParamsDecode.error) {
                respondBadRequest(ctx, { message: "Failed to parse header parameters", location: models.ErrorLocation.HEADER, errors: headerParamsDecode.error })
                return
            }
            const headerParams = headerParamsDecode.value
            const queryParamsDecode = t.decode(TEchoEverythingQueryParams, ctx.request.query)
            if (queryParamsDecode.error) {
                respondBadRequest(ctx, { message: "Failed to parse query parameters", location: models.ErrorLocation.QUERY, errors: queryParamsDecode.error })
                return
            }
            const queryParams = queryParamsDecode.value
            const bodyDecode = t.decode(models.TMessage, ctx.request.body)
            if (bodyDecode.error) {
                respondBadRequest(ctx, { message: "Failed to parse body JSON", location: models.ErrorLocation.BODY, errors: bodyDecode.error })
                return
            }
            const body = bodyDecode.value
            const result = await service.echoEverything({body, ...urlParams, ...headerParams, ...queryParams})
            switch (result.status) {
                case 'ok':
                    ctx.status = 200
                    ctx.body = t.encode(models.TEverything, result.data)
                    return
                case 'forbidden':
                    ctx.status = 403
                    return
            }
        } catch (error) {
            respondInternalServerError(ctx, { message: error instanceof Error ? error.message : "Unknown error" })
            return
        }
    })

    router.get('/echo/same_operation_name', async (ctx) => {
        try {
            const result = await service.sameOperationName()
            switch (result.status) {
                case 'ok':
                    ctx.status = 200
                    return
                case 'forbidden':
                    ctx.status = 403
                    return
            }
        } catch (error) {
            respondInternalServerError(ctx, { message: error instanceof Error ? error.message : "Unknown error" })
            return
        }
    })

    return router
}

export const checkRouter = (service: CheckService) => {
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

    router.get('/check/empty', async (ctx) => {
        try {
            await service.checkEmpty()
            ctx.status = 200
            return
        } catch (error) {
            respondInternalServerError(ctx, { message: error instanceof Error ? error.message : "Unknown error" })
            return
        }
    })

    router.post('/check/empty_response', async (ctx) => {
        try {
            if (!assertContentType(ctx, "application/json")) {
                return
            }
            const bodyDecode = t.decode(models.TMessage, ctx.request.body)
            if (bodyDecode.error) {
                respondBadRequest(ctx, { message: "Failed to parse body JSON", location: models.ErrorLocation.BODY, errors: bodyDecode.error })
                return
            }
            const body = bodyDecode.value
            await service.checkEmptyResponse({body})
            ctx.status = 200
            return
        } catch (error) {
            respondInternalServerError(ctx, { message: error instanceof Error ? error.message : "Unknown error" })
            return
        }
    })

    router.get('/check/forbidden', async (ctx) => {
        try {
            const result = await service.checkForbidden()
            switch (result.status) {
                case 'ok':
                    ctx.status = 200
                    ctx.body = t.encode(models.TMessage, result.data)
                    return
                case 'forbidden':
                    ctx.status = 403
                    return
            }
        } catch (error) {
            respondInternalServerError(ctx, { message: error instanceof Error ? error.message : "Unknown error" })
            return
        }
    })

    router.get('/check/same_operation_name', async (ctx) => {
        try {
            const result = await service.sameOperationName()
            switch (result.status) {
                case 'ok':
                    ctx.status = 200
                    return
                case 'forbidden':
                    ctx.status = 403
                    return
            }
        } catch (error) {
            respondInternalServerError(ctx, { message: error instanceof Error ? error.message : "Unknown error" })
            return
        }
    })

    return router
}
