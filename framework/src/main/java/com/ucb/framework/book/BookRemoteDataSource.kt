package com.ucb.framework.book

import com.ucb.data.NetworkResult
import com.ucb.data.book.IBookRemoteDataSource
import com.ucb.domain.Book
import com.ucb.framework.mappers.toModel
import com.ucb.framework.service.RetrofitBuilder

class BookRemoteDataSource(val retrofitService: RetrofitBuilder): IBookRemoteDataSource {
    override suspend fun buscar(titulo: String): NetworkResult<List<Book>> {
        val response = retrofitService.bookService.buscar(titulo)
        if (response.isSuccessful) {
            return NetworkResult.Success(response.body()!!.docs.map { it.toModel() })
        }
        else {
            return NetworkResult.Error(response.message())
        }
    }
}