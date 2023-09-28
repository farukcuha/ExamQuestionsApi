package com.pandorina.uzman_ogretmenlik

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.uzmanOgretmenlikRouting() {
    route("/study_exams") {
        get("/titles") {
            call.respond(UzmanOgretmenlikController.getTitles())
        }
        get("/tests") {
            val title = call.request.queryParameters["title"] ?: return@get call.respondText(
                "Missing title",
                status = HttpStatusCode.BadRequest
            )
            call.respond(UzmanOgretmenlikController.getTests(title))
        }
        get("/questions") {
            val title = call.request.queryParameters["title"] ?: return@get call.respondText(
                "Missing title",
                status = HttpStatusCode.BadRequest
            )
            val testNo = call.request.queryParameters["test_no"] ?: return@get call.respondText(
                "Missing testNo",
                status = HttpStatusCode.BadRequest
            )
            call.respond(UzmanOgretmenlikController.getQuestions(title, testNo.toInt()))
        }
    }

    route("/trial_exams"){
        get("/tests") {
            call.respond(UzmanOgretmenlikController.getTrialExamTests())
        }
        get("/questions") {
            val testNo = call.request.queryParameters["test_no"] ?: return@get call.respondText(
                "Missing testNo",
                status = HttpStatusCode.BadRequest
            )
            call.respond(UzmanOgretmenlikController.getTrialExamQuestions(testNo.toInt()))
        }
    }

    get("random_questions") {
        val count = call.request.queryParameters["count"]
        call.respond(UzmanOgretmenlikController.getRandomQuestions(count?.toInt() ?: 10))
    }

    get("exam_time") {
        call.respond(1699858800000)
    }
}