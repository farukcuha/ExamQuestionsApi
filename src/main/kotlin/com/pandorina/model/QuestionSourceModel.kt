package com.pandorina.model


data class QuestionSourceModel(
    val title: String,
    val test_no: Int,
    val question_no: Int,
    val question_text: String,
    val answer_0: String,
    val answer_1: String,
    val answer_2: String,
    val answer_3: String,
    val answer_4: String,
    val correct_answer: Int
)
