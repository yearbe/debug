package com.debug.configuration.res

import com.debug.configuration.constant.ResCode

/**
 * 返回值
 * @author Lyb
 * @since 2019-11-27
 */
data class Res<T>(var resCode: ResCode = ResCode.FAIL) {
    constructor(resCode: ResCode, msg: String?) : this() {
        this.resCode = resCode
        if (msg != null) {
            this.msg = msg
        }
    }
    var msg: String = ""
    var data: T? = null
    val code: Int
        get() { return this.resCode.code }
}