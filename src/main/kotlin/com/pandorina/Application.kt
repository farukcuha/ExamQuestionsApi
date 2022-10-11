package com.pandorina

import com.google.gson.Gson
import com.pandorina.model.QuestionSourceModel
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.pandorina.plugins.*
import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    embeddedServer(Netty, port = 5000, host = "0.0.0.0") {
        configureSerialization()
        configureRouting()
    }.start(wait = true)
}
