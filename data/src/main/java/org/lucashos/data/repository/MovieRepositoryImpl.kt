package org.lucashos.data.repository

import io.reactivex.Single
import org.lucashos.core.api.ApiClient
import org.lucashos.data.domain.mapper.MovieDetailMapper
import org.lucashos.data.domain.mapper.TopRatedMoviesMapper
import org.lucashos.data.service.TmdbApiService
import org.lucashos.domain.entity.MovieDetailBO
import org.lucashos.domain.entity.TopRatedMoviesBO
import org.lucashos.domain.repository.MovieRepository

class MovieRepositoryImpl(private val apiClient: ApiClient) : MovieRepository {
    override fun listTopMovies(page: Int): Single<TopRatedMoviesBO> =
        apiClient.retrofit.create(TmdbApiService::class.java)
            .getTopMovies(page).map { TopRatedMoviesMapper.from(it)}

    override fun getMovieDetail(id: Int): Single<MovieDetailBO> =
        apiClient.retrofit.create(TmdbApiService::class.java)
            .getMovie(id).map { MovieDetailMapper.from(it)}
}