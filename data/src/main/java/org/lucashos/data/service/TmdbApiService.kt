package org.lucashos.data.service

import io.reactivex.Single
import org.lucashos.data.domain.response.MovieDetailResponse
import org.lucashos.data.domain.response.MoviesListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApiService {
    @GET("movie/top_rated")
    fun getTopMovies(@Query("page") page: Int = 1): Single<MoviesListResponse>

    @GET("movie/{id}")
    fun getMovie(@Path("id") id: Int): Single<MovieDetailResponse>

    @GET("search/movie")
    fun searchMovies(@Query("query") title: String, @Query("page") page: Int): Single<MoviesListResponse>
}