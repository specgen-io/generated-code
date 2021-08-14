import {Router} from 'express'
import {Request, Response} from 'express'
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
            switch (result.status) {
                case 'ok':
                    response.status(200).type('json').send(JSON.stringify(t.encode(models.TMessage, result.data)))
            }
        } catch (error) {
            response.status(500).send()
        }
    })

    router.get('/echo/header', async (request: Request, response: Response) => {
        var headerParams: EchoHeaderHeaderParams
        try {
            headerParams = t.decode(TEchoHeaderHeaderParams, request.headers)
        } catch (error) {
            response.status(400).send()
            return
        }
        try {
            let result = await service.echoHeader({...headerParams})
            switch (result.status) {
                case 'ok':
                    response.status(200).type('json').send(JSON.stringify(t.encode(models.TMessage, result.data)))
            }
        } catch (error) {
            response.status(500).send()
        }
    })

    router.get('/echo/url_params/:int_url/:string_url', async (request: Request, response: Response) => {
        var urlParams: EchoUrlParamsUrlParams
        try {
            urlParams = t.decode(TEchoUrlParamsUrlParams, request.params)
        } catch (error) {
            response.status(400).send()
            return
        }
        try {
            let result = await service.echoUrlParams({...urlParams})
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
    let router = Router()

    router.get('/check/empty', async (request: Request, response: Response) => {
        try {
            let result = await service.checkEmpty()
            switch (result.status) {
                case 'ok':
                    response.status(200).send()
            }
        } catch (error) {
            response.status(500).send()
        }
    })

    router.get('/check/query', async (request: Request, response: Response) => {
        var queryParams: CheckQueryQueryParams
        try {
            queryParams = t.decode(TCheckQueryQueryParams, request.query)
        } catch (error) {
            response.status(400).send()
            return
        }
        try {
            let result = await service.checkQuery({...queryParams})
            switch (result.status) {
                case 'ok':
                    response.status(200).send()
            }
        } catch (error) {
            response.status(500).send()
        }
    })

    router.get('/check/url_params/:int_url/:string_url/:float_url/:bool_url/:uuid_url/:decimal_url/:date_url', async (request: Request, response: Response) => {
        var urlParams: CheckUrlParamsUrlParams
        try {
            urlParams = t.decode(TCheckUrlParamsUrlParams, request.params)
        } catch (error) {
            response.status(400).send()
            return
        }
        try {
            let result = await service.checkUrlParams({...urlParams})
            switch (result.status) {
                case 'ok':
                    response.status(200).send()
            }
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
                case 'forbidden':
                    response.status(403).send()
            }
        } catch (error) {
            response.status(500).send()
        }
    })

    return router
}
