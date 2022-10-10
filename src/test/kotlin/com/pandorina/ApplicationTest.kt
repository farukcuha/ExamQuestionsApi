package com.pandorina

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlin.test.*
import io.ktor.server.testing.*
import com.pandorina.plugins.*
import com.pandorina.uzman_ogretmenlik.UzmanOgretmenlikTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            configureRouting()
        }
        Database.connect("jdbc:h2:./exam_questions_database", "org.h2.Driver")
        transaction {
            SchemaUtils.create(UzmanOgretmenlikTable)
        }
        val result = transaction {
            UzmanOgretmenlikTable
                .selectAll()
                .map {
                    it[UzmanOgretmenlikTable.title]
                }.distinct()
        }
        println(result)
    }
}