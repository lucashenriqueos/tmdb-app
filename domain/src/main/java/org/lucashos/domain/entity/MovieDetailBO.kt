package org.lucashos.domain.entity

class MovieDetailBO(
    val title: String,
    val overview: String,
    val genres: List<GenreBO>,
    val releaseDate: String,
    val posterPath: String,
    val rating: Double
)