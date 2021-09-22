package .

import (
	check "./check"
	echo "./echo"
	"./v2"
	echoV2 "./v2/echo"
	"github.com/husobee/vestigo"
)

func AddRoutes(router *vestigo.Router, echoServiceV2 echoV2.Service, echoService echo.Service, checkService check.Service) {
	v2.AddEchoRoutes(router, echoServiceV2)
	AddEchoRoutes(router, echoService)
	AddCheckRoutes(router, checkService)
}
