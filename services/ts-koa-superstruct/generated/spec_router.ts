import Router from '@koa/router'
import {EchoService as EchoServiceV2} from './v2/echo'
import {echoRouter as echoRouterV2} from './v2/routing'
import {EchoService as EchoService} from './echo'
import {echoRouter as echoRouter} from './routing'
import {CheckService as CheckService} from './check'
import {checkRouter as checkRouter} from './routing'

export interface Services {
    echoServiceV2: EchoServiceV2
    echoService: EchoService
    checkService: CheckService
}

export const specRouter = (services: Services) => {
    const router = new Router()
    const theEchoRouterV2 = echoRouterV2(services.echoServiceV2)
    router.use('/v2', theEchoRouterV2.routes(), theEchoRouterV2.allowedMethods())
    const theEchoRouter = echoRouter(services.echoService)
    router.use(theEchoRouter.routes(), theEchoRouter.allowedMethods())
    const theCheckRouter = checkRouter(services.checkService)
    router.use(theCheckRouter.routes(), theCheckRouter.allowedMethods())
    return router
}
