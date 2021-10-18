package org.lucashos.data.repository

import io.reactivex.Single
import org.lucashos.core.api.ApiClient
import org.lucashos.data.db.AppDatabase
import org.lucashos.data.domain.entity.MovieEntity
import org.lucashos.data.service.TmdbApiService
import org.lucashos.domain.entity.GenreBO
import org.lucashos.domain.entity.MovieBO
import org.lucashos.domain.entity.MovieDetailBO
import org.lucashos.domain.entity.MoviesListBO
import org.lucashos.domain.repository.MovieRepository

class MovieRepositoryImpl(private val apiClient: ApiClient, private val appDatabase: AppDatabase) : MovieRepository {

    override fun listTopMovies(page: Int): Single<MoviesListBO> =
        apiClient.retrofit.create(TmdbApiService::class.java)
            .getTopMovies(page).map { it.toBO() }

    override fun searchMovies(title: String, page: Int): Single<MoviesListBO> =
        apiClient.retrofit.create(TmdbApiService::class.java)
            .searchMovies(title, page).map { it.toBO() }

    override fun getSimilarMovies(id: Long): Single<MoviesListBO> =
        apiClient.retrofit.create(TmdbApiService::class.java)
            .getSimilarMovies(id).map { it.toBO() }

    override fun getMovieDetail(id: Long): Single<MovieDetailBO> =
        apiClient.retrofit.create(TmdbApiService::class.java)
            .getMovie(id).map { it.toBO() }

    override fun getRandomPopularMovie(): Single<MovieBO> =
        apiClient.retrofit.create(TmdbApiService::class.java)
            .getPopularMovies()
            .map { it.movies.random() }
            .map { it.toBO() }

    override fun getGenres(): Single<List<GenreBO>> =
        apiClient.retrofit.create(TmdbApiService::class.java)
            .getGenres()
            .map {
                it.genres.map { genre ->
                    genre.toBO()
                }
            }

    override fun findFavourite(id: Long): Single<Int> =
        appDatabase.movieDao().findFavourite(id)

    override fun addFavourite(id: Long): Single<Any> =
        appDatabase.movieDao().insert(MovieEntity(id)).toSingleDefault(true)

    override fun removeFavourite(id: Long): Single<Int> = appDatabase.movieDao().delete(MovieEntity(id))


}