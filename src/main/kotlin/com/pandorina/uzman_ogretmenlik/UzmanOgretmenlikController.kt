package com.pandorina.uzman_ogretmenlik

import com.pandorina.model.*
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object UzmanOgretmenlikController {

    fun getTitles(): TitlesResponse {
        return transaction {
            val results = UzmanOgretmenlikTable
                .selectAll()
                .map {
                    it[UzmanOgretmenlikTable.title]
                }.distinct()
            TitlesResponse(results.size, results)
        }
    }

    fun getTests(title: String): TestsResponse {
        return transaction {
            val query = UzmanOgretmenlikTable
                .select {
                    UzmanOgretmenlikTable.title eq title
                }
            val results = query.map {
                it.toTestResponse()
            }.distinct()
            TestsResponse(size = results.size, results)
        }
    }

    fun getQuestions(title: String, testNo: Int): QuestionsResponse {
        return transaction {
            val query = UzmanOgretmenlikTable
                .select {
                    (UzmanOgretmenlikTable.testNo eq testNo) and (UzmanOgretmenlikTable.title eq title)
                }
            val questions = query.map {
                it.toQuestion()
            }
            QuestionsResponse(size = questions.size, questions)
        }
    }

    fun getRandomQuestions(count: Int = 10): QuestionsResponse {
        var questionCount = 0
        return transaction {
            val query = UzmanOgretmenlikTable.selectAll().map {
                it.toQuestion()
            }
            val results = query.shuffled().take(count).map {
                questionCount ++
                it.copy(questionNo = questionCount)
            }
            QuestionsResponse(size = results.size, results)
        }
    }
}