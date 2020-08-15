package org.lucashos.domain.repository

import io.reactivex.Single
import org.lucashos.domain.entity.MovieBO

interface MovieRepository {
    fun listTopMovies(): Single<List<MovieBO>>
}