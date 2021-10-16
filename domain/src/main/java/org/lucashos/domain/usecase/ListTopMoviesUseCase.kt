package org.lucashos.domain.usecase

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.lucashos.core.base.BaseUseCase
import org.lucashos.domain.entity.MoviesListBO
import org.lucashos.domain.repository.MovieRepository

class ListTopMoviesUseCase(private val movieRepository: MovieRepository) : BaseUseCase<MoviesListBO, Int> {

    override fun execute(params: Int): Single<MoviesListBO> {
        return movieRepository.listTopMovies(params).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}