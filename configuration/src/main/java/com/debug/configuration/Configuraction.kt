package com.debug.configuration

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * 启动类
 * @author Lyb
 * @since 2019-11-27
 */
@SpringBootApplication
open class ConfigurationApplication

fun main(args: Array<String>) {
    SpringApplication.run(ConfigurationApplication::class.java, *args)
}