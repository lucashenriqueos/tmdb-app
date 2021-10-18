package org.lucashos.domain.repository

import io.reactivex.Single
import org.lucashos.domain.entity.GenreBO
import org.lucashos.domain.entity.MovieBO
import org.lucashos.domain.entity.MovieDetailBO
import org.lucashos.domain.entity.MoviesListBO

interface MovieRepository {
    fun listTopMovies(page: Int): Single<MoviesListBO>

    fun searchMovies(title: String, page: Int): Single<MoviesListBO>

    fun getMovieDetail(id: Long): Single<MovieDetailBO>

    fun getSimilarMovies(id: Long): Single<MoviesListBO>

    fun findFavourite(id: Long): Single<Int>

    fun addFavourite(id: Long): Single<Any>

    fun removeFavourite(id: Long): Single<Int>

    fun getRandomPopularMovie(): Single<MovieBO>

    fun getGenres(): Single<List<GenreBO>>
}