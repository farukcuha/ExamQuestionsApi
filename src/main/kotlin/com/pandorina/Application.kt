package com.pandorina

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.pandorina.plugins.*
import com.pandorina.uzman_ogretmenlik.UzmanOgretmenlikTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {
    embeddedServer(Netty, port = 5432, host = "0.0.0.0") {
        configureSerialization()
        configureRouting()
        Database.connect("jdbc:h2:./exam_questions_database", "org.h2.Driver")
        transaction {
            SchemaUtils.create(UzmanOgretmenlikTable)
        }
    }.start(wait = true)
}
