package com.pandorina

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.pandorina.plugins.*

fun main() {
    embeddedServer(Netty, port = 5000, host = "0.0.0.0") {
        configureSerialization()
        configureRouting()
    }.start(wait = true)
}
