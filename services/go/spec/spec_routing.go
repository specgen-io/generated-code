package spec

import (
	"github.com/husobee/vestigo"
	check "spec/check"
	echo "spec/echo"
	"spec/v2"
	echoV2 "spec/v2/echo"
)

func AddRoutes(router *vestigo.Router, echoServiceV2 echoV2.Service, echoService echo.Service, checkService check.Service) {
	v2.AddEchoRoutes(router, echoServiceV2)
	AddEchoRoutes(router, echoService)
	AddCheckRoutes(router, checkService)
}
