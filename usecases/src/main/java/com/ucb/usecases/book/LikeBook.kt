package com.ucb.usecases.book

import com.ucb.data.BookRepository
import com.ucb.domain.Book

class LikeBook(private val repo: BookRepository) {
    suspend fun invoke(book: Book): Boolean {
        return this.repo.likeBook(book)
    }
}