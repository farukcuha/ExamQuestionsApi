package com.pandorina.model

@kotlinx.serialization.Serializable
data class TitlesResponse(
    val size: Int?,
    val titles: List<String>?
)
