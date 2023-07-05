package com.oreily.demo.controllers

import com.oreily.demo.json.Greeting
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloWorldFunctionalTest {

    @Autowired
    lateinit var template: TestRestTemplate

    @Test
    fun greetWithName() {
        val greeting = template.getForObject("/hello", Greeting::class.java);
        assertThat(greeting.message).isEqualTo("Hello, World!")
    }

    @Test
    fun greetWithoutName() {
        val greeting = template.getForEntity("/hello", Greeting::class.java);
        assertEquals(HttpStatus.OK, greeting.statusCode)
        assertEquals(MediaType.APPLICATION_JSON, greeting.headers.contentType)
        val response = greeting.body
        if (response != null) {
            assertEquals("Hello, World", response.message)
        }
    }
}