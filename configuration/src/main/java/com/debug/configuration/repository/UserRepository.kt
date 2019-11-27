package com.debug.configuration.repository

import com.debug.configuration.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

/**
 * 用户数据
 * @author Lyb
 * @since 2019-11-27
 */
@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByUsernameAndPassword(username: String, password: String): User?
}