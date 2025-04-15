package com.ucb.ucbtest.book

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.data.NetworkResult
import com.ucb.domain.Book
import com.ucb.ucbtest.movie.MovieViewModel.MovieUIState
import com.ucb.usecases.book.Buscar
import com.ucb.usecases.book.GetLikedBooks
import com.ucb.usecases.book.LikeBook
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val buscar: Buscar,
    private val getLikedBooks: GetLikedBooks,
    private val likeBook: LikeBook,
    @ApplicationContext private val context: Context
): ViewModel() {
    sealed class BookState {
        object init: BookState()
        data class Success(val model: List<Book>): BookState()
        data class Error(val message: String): BookState()
    }
    private val _state = MutableStateFlow<BookState>(BookState.init)
    val state : StateFlow<BookState> = _state

    fun buscarLibros(titulo: String) {
        _state.value = BookState.init
        viewModelScope.launch {
            val response = buscar.invoke(titulo)
            when ( val result = response ) {
                is NetworkResult.Error -> {
                    _state.value = BookState.Error(result.error)
                }
                is NetworkResult.Success -> {
                    _state.value = BookState.Success(result.data)
                }
            }
        }
    }

}