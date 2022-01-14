import Router from '@koa/router'
import {zipHeaders} from './params'
import * as t from './superstruct'
import * as models from './models'
import {EchoService} from './echo_service'
import {CheckService} from './check_service'

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

export let echoRouter = (service: EchoService) => {
    let router = new Router()

    router.post('/echo/body_string', async (ctx) => {
        const body: string = ctx.request.rawBody
        try {
            let result = await service.echoBodyString({body})
            ctx.status = 200
            ctx.body = result
            return
        } catch (error) {
            ctx.throw(500)
        }
    })

    router.post('/echo/body', async (ctx) => {
        var body: models.Message
        try {
            body = t.decode(models.TMessage, ctx.request.body)
        } catch (error) {
            response.status(400).send()
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

    router.get('/echo/query', async (ctx) => {
        var queryParams: EchoQueryQueryParams
        try {
            queryParams = t.decode(TEchoQueryQueryParams, ctx.request.query)
        } catch (error) {
            ctx.throw(400)
            return
        }
        try {
            let result = await service.echoQuery({...queryParams})
            ctx.status = 200
            ctx.body = t.encode(models.TParameters, result)
            return
        } catch (error) {
            ctx.throw(500)
        }
    })

    router.get('/echo/header', async (ctx) => {
        var headerParams: EchoHeaderHeaderParams
        try {
            headerParams = t.decode(TEchoHeaderHeaderParams, zipHeaders(ctx.req.rawHeaders))
        } catch (error) {
            ctx.throw(400)
            return
        }
        try {
            let result = await service.echoHeader({...headerParams})
            ctx.status = 200
            ctx.body = t.encode(models.TParameters, result)
            return
        } catch (error) {
            ctx.throw(500)
        }
    })

    router.get('/echo/url_params/:int_url/:long_url/:float_url/:double_url/:decimal_url/:bool_url/:string_url/:uuid_url/:date_url/:datetime_url/:enum_url', async (ctx) => {
        var urlParams: EchoUrlParamsUrlParams
        try {
            urlParams = t.decode(TEchoUrlParamsUrlParams, ctx.params)
        } catch (error) {
            ctx.throw(400)
            return
        }
        try {
            let result = await service.echoUrlParams({...urlParams})
            ctx.status = 200
            ctx.body = t.encode(models.TUrlParameters, result)
            return
        } catch (error) {
            ctx.throw(500)
        }
    })

    router.post('/echo/everything/:date_url/:decimal_url', async (ctx) => {
        var urlParams: EchoEverythingUrlParams
        var headerParams: EchoEverythingHeaderParams
        var queryParams: EchoEverythingQueryParams
        try {
            urlParams = t.decode(TEchoEverythingUrlParams, ctx.params)
            headerParams = t.decode(TEchoEverythingHeaderParams, zipHeaders(ctx.req.rawHeaders))
            queryParams = t.decode(TEchoEverythingQueryParams, ctx.request.query)
        } catch (error) {
            ctx.throw(400)
            return
        }
        var body: models.Message
        try {
            body = t.decode(models.TMessage, ctx.request.body)
        } catch (error) {
            response.status(400).send()
            return
        }
        try {
            let result = await service.echoEverything({body, ...urlParams, ...headerParams, ...queryParams})
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
            ctx.throw(500)
        }
    })

    router.get('/echo/same_operation_name', async (ctx) => {
        try {
            let result = await service.sameOperationName()
            switch (result.status) {
                case 'ok':
                    ctx.status = 200
                    return
                case 'forbidden':
                    ctx.status = 403
                    return
            }
        } catch (error) {
            ctx.throw(500)
        }
    })

    return router
}

export let checkRouter = (service: CheckService) => {
    let router = new Router()

    router.get('/check/empty', async (ctx) => {
        try {
            await service.checkEmpty()
            ctx.status = 200
            return
        } catch (error) {
            ctx.throw(500)
        }
    })

    router.post('/check/empty_response', async (ctx) => {
        var body: models.Message
        try {
            body = t.decode(models.TMessage, ctx.request.body)
        } catch (error) {
            response.status(400).send()
            return
        }
        try {
            await service.checkEmptyResponse({body})
            ctx.status = 200
            return
        } catch (error) {
            ctx.throw(500)
        }
    })

    router.get('/check/forbidden', async (ctx) => {
        try {
            let result = await service.checkForbidden()
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
            ctx.throw(500)
        }
    })

    router.get('/check/same_operation_name', async (ctx) => {
        try {
            let result = await service.sameOperationName()
            switch (result.status) {
                case 'ok':
                    ctx.status = 200
                    return
                case 'forbidden':
                    ctx.status = 403
                    return
            }
        } catch (error) {
            ctx.throw(500)
        }
    })

    return router
}
