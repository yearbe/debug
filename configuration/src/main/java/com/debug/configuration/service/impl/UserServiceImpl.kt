package com.debug.configuration.service.impl

import com.debug.configuration.constant.ResCode
import com.debug.configuration.entity.User
import com.debug.configuration.repository.UserRepository
import com.debug.configuration.req.LoginParam
import com.debug.configuration.res.Res
import com.debug.configuration.service.UserService
import com.debug.configuration.util.toMD5
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 用户服务
 * @author Lyb
 * @since 2019-11-27
 */
@Service
class UserServiceImpl @Autowired constructor(private val userRepository: UserRepository) : UserService {

    private val logger: Logger = LoggerFactory.getLogger(javaClass);

    override fun checkLogin(loginParam: LoginParam): Res<*> {
        val res: Res<*> = Res<Any>()
        logger.debug("当前登录用户：{}，密码：{}", loginParam.username, loginParam.password.toMD5())
        val user: User? = userRepository.findByUsernameAndPassword(loginParam.username, loginParam.password.toMD5())
        if (user == null) {
            res.msg = "用户名或密码错误"
        } else {
            res.resCode = ResCode.SUCCESS
            res.msg = "登录成功"
        }
        return res
    }
}