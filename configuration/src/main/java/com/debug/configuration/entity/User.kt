package com.debug.configuration.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 * 用户
 * @author Lyb
 * @since 2019-11-27
 */
@Entity
data class User(
        @Column(length = 32, unique = true, nullable = false)
        var username: String = "",
        @Column(length = 64)
        var password: String = ""
) {
    @Id
    var id: Long? = null
    @Column(length = 16)
    var nickname: String? = null
}