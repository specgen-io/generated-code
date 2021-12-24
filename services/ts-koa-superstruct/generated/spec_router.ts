import Router from '@koa/router'
import {EchoService as EchoServiceV2} from './v2/echo_service'
import {echoRouter as echoRouterV2} from './v2/routing'
import {EchoService as EchoService} from './echo_service'
import {echoRouter as echoRouter} from './routing'
import {CheckService as CheckService} from './check_service'
import {checkRouter as checkRouter} from './routing'

export let specRouter = (echoServiceV2: EchoServiceV2, echoService: EchoService, checkService: CheckService) => {
    let router = new Router()
    const theEchoRouterV2 = echoRouterV2(echoServiceV2)
    router.use('/v2', theEchoRouterV2.routes(), theEchoRouterV2.allowedMethods())
    const theEchoRouter = echoRouter(echoService)
    router.use(theEchoRouter.routes(), theEchoRouter.allowedMethods())
    const theCheckRouter = checkRouter(checkService)
    router.use(theCheckRouter.routes(), theCheckRouter.allowedMethods())
    return router
}