package org.lucashos.domain.entity

data class MoviesListBO(
    val page: Int,
    val totalPages: Int,
    val movies: List<MovieBO>
)