package org.lucashos.domain.usecase

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.lucashos.core.base.BaseUseCase
import org.lucashos.domain.entity.MoviesListBO
import org.lucashos.domain.repository.MovieRepository

class GetSimilarMoviesUseCase(private val movieRepository: MovieRepository) : BaseUseCase<MoviesListBO, Long> {

    override fun execute(params: Long): Single<MoviesListBO> {
        return movieRepository.getSimilarMovies(params).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}