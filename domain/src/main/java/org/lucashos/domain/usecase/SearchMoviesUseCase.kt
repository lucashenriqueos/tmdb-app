package org.lucashos.domain.usecase

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.lucashos.domain.entity.MoviesListBO
import org.lucashos.domain.repository.MovieRepository

class SearchMoviesUseCase(private val movieRepository: MovieRepository) :
    org.lucashos.core.base.BaseUseCase<MoviesListBO, SearchMoviesUseCase.Params> {
    override fun execute(params: Params): Single<MoviesListBO> {
        return movieRepository.searchMovies(params.title, params.page).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    data class Params(val title: String, val page: Int)
}