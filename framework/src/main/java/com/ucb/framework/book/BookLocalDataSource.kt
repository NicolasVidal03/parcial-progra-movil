package com.ucb.framework.book

import android.content.Context
import com.ucb.data.book.IBookLocalDataSource
import com.ucb.domain.Book
import com.ucb.framework.bookDB.BookRoomDatabase
import com.ucb.framework.mappers.toEntity
import com.ucb.framework.mappers.toModel

class BookLocalDataSource(val context: Context) : IBookLocalDataSource {
    val bookDao = BookRoomDatabase.getDatabase(context).bookDao()
    override suspend fun getLikedBooks(): List<Book> {
        return this.bookDao.getLikedBooks().map { it.toModel() }
    }

    override suspend fun likeBook(book: Book): Boolean {
        this.bookDao.likeBook(book.toEntity())
        return true
    }
}