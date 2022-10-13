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
        val answers: List<String>?,
        val correctAnswer: Int?
    )
}

fun QuestionSourceModel.toQuestion(): QuestionsResponse.Question {
    val answers = mutableListOf<String>()
    answers.add(answer_0)
    answers.add(answer_1)
    answers.add(answer_2)
    answers.add(answer_3)
    if (answer_4.isNotBlank()) answers.add(answer_4)

    return QuestionsResponse.Question(
        questionNo = question_no,
        questionText = question_text,
        answers.toList(),
        correctAnswer = correct_answer
    )
}
