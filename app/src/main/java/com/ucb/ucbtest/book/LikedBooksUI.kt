package com.ucb.ucbtest.book

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ucb.ucbtest.navigation.Screen

@Composable
fun LikedBooksUI(
    onBackPressed: () -> Unit,
    viewModel: BookViewModel = hiltViewModel()
) {

    val libros = viewModel.libros.collectAsState().value
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        if (libros.isEmpty()) {
            viewModel.getLibros()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
            IconButton(
                onClick = onBackPressed,
                content = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            )
        Text(
            text = "Libros Favoritos",
            modifier = Modifier.padding(bottom = 16.dp),
                    style = TextStyle(
                    fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
        )

        libros.forEach { libro ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = libro.titulo,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = "Autor: ${libro.autor.joinToString()}",
                        fontSize = 14.sp
                    )
                    Text(
                        text = "Año: ${libro.anio}",
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}
