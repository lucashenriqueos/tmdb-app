package org.lucashos.domain.usecase

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.lucashos.domain.entity.TopRatedMoviesBO
import org.lucashos.domain.repository.MovieRepository

class ListTopMoviesUseCase(private val movieRepository: MovieRepository) :
    org.lucashos.core.base.BaseUseCase<TopRatedMoviesBO, Unit> {
    override fun execute(params: Unit): Single<TopRatedMoviesBO> {
        return movieRepository.listTopMovies().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}