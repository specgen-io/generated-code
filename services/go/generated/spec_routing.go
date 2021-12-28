package generated

import (
	"github.com/husobee/vestigo"
	check "test-service/generated/check"
	echo "test-service/generated/echo"
	"test-service/generated/v2"
	echoV2 "test-service/generated/v2/echo"
)

func AddRoutes(router *vestigo.Router, echoServiceV2 echoV2.Service, echoService echo.Service, checkService check.Service) {
	v2.AddEchoRoutes(router, echoServiceV2)
	AddEchoRoutes(router, echoService)
	AddCheckRoutes(router, checkService)
}
