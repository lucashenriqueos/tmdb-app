package org.lucashos.data.domain.response

import com.google.gson.annotations.SerializedName

data class MoviesListResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("results") val movies: List<MovieResponse>
)