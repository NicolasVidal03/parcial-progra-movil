package com.ucb.framework.bookDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface IBookDAO {
    @Query("select * from book")
    fun getLikedBooks(): List<BookTable>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun likeBook(book: BookTable)
}