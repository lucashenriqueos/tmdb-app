package org.lucashos.domain.usecase

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.lucashos.domain.entity.MovieDetailBO
import org.lucashos.domain.entity.TopRatedMoviesBO
import org.lucashos.domain.repository.MovieRepository

class GetMovieDetailUseCase(private val movieRepository: MovieRepository) :
    org.lucashos.core.base.BaseUseCase<MovieDetailBO, Int> {
    override fun execute(params: Int): Single<MovieDetailBO> {
        return movieRepository.getMovieDetail(params).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}