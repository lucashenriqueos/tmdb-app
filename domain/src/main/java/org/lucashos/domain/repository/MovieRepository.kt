package org.lucashos.domain.repository

import io.reactivex.Single
import org.lucashos.domain.entity.TopRatedMoviesBO

interface MovieRepository {
    fun listTopMovies(page: Int): Single<TopRatedMoviesBO>
}