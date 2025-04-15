package com.ucb.ucbtest.book

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.ucb.ucbtest.R

@Composable
fun BookUI(viewModel: BookViewModel = hiltViewModel()) {
    var titulo by remember { mutableStateOf("") }
    val state by viewModel.state.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = titulo,
                onValueChange = {
                    titulo = it
                },
                label = {
                    Text("Ingrese el título del libro")
                }
            )

            OutlinedButton(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                onClick = {
                    viewModel.buscarLibros(titulo)
                }
            ) {
                Text(stringResource(id = R.string.gitalias_btn_find))
            }

            // Muestra los resultados
            when (state) {
                is BookViewModel.BookState.Success -> {
                    val libros = (state as BookViewModel.BookState.Success).model
                    libros.forEach { libro ->
                        Text(
                            text = "Título: ${libro.titulo}, Autor: ${libro.autor}, Año: ${libro.anio}",
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }

                is BookViewModel.BookState.Error -> {
                    val errorMessage = (state as BookViewModel.BookState.Error).message
                    Text(
                        text = "Error: $errorMessage",
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                BookViewModel.BookState.init -> {
                    Text("Esperando a la busqueda")
                }
            }
        }
    }
}
