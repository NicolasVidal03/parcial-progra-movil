package com.ucb.data.book

import com.ucb.domain.Book

interface IBookLocalDataSource {
    suspend fun likeBook(book: Book): Boolean
}