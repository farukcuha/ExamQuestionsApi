package com.pandorina

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.pandorina.plugins.*

fun main() {
    embeddedServer(Netty, port = System.getenv("PORT").toInt()) {
        configureSerialization()
        configureRouting()
    }.start(wait = true)
}
