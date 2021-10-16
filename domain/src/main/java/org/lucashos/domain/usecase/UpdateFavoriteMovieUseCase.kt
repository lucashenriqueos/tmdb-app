package org.lucashos.domain.usecase

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.lucashos.core.base.BaseUseCase
import org.lucashos.domain.repository.MovieRepository
import org.lucashos.domain.usecase.UpdateFavoriteMovieUseCase.Params

class UpdateFavoriteMovieUseCase(private val movieRepository: MovieRepository) : BaseUseCase<Boolean, Params> {

    override fun execute(params: Params): Single<Boolean> = if (params.isFav) {
        movieRepository.removeFavourite(params.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { false }
    } else {
        movieRepository.addFavourite(params.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { true }
    }

    data class Params(val id: Long, val isFav: Boolean)
}