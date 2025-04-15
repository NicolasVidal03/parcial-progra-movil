package com.ucb.framework.bookDB

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromList(value: List<String>): String {
        return value.joinToString(",")
    }

    @TypeConverter
    fun toList(value: String): List<String> {
        return value.split(",").map { it.trim() }
    }
}