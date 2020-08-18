package org.lucashos.domain.usecase

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.lucashos.domain.repository.MovieRepository

class UpdateFavoriteMovieUseCase(private val movieRepository: MovieRepository) :
    org.lucashos.core.base.BaseUseCase<Boolean, UpdateFavoriteMovieUseCase.Params> {
    override fun execute(params: Params): Single<Boolean> = if (params.isFav)
        movieRepository.removeFavourite(params.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { false }
    else
        movieRepository.addFavourite(params.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { true }

    data class Params(val id: Int, val isFav: Boolean)
}