import Router from '@koa/router'
import * as services_v2 from './services_v2'
import * as routing_v2 from './routing_v2'
import * as services from './services'
import * as routing from './routing'

export let specRouter = (echoServiceV2: services_v2.EchoService, echoService: services.EchoService, checkService: services.CheckService) => {
    let router = new Router()
    const echoRouterV2 = routing_v2.echoRouter(echoServiceV2)
    router.use('/v2', echoRouterV2.routes(), echoRouterV2.allowedMethods())
    const echoRouter = routing.echoRouter(echoService)
    router.use(echoRouter.routes(), echoRouter.allowedMethods())
    const checkRouter = routing.checkRouter(checkService)
    router.use(checkRouter.routes(), checkRouter.allowedMethods())
    return router
}
