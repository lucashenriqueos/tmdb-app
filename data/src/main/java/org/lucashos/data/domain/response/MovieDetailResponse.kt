package org.lucashos.data.domain.response

import com.google.gson.annotations.SerializedName
import org.lucashos.domain.entity.MovieDetailBO

data class MovieDetailResponse(
    val id: Long,
    val title: String,
    val overview: String,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("vote_average") val rating: Double
) {
    fun toBO() = MovieDetailBO(
        id = id,
        title = title,
        rating = rating,
        releaseDate = releaseDate,
        posterPath = posterPath,
        overview = overview
    )
}