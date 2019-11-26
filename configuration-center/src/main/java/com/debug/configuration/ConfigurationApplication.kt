package com.debug.configuration

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 *
 * @author Lyb
 * @since 2019-11-26
 */
@SpringBootApplication
open class ConfigurationApplication

fun main(args: Array<String>) {
    listOf(1, 2)
    SpringApplication.run(ConfigurationApplication::class.java, *args)
}