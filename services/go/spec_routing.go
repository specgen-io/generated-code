package .

import (
	"github.com/husobee/vestigo"
	check "test-service/./check"
	echo "test-service/./echo"
	"test-service/./v2"
	echoV2 "test-service/./v2/echo"
)

func AddRoutes(router *vestigo.Router, echoServiceV2 echoV2.Service, echoService echo.Service, checkService check.Service) {
	v2.AddEchoRoutes(router, echoServiceV2)
	AddEchoRoutes(router, echoService)
	AddCheckRoutes(router, checkService)
}
