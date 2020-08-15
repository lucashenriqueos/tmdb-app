package org.lucashos.domain.entity

class MovieBO(
    val title: String,
    val overview: String,
    val genres: List<GenreBO>,
    val releaseDate: String,
    val posterPath: String
)