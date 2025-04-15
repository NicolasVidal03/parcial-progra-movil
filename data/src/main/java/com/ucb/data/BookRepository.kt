package com.ucb.data

import com.ucb.data.book.IBookLocalDataSource
import com.ucb.data.book.IBookRemoteDataSource
import com.ucb.domain.Book

class BookRepository(
    private val remote: IBookRemoteDataSource,
    private val local: IBookLocalDataSource) {

    suspend fun findBook(titulo: String): NetworkResult<List<Book>> {
        return this.remote.buscar(titulo)
    }

    suspend fun likeBook(book: Book): Boolean {
        return this.local.likeBook(book)
    }

    suspend fun getLikedBooks(): List<Book> {
        return this.local.getLikedBooks()
    }
}