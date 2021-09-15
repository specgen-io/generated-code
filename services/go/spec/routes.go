package spec

import (
	"github.com/husobee/vestigo"
	"/spec/v2"
)

func AddRoutes(router *vestigo.Router, echoServiceV2 v2.IEchoService, echoService IEchoService, checkService ICheckService) {
	v2.AddEchoRoutes(router, echoServiceV2)
	AddEchoRoutes(router, echoService)
	AddCheckRoutes(router, checkService)
}
