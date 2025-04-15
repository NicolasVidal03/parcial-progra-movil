package com.ucb.framework.bookDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book")
data class BookTable (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "titulo")
    val titulo: String,

    @ColumnInfo(name = "autor")
    val autor: List<String>,

    @ColumnInfo(name = "anio")
    val anio: Int
)

