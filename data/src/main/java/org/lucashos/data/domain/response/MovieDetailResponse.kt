package org.lucashos.data.domain.response

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    val id: Int,
    val title: String,
    val overview: String,
    val genres: List<GenreResponse>,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("vote_average") val rating: Double
)