package com.pandorina.model

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

fun QuestionSourceModel.toQuestion(): QuestionsResponse.Question {

    return QuestionsResponse.Question(
        questionNo = question_no,
        questionText = question_text,
        answer0 = answer_0,
        answer1 = answer_1,
        answer2 = answer_2,
        answer3 = answer_3,
        answer4 = answer_4,
        correctAnswer = correct_answer
    )
}
