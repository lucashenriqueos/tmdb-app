package org.lucashos.data.domain.response

import com.google.gson.annotations.SerializedName
import org.lucashos.domain.entity.MovieBO
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

data class MovieResponse(
    val id: Long,
    val title: String,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("vote_average") val rating: Double?,
    @SerializedName("genre_ids") val genres: List<Long>
) {
    fun toBO(): MovieBO = MovieBO(
        id = id,
        title = title,
        rating = rating,
        releaseDate = releaseDate,
        posterPath = posterPath,
        genresIds = genres
    )
}