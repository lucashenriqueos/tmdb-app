package org.lucashos.domain.usecase

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import org.lucashos.core.base.BaseUseCase
import org.lucashos.domain.entity.MovieDetailBO
import org.lucashos.domain.repository.MovieRepository

class GetMovieDetailUseCase(
    private val movieRepository: MovieRepository,
    private val checkFavoriteMovieUseCase: CheckFavoriteMovieUseCase
) : BaseUseCase<MovieDetailBO, Long> {

    override fun execute(params: Long): Single<MovieDetailBO> {
        return Single.zip(movieRepository.getMovieDetail(params).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()),
            checkFavoriteMovieUseCase.execute(params),
            BiFunction { movieDetail, isFavourite ->
                movieDetail.isFavourite = isFavourite
                return@BiFunction movieDetail
            })
    }
}