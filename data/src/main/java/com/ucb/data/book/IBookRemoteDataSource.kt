package com.ucb.data.book

import com.ucb.data.NetworkResult
import com.ucb.domain.Book

interface IBookRemoteDataSource {
    suspend fun buscar(titulo: String) : NetworkResult<List<Book>>
}
