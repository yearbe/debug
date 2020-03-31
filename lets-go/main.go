package main

import (
	"github.com/gogf/gf/frame/g"
	"github.com/gogf/gf/net/ghttp"
	"net/http"
)

func main() {
	s := g.Server("Bingo Http Server")
	s.SetConfigWithMap(g.Map{
		"AccessLogEnabled": false,
		"ErrorLogEnabled":  false,
	})
	s.SetPort(8080)
	s.BindMiddlewareDefault(MiddlewareLog)
	s.Group("/api", func(group *ghttp.RouterGroup) {
		group.Middleware(MiddlewareAuth, MiddlewareCORS, MiddlewareErrorHandler)
		group.ALL("/user/list", func(r *ghttp.Request) {
			panic("啊！我出错了！")
		})
	})
	s.Run()
}

func MiddlewareAuth(r *ghttp.Request) {
	token := r.Get("token")
	if token == "123456" {
		r.Middleware.Next()
	} else {
		r.Response.WriteStatus(http.StatusForbidden)
	}
}

func MiddlewareCORS(r *ghttp.Request) {
	r.Response.CORSDefault()
	r.Middleware.Next()
}

func MiddlewareErrorHandler(r *ghttp.Request) {
	r.Middleware.Next()
	if r.Response.Status >= http.StatusInternalServerError {
		r.Response.ClearBuffer()
		r.Response.Writeln("哎哟我去，服务器居然开小差了，请稍后再试吧！")
	}
}

func MiddlewareLog(r *ghttp.Request) {
	r.Middleware.Next()
	g.Log().Println(r.Response.Status, r.URL.Path, r.GetError().Error())
}
