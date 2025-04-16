package com.ucb.ucbtest.book

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucb.ucbtest.R
import kotlinx.coroutines.delay

@Composable
fun BookUI(viewModel: BookViewModel = hiltViewModel()) {
    val scrollState = rememberScrollState()
    var titulo by remember { mutableStateOf("") }

    val libros by viewModel.libros.collectAsState()
    val likeMessage by viewModel.likeMessage.collectAsState()
    val errorState by viewModel.state.collectAsState() // opcional: para errores

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Campo de texto
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text("Ingrese el título del libro") }
            )

            // Botón buscar
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                onClick = { viewModel.buscarLibros(titulo) }
            ) {
                Text(stringResource(id = R.string.gitalias_btn_find))
            }

            // Mensaje de like (si existe)
            likeMessage?.let { message ->
                LaunchedEffect(message) {
                    delay(2000)
                    viewModel.clearMessage()
                }
                Text(
                    text = message,
                    color = Color.Green,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
            }

            // Lista de libros
            if (libros.isNotEmpty()) {
                libros.forEach { libro ->
                    var isLiked by remember { mutableStateOf(false) }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .background(Color.LightGray)
                        ) {
                            Text(
                                text = libro.titulo,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 4.dp),
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )

                            Text(
                                text = libro.autor.joinToString(", "),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 4.dp),
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Thin
                                )
                            )

                            Text(
                                text = "Año: ${libro.anio}",
                                modifier = Modifier.fillMaxWidth(),
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Thin
                                )
                            )

                            IconButton(
                                onClick = {
                                    isLiked = !isLiked
                                    viewModel.likeLibro(libro)
                                },
                                modifier = Modifier
                                    .align(Alignment.End)
                                    .padding(top = 8.dp)
                            ) {
                                Icon(
                                    imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                                    contentDescription = "Me gusta",
                                    tint = Color.Red
                                )
                            }
                        }
                    }
                }
            } else {
                // Si no hay libros, pero no hay error
                if (errorState !is BookViewModel.BookState.Error) {
                    Text("Esperando resultados de búsqueda o no se encontraron libros")
                }
            }

            // Mostrar errores si hay
            if (errorState is BookViewModel.BookState.Error) {
                val errorMessage = (errorState as BookViewModel.BookState.Error).message
                Text(
                    text = "Error: $errorMessage",
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}
