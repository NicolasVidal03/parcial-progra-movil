package com.ucb.ucbtest.book

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ucb.data.NetworkResult
import com.ucb.domain.Book
import com.ucb.ucbtest.movie.MovieViewModel.MovieUIState
import com.ucb.usecases.book.Buscar
import com.ucb.usecases.book.GetLikedBooks
import com.ucb.usecases.book.LikeBook
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
        data class SuccessList(val model: List<Book>): BookState()
        data class SuccessLike(val message: String): BookState()
        data class Error(val message: String): BookState()
    }
    private val _state = MutableStateFlow<BookState>(BookState.init)
    val state : StateFlow<BookState> = _state

    private val _libros = MutableStateFlow<List<Book>>(emptyList())
    val libros: StateFlow<List<Book>> = _libros

    private val _likeMessage = MutableStateFlow<String?>(null)
    val likeMessage: StateFlow<String?> = _likeMessage

    private val _librosLike = MutableStateFlow<List<Book>>(emptyList())
    val librosLike: StateFlow<List<Book>> = _librosLike

    fun buscarLibros(titulo: String) {
        _state.value = BookState.init
        viewModelScope.launch {
            val response = buscar.invoke(titulo)
            when ( val result = response ) {
                is NetworkResult.Error -> {
                    _state.value = BookState.Error(result.error)
                }
                is NetworkResult.Success -> {
//                    _state.value = BookState.SuccessList(result.data)
                    _libros.value = result.data
                }
            }
        }
    }

    fun clearMessage() {
        _state.value = BookState.init
    }

    fun likeLibro(book: Book) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                likeBook.invoke(book)
                _likeMessage.value = "Se añadió el libro ${book.titulo} a su colección"
            }
            } catch (e: Exception) {
                _likeMessage.value = "Error al dar like: ${e.message}"
            }
        }
    }

    fun getLibros() {
        viewModelScope.launch() {
            try {
                withContext(Dispatchers.IO) {
                    val libros = getLikedBooks.invoke()
                    _libros.value = libros
                    _state.value = BookState.init
                }
            } catch (e: Exception) {
                _state.value = BookState.Error("Error al mostrar los libros con like ${e.message}")
            }
        }
    }


}