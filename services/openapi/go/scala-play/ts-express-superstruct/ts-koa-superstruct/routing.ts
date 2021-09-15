import Router from '@koa/router'
import * as t from './superstruct'
import * as models from './models'
import * as services from './services'

const TEchoQueryQueryParams = t.type({
    int_query: t.StrInteger,
    string_query: t.string(),
})

type EchoQueryQueryParams = t.Infer<typeof TEchoQueryQueryParams>

const TEchoHeaderHeaderParams = t.type({
    'int-header': t.StrInteger,
    'string-header': t.string(),
})

type EchoHeaderHeaderParams = t.Infer<typeof TEchoHeaderHeaderParams>

const TEchoUrlParamsUrlParams = t.type({
    int_url: t.StrInteger,
    string_url: t.string(),
})

type EchoUrlParamsUrlParams = t.Infer<typeof TEchoUrlParamsUrlParams>

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

    router.get('/echo/query', async (ctx) => {
        var queryParams: EchoQueryQueryParams
        try {
            queryParams = t.decode(TEchoQueryQueryParams, ctx.request.query)
        } catch (error) {
            ctx.throw(400, error)
            return
        }
        try {
            let result = await service.echoQuery({...queryParams})
            switch (result.status) {
                case 'ok':
                    ctx.status = 200
        ctx.body = t.encode(models.TMessage, result.data)
            }
        } catch (error) {
            ctx.throw(500)
        }
    })

    router.get('/echo/header', async (ctx) => {
        var headerParams: EchoHeaderHeaderParams
        try {
            headerParams = t.decode(TEchoHeaderHeaderParams, ctx.request.headers)
        } catch (error) {
            ctx.throw(400, error)
            return
        }
        try {
            let result = await service.echoHeader({...headerParams})
            switch (result.status) {
                case 'ok':
                    ctx.status = 200
        ctx.body = t.encode(models.TMessage, result.data)
            }
        } catch (error) {
            ctx.throw(500)
        }
    })

    router.get('/echo/url_params/:int_url/:string_url', async (ctx) => {
        var urlParams: EchoUrlParamsUrlParams
        try {
            urlParams = t.decode(TEchoUrlParamsUrlParams, ctx.params)
        } catch (error) {
            ctx.throw(400, error)
            return
        }
        try {
            let result = await service.echoUrlParams({...urlParams})
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

const TCheckQueryQueryParams = t.type({
    p_string: t.string(),
    p_string_opt: t.optional(t.string()),
    p_string_array: t.array(t.string()),
    p_date: t.string(),
    p_date_array: t.array(t.string()),
    p_datetime: t.StrDateTime,
    p_int: t.StrInteger,
    p_long: t.StrInteger,
    p_decimal: t.StrFloat,
    p_enum: models.TChoice,
    p_string_defaulted: t.defaulted(t.string(), "the default value"),
})

type CheckQueryQueryParams = t.Infer<typeof TCheckQueryQueryParams>

const TCheckUrlParamsUrlParams = t.type({
    int_url: t.StrInteger,
    string_url: t.string(),
    float_url: t.StrFloat,
    bool_url: t.StrBoolean,
    uuid_url: t.string(),
    decimal_url: t.StrFloat,
    date_url: t.string(),
})

type CheckUrlParamsUrlParams = t.Infer<typeof TCheckUrlParamsUrlParams>

export let checkRouter = (service: services.CheckService) => {
    let router = new Router()

    router.get('/check/empty', async (ctx) => {
        try {
            let result = await service.checkEmpty()
            switch (result.status) {
                case 'ok':
                    ctx.status = 200
            }
        } catch (error) {
            ctx.throw(500)
        }
    })

    router.get('/check/query', async (ctx) => {
        var queryParams: CheckQueryQueryParams
        try {
            queryParams = t.decode(TCheckQueryQueryParams, ctx.request.query)
        } catch (error) {
            ctx.throw(400, error)
            return
        }
        try {
            let result = await service.checkQuery({...queryParams})
            switch (result.status) {
                case 'ok':
                    ctx.status = 200
            }
        } catch (error) {
            ctx.throw(500)
        }
    })

    router.get('/check/url_params/:int_url/:string_url/:float_url/:bool_url/:uuid_url/:decimal_url/:date_url', async (ctx) => {
        var urlParams: CheckUrlParamsUrlParams
        try {
            urlParams = t.decode(TCheckUrlParamsUrlParams, ctx.params)
        } catch (error) {
            ctx.throw(400, error)
            return
        }
        try {
            let result = await service.checkUrlParams({...urlParams})
            switch (result.status) {
                case 'ok':
                    ctx.status = 200
            }
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
                case 'forbidden':
                    ctx.status = 403
            }
        } catch (error) {
            ctx.throw(500)
        }
    })

    return router
}
