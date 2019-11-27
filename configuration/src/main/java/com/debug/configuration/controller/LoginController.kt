package com.debug.configuration.controller

import com.debug.configuration.req.LoginParam
import com.debug.configuration.res.Res
import com.debug.configuration.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * 登录
 * @author Lyb
 * @since 2019-11-27
 */
@RestController
class LoginController @Autowired constructor(private val userService: UserService) {

    @RequestMapping(value = ["/login"], method = [RequestMethod.POST], produces = ["application/json;charset=utf8"])
    fun login(@Valid @RequestBody loginParam: LoginParam) : Res<*> = userService.checkLogin(loginParam)
}