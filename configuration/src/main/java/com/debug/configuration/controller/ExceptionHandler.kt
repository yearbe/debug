package com.debug.configuration.controller

import com.debug.configuration.constant.ResCode
import com.debug.configuration.res.Res
import org.springframework.dao.DataAccessException
import org.springframework.http.converter.HttpMessageConversionException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import java.sql.SQLException
import javax.servlet.http.HttpServletRequest

/**
 * 异常处理
 * @author Lyb
 * @since 2019-11-27
 */
@ControllerAdvice
class ExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = [Exception::class])
    fun exceptionHandler(request: HttpServletRequest, exception: Exception): Res<Any> {
        return if (exception is MethodArgumentNotValidException) {
            Res(ResCode.ERROR, exception.bindingResult.allErrors[0].defaultMessage)
        } else if (exception is SQLException || exception is DataAccessException) {
            Res(ResCode.ERROR, "数据库异常")
        } else if (exception is NullPointerException) {
            Res(ResCode.ERROR, "数据异常")
        } else if (exception is HttpMessageConversionException) {
            Res(ResCode.ERROR, "请求参数有误")
        } else if (exception is HttpRequestMethodNotSupportedException) {
            Res(ResCode.ERROR, "请求方法" + request.method + "不支持")
        } else {
            Res(ResCode.ERROR, "未知错误")
        }
    }
}