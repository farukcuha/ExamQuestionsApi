package com.pandorina

import com.google.gson.Gson
import com.pandorina.model.QuestionSourceModel
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.pandorina.plugins.*
import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    embeddedServer(Netty, port = System.getenv("PORT").toInt()) {
        configureSerialization()
        configureRouting()
    }.start(wait = true)
}
