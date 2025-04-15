package com.ucb.framework.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookDto (
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "author_name")
    val author_name: List<String>? = null,
    @Json(name = "first_publish_year")
    val first_publish_year: Int? = null
)
