package com.pandorina.table

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

open class QuestionTable : Table() {
    val id: Column<Int> = integer("id").autoIncrement()
    val title: Column<String> = varchar("title", 128)
    val testNo: Column<Int> = integer("test_no")
    val questionNo: Column<Int> = integer("question_no")
    val questionText: Column<String> = varchar("question_text", 1024)
    val answer0: Column<String> = varchar("answer_0", 256)
    val answer1: Column<String> = varchar("answer_1", 256)
    val answer2: Column<String> = varchar("answer_2", 256)
    val answer3: Column<String> = varchar("answer_3", 256)
    val answer4: Column<String> = varchar("answer_4", 256)
    val correctAnswer: Column<Int> = integer("correct_answer")
    override val primaryKey = PrimaryKey(id, name = "id")
}