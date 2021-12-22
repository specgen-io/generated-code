import {Router} from 'express'
import {EchoService as EchoServiceV2} from './v2/echo_service'
import {echoRouter as echoRouterV2} from './v2/routing'
import {EchoService as EchoService} from './echo_service'
import {echoRouter as echoRouter} from './routing'
import {CheckService as CheckService} from './check_service'
import {checkRouter as checkRouter} from './routing'

export let specRouter = (echoServiceV2: EchoServiceV2, echoService: EchoService, checkService: CheckService) => {
    let router = Router()
    router.use('/v2', echoRouterV2(echoServiceV2))
    router.use('/', echoRouter(echoService))
    router.use('/', checkRouter(checkService))
    return router
}
