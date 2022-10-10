package com.pandorina.model

import com.pandorina.uzman_ogretmenlik.UzmanOgretmenlikTable
import org.jetbrains.exposed.sql.ResultRow

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

fun ResultRow.toTestResponse(): TestsResponse.Test {

    return TestsResponse.Test(
        testTitle = this[UzmanOgretmenlikTable.title],
        testNo = this[UzmanOgretmenlikTable.testNo]
    )
}
