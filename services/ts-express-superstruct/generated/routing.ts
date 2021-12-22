import {Router} from 'express'
import {Request, Response} from 'express'
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
    let router = Router()

    router.post('/echo/body_string', async (request: Request, response: Response) => {
        const body: string = request.body
        try {
        } catch (error) {
            response.status(400).send()
            return
        }
        try {
            let result = await service.echoBodyString({body})
            response.status(200).type('text').send(result)
            return
        } catch (error) {
            response.status(500).send()
        }
    })

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
            response.status(200).type('json').send(JSON.stringify(t.encode(models.TMessage, result)))
            return
        } catch (error) {
            response.status(500).send()
        }
    })

    router.get('/echo/query', async (request: Request, response: Response) => {
        var queryParams: EchoQueryQueryParams
        try {
            queryParams = t.decode(TEchoQueryQueryParams, request.query)
        } catch (error) {
            response.status(400).send()
            return
        }
        try {
            let result = await service.echoQuery({...queryParams})
            response.status(200).type('json').send(JSON.stringify(t.encode(models.TParameters, result)))
            return
        } catch (error) {
            response.status(500).send()
        }
    })

    router.get('/echo/header', async (request: Request, response: Response) => {
        var headerParams: EchoHeaderHeaderParams
        try {
            headerParams = t.decode(TEchoHeaderHeaderParams, zipHeaders(request.rawHeaders))
        } catch (error) {
            response.status(400).send()
            return
        }
        try {
            let result = await service.echoHeader({...headerParams})
            response.status(200).type('json').send(JSON.stringify(t.encode(models.TParameters, result)))
            return
        } catch (error) {
            response.status(500).send()
        }
    })

    router.get('/echo/url_params/:int_url/:long_url/:float_url/:double_url/:decimal_url/:bool_url/:string_url/:uuid_url/:date_url/:datetime_url/:enum_url', async (request: Request, response: Response) => {
        var urlParams: EchoUrlParamsUrlParams
        try {
            urlParams = t.decode(TEchoUrlParamsUrlParams, request.params)
        } catch (error) {
            response.status(400).send()
            return
        }
        try {
            let result = await service.echoUrlParams({...urlParams})
            response.status(200).type('json').send(JSON.stringify(t.encode(models.TUrlParameters, result)))
            return
        } catch (error) {
            response.status(500).send()
        }
    })

    router.post('/echo/everything/:date_url/:decimal_url', async (request: Request, response: Response) => {
        var body: models.Message
        var urlParams: EchoEverythingUrlParams
        var headerParams: EchoEverythingHeaderParams
        var queryParams: EchoEverythingQueryParams
        try {
            body = t.decode(models.TMessage, request.body)
            urlParams = t.decode(TEchoEverythingUrlParams, request.params)
            headerParams = t.decode(TEchoEverythingHeaderParams, zipHeaders(request.rawHeaders))
            queryParams = t.decode(TEchoEverythingQueryParams, request.query)
        } catch (error) {
            response.status(400).send()
            return
        }
        try {
            let result = await service.echoEverything({body, ...urlParams, ...headerParams, ...queryParams})
            switch (result.status) {
                case 'ok':
                    response.status(200).type('json').send(JSON.stringify(t.encode(models.TEverything, result.data)))
                    return
                case 'forbidden':
                    response.status(403).send()
                    return
            }
        } catch (error) {
            response.status(500).send()
        }
    })

    router.get('/echo/same_operation_name', async (request: Request, response: Response) => {
        try {
            let result = await service.sameOperationName()
            switch (result.status) {
                case 'ok':
                    response.status(200).send()
                    return
                case 'forbidden':
                    response.status(403).send()
                    return
            }
        } catch (error) {
            response.status(500).send()
        }
    })

    return router
}

export let checkRouter = (service: CheckService) => {
    let router = Router()

    router.get('/check/empty', async (request: Request, response: Response) => {
        try {
            await service.checkEmpty()
            response.status(200).send()
            return
        } catch (error) {
            response.status(500).send()
        }
    })

    router.get('/check/forbidden', async (request: Request, response: Response) => {
        try {
            let result = await service.checkForbidden()
            switch (result.status) {
                case 'ok':
                    response.status(200).type('json').send(JSON.stringify(t.encode(models.TMessage, result.data)))
                    return
                case 'forbidden':
                    response.status(403).send()
                    return
            }
        } catch (error) {
            response.status(500).send()
        }
    })

    router.get('/check/same_operation_name', async (request: Request, response: Response) => {
        try {
            let result = await service.sameOperationName()
            switch (result.status) {
                case 'ok':
                    response.status(200).send()
                    return
                case 'forbidden':
                    response.status(403).send()
                    return
            }
        } catch (error) {
            response.status(500).send()
        }
    })

    return router
}
