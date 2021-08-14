import {Router} from 'express'
import * as services_v2 from './services_v2'
import * as routing_v2 from './routing_v2'
import * as services from './services'
import * as routing from './routing'

export let specRouter = (echoServiceV2: services_v2.EchoService, echoService: services.EchoService, checkService: services.CheckService) => {
    let router = Router()
    router.use('/v2', routing_v2.echoRouter(echoServiceV2))
    router.use('/', routing.echoRouter(echoService))
    router.use('/', routing.checkRouter(checkService))
    return router
}
