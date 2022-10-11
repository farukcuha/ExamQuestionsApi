package com.pandorina.model

@kotlinx.serialization.Serializable
data class TestsResponse(
    val size: Int?,
    val tests: List<Test>?
) {
    @kotlinx.serialization.Serializable
    data class Test(
        val testTitle: String?,
        val testNo: Int?
    )
}

fun QuestionSourceModel.toTestResponse(): TestsResponse.Test {

    return TestsResponse.Test(
        testTitle = title,
        testNo = test_no
    )
}
