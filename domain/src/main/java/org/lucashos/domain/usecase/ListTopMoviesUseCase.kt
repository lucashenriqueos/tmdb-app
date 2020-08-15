package org.lucashos.domain.usecase

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.lucashos.domain.entity.MovieBO
import org.lucashos.domain.repository.MovieRepository

class ListTopMoviesUseCase(private val movieRepository: MovieRepository) :
    BaseUseCase<List<MovieBO>, Unit> {
    override fun execute(params: Unit): Single<List<MovieBO>> {
        return movieRepository.listTopMovies().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}