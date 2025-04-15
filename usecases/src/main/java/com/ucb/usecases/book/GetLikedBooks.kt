package com.ucb.usecases.book

import com.ucb.data.BookRepository
import com.ucb.domain.Book

class GetLikedBooks(private val repo: BookRepository) {
    suspend fun invoke(): List<Book> {
        return this.repo.getLikedBooks()
    }
}