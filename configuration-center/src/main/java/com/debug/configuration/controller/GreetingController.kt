package com.debug.configuration.controller

import com.debug.configuration.entity.Greeting
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

/**
 *
 * @author Lyb
 * @since 2019-11-26
 */
@RestController
class GreetingController {
    private val counter = AtomicLong()

    @RequestMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "world") name: String) = Greeting(counter.incrementAndGet(), "Hello, $name")
}