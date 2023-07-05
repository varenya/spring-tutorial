package com.oreily.demo.controllers

import com.oreily.demo.json.Greeting
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorld {
    @GetMapping("/hello")
    fun sayHello(@RequestParam(required = false, defaultValue = "World") name : String) : Greeting {
        return Greeting("Hello, $name")
    }
}