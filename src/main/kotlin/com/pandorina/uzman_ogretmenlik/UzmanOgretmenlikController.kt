package com.pandorina.uzman_ogretmenlik

import com.google.gson.Gson
import com.pandorina.model.*
import java.io.BufferedReader
import java.io.InputStreamReader

object UzmanOgretmenlikController {

    private val questionList = BufferedReader(
        InputStreamReader(javaClass.getResourceAsStream("/uzman-ogretmenlik-sorular.json"))
    ).readText().run {
        Gson().fromJson(this, Array<QuestionSourceModel>::class.java).asList().distinctBy {
            it.question_text
        }
    }

    private val trialExamList = BufferedReader(
        InputStreamReader(javaClass.getResourceAsStream("/uzman-ogretmenlik-deneme-sinavlari.json"))
    ).readText().run {
        Gson().fromJson(this, Array<QuestionSourceModel>::class.java).asList().distinctBy {
            it.question_text
        }
    }

    fun mixedList(): List<QuestionSourceModel> {
        return mutableListOf<QuestionSourceModel>().apply {
            addAll(questionList)
            addAll(trialExamList)
            shuffled()
            filter {
                it.answer_0.isNotEmpty()
            }
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
        val prList = mutableListOf<TestsResponse.Test>()
        questionList.filter {
            it.title == title && it.answer_0.isNotEmpty()
        }.apply {
            distinctBy {
                it.test_no
            }.forEach { filtered ->
                val asd = filter {
                    filtered.test_no == it.test_no
                }
                if (asd.size >= 5) prList.addAll(
                    asd.map {
                        it.toTestResponse()
                    }.distinct()
                )
            }
        }
        return TestsResponse(size = prList.size, prList)
    }

    fun getQuestions(title: String, testNo: Int): QuestionsResponse {
        val questions = questionList.filter {
            it.title == title && it.test_no == testNo && it.answer_0.isNotEmpty()
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
            questionCount++
            it.copy(questionNo = questionCount)
        }
        return QuestionsResponse(size = randomQuestions.size, randomQuestions)
    }
}