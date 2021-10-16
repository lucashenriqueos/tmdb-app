package org.lucashos.domain.entity

class MovieDetailBO(
    val id: Long,
    val title: String,
    val overview: String,
    val releaseDate: String?,
    val posterPath: String?,
    val rating: Double,
    var isFavourite: Boolean = false
)