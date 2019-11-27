package com.debug.configuration.req

import javax.validation.constraints.NotBlank

/**
 * 登录请求
 * @author Lyb
 * @since 2019-11-27
 */
class LoginParam(
        @field: NotBlank(message = "用户名不能为空")
        val username: String = "",
        @field: NotBlank(message = "密码不能为空")
        val password: String = ""
)