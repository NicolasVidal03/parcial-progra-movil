package com.ucb.framework.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookDto (
    val titulo: String,
    val autor: List<String>,
    val anio: Int
)
