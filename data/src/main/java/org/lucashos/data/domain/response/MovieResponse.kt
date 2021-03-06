package org.lucashos.data.domain.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val id: Int,
    val title: String,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("vote_average") val rating: Double?
)