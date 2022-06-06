import {Router} from 'express'
import {EchoService as EchoServiceV2} from './v2/echo'
import {echoRouter as echoRouterV2} from './v2/routing'
import {EchoService as EchoService} from './echo'
import {echoRouter as echoRouter} from './routing'
import {CheckService as CheckService} from './check'
import {checkRouter as checkRouter} from './routing'

export const specRouter = (echoServiceV2: EchoServiceV2, echoService: EchoService, checkService: CheckService) => {
    const router = Router()
    router.use('/v2', echoRouterV2(echoServiceV2))
    router.use('/', echoRouter(echoService))
    router.use('/', checkRouter(checkService))
    return router
}
