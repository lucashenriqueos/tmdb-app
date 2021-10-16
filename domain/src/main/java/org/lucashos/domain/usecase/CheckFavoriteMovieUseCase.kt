package org.lucashos.domain.usecase

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.lucashos.core.base.BaseUseCase
import org.lucashos.domain.repository.MovieRepository

class CheckFavoriteMovieUseCase(private val movieRepository: MovieRepository) : BaseUseCase<Boolean, Long> {

    override fun execute(params: Long): Single<Boolean> {
        return movieRepository.findFavourite(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it > 0 }
    }
}