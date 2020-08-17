package org.lucashos.domain.entity

class MovieBO(
    val id: Int,
    val title: String,
    val overview: String,
    val genres: List<Int>,
    val releaseDate: String?,
    val posterPath: String?,
    val rating: Double
)