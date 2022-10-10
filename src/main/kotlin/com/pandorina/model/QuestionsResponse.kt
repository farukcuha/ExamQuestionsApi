package com.pandorina.model

import com.pandorina.uzman_ogretmenlik.UzmanOgretmenlikTable
import org.jetbrains.exposed.sql.ResultRow

@kotlinx.serialization.Serializable
data class QuestionsResponse(
    val size: Int?,
    val questions: List<Question>
){

    @kotlinx.serialization.Serializable
    data class Question(
        val questionNo: Int?,
        val questionText: String?,
        val answer0: String?,
        val answer1: String?,
        val answer2: String?,
        val answer3: String?,
        val answer4: String?,
        val correctAnswer: Int?
    )
}

fun ResultRow.toQuestion(): QuestionsResponse.Question {

    return QuestionsResponse.Question(
        questionNo = this[UzmanOgretmenlikTable.questionNo],
        questionText = this[UzmanOgretmenlikTable.questionText],
        answer0 = this[UzmanOgretmenlikTable.answer0],
        answer1 = this[UzmanOgretmenlikTable.answer1],
        answer2 = this[UzmanOgretmenlikTable.answer2],
        answer3 = this[UzmanOgretmenlikTable.answer3],
        answer4 = this[UzmanOgretmenlikTable.answer4],
        correctAnswer = this[UzmanOgretmenlikTable.correctAnswer],
    )
}
