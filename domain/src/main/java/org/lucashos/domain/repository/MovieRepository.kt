package org.lucashos.domain.repository

import io.reactivex.Single
import org.lucashos.domain.entity.MovieDetailBO
import org.lucashos.domain.entity.MoviesListBO

interface MovieRepository {
    fun listTopMovies(page: Int): Single<MoviesListBO>

    fun searchMovies(title: String, page: Int): Single<MoviesListBO>

    fun getMovieDetail(id: Int): Single<MovieDetailBO>

    fun findFavourite(id: Int): Single<Int>

    fun addFavourite(id: Int): Single<Any>

    fun removeFavourite(id: Int): Single<Int>
}