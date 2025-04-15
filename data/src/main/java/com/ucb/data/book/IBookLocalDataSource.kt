package com.ucb.data.book

import com.ucb.domain.Book
import com.ucb.domain.Movie

interface IBookLocalDataSource {
    suspend fun likeBook(book: Book): Boolean
    suspend fun getLikedBooks(): List<Movie>
}