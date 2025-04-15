package com.ucb.framework.mappers

import com.ucb.domain.Book
import com.ucb.domain.Gitalias
import com.ucb.domain.Movie
import com.ucb.framework.bookDB.BookTable
import com.ucb.framework.dto.AvatarResponseDto
import com.ucb.framework.dto.BookDto
import com.ucb.framework.dto.MovieDto
import com.ucb.framework.persistence.GitAccount

fun AvatarResponseDto.toModel(): Gitalias {
    return Gitalias(
        login = login,
        avatarUrl = url
    )
}

fun Gitalias.toEntity(): GitAccount {
    return GitAccount(login)
}

fun GitAccount.toModel(): Gitalias {
    return Gitalias(
        alias,
        ""
    )
}

fun MovieDto.toModel(): Movie {
    return Movie(
        title = title,
        overview = overview,
        posterPath = posterPath
    )
}

fun BookDto.toModel(): Book {
    return Book(
        titulo = title ?: "Sin título",
        autor = author_name ?: listOf("Autor desconocido"),
        anio = first_publish_year ?: -1
    )
}

fun BookTable.toModel(): Book {
    return Book(
        titulo = titulo,
        autor = autor,
        anio = anio
    )
}

fun Book.toEntity(): BookTable {
    return BookTable(
        titulo = titulo,
        autor = autor,
        anio = anio
    )
}