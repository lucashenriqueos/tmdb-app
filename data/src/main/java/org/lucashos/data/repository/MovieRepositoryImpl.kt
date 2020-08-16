package org.lucashos.data.repository

import io.reactivex.Single
import org.lucashos.core.api.ApiClient
import org.lucashos.data.domain.mapper.TopRatedMoviesMapper
import org.lucashos.data.service.TmdbApiService
import org.lucashos.domain.entity.TopRatedMoviesBO
import org.lucashos.domain.repository.MovieRepository

class MovieRepositoryImpl(private val apiClient: ApiClient) : MovieRepository {
    override fun listTopMovies(): Single<TopRatedMoviesBO> =
        apiClient.retrofit.create(TmdbApiService::class.java)
            .getTopMovies().map { TopRatedMoviesMapper.from(it)}
}