package org.lucashos.domain.usecase

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.lucashos.core.base.BaseUseCase
import org.lucashos.domain.entity.MoviesListBO
import org.lucashos.domain.repository.MovieRepository
import org.lucashos.domain.usecase.SearchMoviesUseCase.Params

class SearchMoviesUseCase(private val movieRepository: MovieRepository) : BaseUseCase<MoviesListBO, Params> {

    override fun execute(params: Params): Single<MoviesListBO> {
        return movieRepository.searchMovies(params.title, params.page).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    data class Params(val title: String, val page: Int)
}