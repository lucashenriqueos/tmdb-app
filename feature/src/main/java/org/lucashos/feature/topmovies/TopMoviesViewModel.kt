package org.lucashos.feature.topmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.lucashos.core.base.BaseViewModel
import org.lucashos.domain.entity.MoviesListBO
import org.lucashos.domain.usecase.ListTopMoviesUseCase
import org.lucashos.domain.usecase.SearchMoviesUseCase
import org.lucashos.domain.utils.Either

class TopMoviesViewModel(
    private val topMoviesUseCase: ListTopMoviesUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase
) : BaseViewModel() {
    private val _movieListLiveData: MutableLiveData<Either<Throwable, MoviesListBO>> =
        MutableLiveData()

    val moviesListLiveData: LiveData<Either<Throwable, MoviesListBO>>
        get() = _movieListLiveData

    fun getTopMovies(page: Int = 1) {
        disposables.add(topMoviesUseCase.execute(page).subscribe({
            _movieListLiveData.value = Either.Right(it)
        }, {
            _movieListLiveData.value = Either.Left(it)
        }))
    }

    fun searchMovie(title: String) {
        disposables.add(searchMoviesUseCase.execute(title).subscribe({
            _movieListLiveData.value = Either.Right(it)
        }, {
            _movieListLiveData.value = Either.Left(it)
        }))
    }
}