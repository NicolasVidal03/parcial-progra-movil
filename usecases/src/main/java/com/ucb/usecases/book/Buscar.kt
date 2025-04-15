package com.ucb.usecases.book

import com.ucb.data.BookRepository
import com.ucb.data.NetworkResult
import com.ucb.domain.Book

class Buscar(private val repo: BookRepository) {
    suspend fun invoke(titulo: String): NetworkResult<List<Book>> {
        return this.repo.findBook(titulo)
    }
}