package org.lucashos.data.repository

import io.reactivex.Single
import org.lucashos.core.api.ApiClient
import org.lucashos.data.db.AppDatabase
import org.lucashos.data.domain.entity.MovieEntity
import org.lucashos.data.domain.mapper.MovieDetailMapper
import org.lucashos.data.domain.mapper.TopRatedMoviesMapper
import org.lucashos.data.service.TmdbApiService
import org.lucashos.domain.entity.MovieDetailBO
import org.lucashos.domain.entity.TopRatedMoviesBO
import org.lucashos.domain.repository.MovieRepository

class MovieRepositoryImpl(private val apiClient: ApiClient, private val appDatabase: AppDatabase) : MovieRepository {
    override fun listTopMovies(page: Int): Single<TopRatedMoviesBO> =
        apiClient.retrofit.create(TmdbApiService::class.java)
            .getTopMovies(page).map { TopRatedMoviesMapper.from(it)}

    override fun getMovieDetail(id: Int): Single<MovieDetailBO> =
        apiClient.retrofit.create(TmdbApiService::class.java)
            .getMovie(id).map { MovieDetailMapper.from(it)}

    override fun findFavourite(id: Int): Single<Int> =
        appDatabase.movieDao().findFavourite(id)

    override fun addFavourite(id: Int): Single<Any> =
        appDatabase.movieDao().insert(MovieEntity(id)).toSingleDefault(true)

    override fun removeFavourite(id: Int): Single<Int> = appDatabase.movieDao().delete(MovieEntity(id))


}