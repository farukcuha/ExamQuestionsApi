package com.pandorina.plugins

import com.pandorina.uzman_ogretmenlik.uzmanOgretmenlikRouting
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {
    routing {
        get {
            call.respondText("Welcome to exam questions server!")
        }
        route("/uzman_ogretmenlik"){
            uzmanOgretmenlikRouting()
        }
    }
}
