package com.pandorina.uzman_ogretmenlik

import com.google.gson.Gson
import com.pandorina.model.*
import com.pandorina.model.QuestionSourceModel
import java.io.File

object UzmanOgretmenlikController {

    private val questionList =
        File("files/uzman-ogretmenlik/uzman-ogretmenlik-sorular.json").readText().run {
            Gson().fromJson(this, Array<QuestionSourceModel>::class.java).asList()
        }
    private val trialExamList = File("files/uzman-ogretmenlik/uzman-ogretmenlik-deneme-sinavlari.json").readText().run {
        Gson().fromJson(this, Array<QuestionSourceModel>::class.java).asList()
    }

    private fun mixedList(): List<QuestionSourceModel> {
        return mutableListOf<QuestionSourceModel>().apply {
            addAll(questionList)
            addAll(trialExamList)
            shuffled()
        }
    }

    fun getTitles(): TitlesResponse {
        val titles = questionList
            .map {
                it.title
            }.distinct()
        return TitlesResponse(titles.size, titles)
    }

    fun getTests(title: String): TestsResponse {
        val questions = questionList.filter {
            it.title == title
        }.map {
            it.toTestResponse()
        }.distinct()
        return TestsResponse(size = questions.size, questions)
    }

    fun getQuestions(title: String, testNo: Int): QuestionsResponse {
        val questions = questionList.filter {
            it.title == title && it.test_no == testNo
        }.map {
            it.toQuestion()
        }
        return QuestionsResponse(size = questions.size, questions)
    }

    fun getTrialExamTests(): TestsResponse {
        val tests = trialExamList.map {
            it.toTestResponse()
        }.distinct()
        return TestsResponse(size = tests.size, tests)
    }

    fun getTrialExamQuestions(testNo: Int): QuestionsResponse {
        val questions = trialExamList.filter {
            it.test_no == testNo
        }.map {
            it.toQuestion()
        }
        return QuestionsResponse(size = questions.size, questions)
    }

    fun getRandomQuestions(count: Int = 10): QuestionsResponse {
        var questionCount = 0
        val randomQuestions = mixedList().map {
            it.toQuestion()
        }.shuffled().take(count).map {
            questionCount ++
            it.copy(questionNo = questionCount)
        }
        return QuestionsResponse(size = randomQuestions.size, randomQuestions)
    }
}