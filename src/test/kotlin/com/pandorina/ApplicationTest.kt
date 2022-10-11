package com.pandorina

import kotlin.test.*
import io.ktor.server.testing.*
import com.pandorina.plugins.*

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            configureRouting()
        }
    }
}