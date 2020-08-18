package org.lucashos.domain.usecase

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.lucashos.domain.repository.MovieRepository

class CheckFavoriteMovieUseCase(private val movieRepository: MovieRepository) :
    org.lucashos.core.base.BaseUseCase<Boolean, Int> {
    override fun execute(params: Int): Single<Boolean> {
        return movieRepository.findFavourite(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it > 0 }
    }
}