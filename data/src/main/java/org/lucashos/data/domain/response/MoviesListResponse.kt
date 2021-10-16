package org.lucashos.data.domain.response

import com.google.gson.annotations.SerializedName
import org.lucashos.domain.entity.MoviesListBO

data class MoviesListResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("results") val movies: List<MovieResponse>
) {
    fun toBO() = MoviesListBO(
        page = page,
        totalPages = totalPages,
        movies = movies.map { it.toBO() }
    )
}