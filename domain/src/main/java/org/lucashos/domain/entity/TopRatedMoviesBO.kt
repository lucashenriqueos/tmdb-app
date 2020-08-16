package org.lucashos.domain.entity

data class TopRatedMoviesBO(
    val page: Int,
    val totalPages: Int,
    val movies: List<MovieBO>
)