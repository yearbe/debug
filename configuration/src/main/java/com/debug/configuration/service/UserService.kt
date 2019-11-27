package com.debug.configuration.service

import com.debug.configuration.req.LoginParam
import com.debug.configuration.res.Res

/**
 * 用户服务
 * @author Lyb
 * @since 2019-11-27
 */
interface UserService {
    fun checkLogin(loginParam: LoginParam): Res<*>
}