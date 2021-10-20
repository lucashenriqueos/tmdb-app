package org.lucashos.feature.topmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.lucashos.core.base.BaseViewModel
import org.lucashos.core.base.ViewStates
import org.lucashos.domain.entity.MovieBO
import org.lucashos.domain.entity.MoviesListBO
import org.lucashos.domain.usecase.GetPopularMovieUseCase
import org.lucashos.domain.usecase.ListTopMoviesUseCase
import org.lucashos.domain.utils.Either
import org.lucashos.feature.topmovies.PopularMovieState.Error
import org.lucashos.feature.topmovies.PopularMovieState.HighlightMovie
import javax.inject.Inject

class TopMoviesViewModel @Inject constructor(
    private val topMoviesUseCase: ListTopMoviesUseCase,
    private val popularMovieUseCase: GetPopularMovieUseCase
) : BaseViewModel() {
    private val _movieListLiveData: MutableLiveData<Either<Throwable, MoviesListBO>> =
        MutableLiveData()

    val moviesListLiveData: LiveData<Either<Throwable, MoviesListBO>>
        get() = _movieListLiveData

    private val _popularMovieLiveData: MutableLiveData<PopularMovieState> =
        MutableLiveData()

    val popularMovieLiveData: LiveData<PopularMovieState>
        get() = _popularMovieLiveData

    fun getTopMovies(page: Int = 1) {
        disposables.add(topMoviesUseCase.execute(page).subscribe({
            _movieListLiveData.value = Either.Right(it)
        }, {
            _movieListLiveData.value = Either.Left(it)
        }))
    }

    fun getPopularMovie() = popularMovieUseCase.execute(Unit).subscribe({
        _popularMovieLiveData.postValue(HighlightMovie(it))
    }, {
        _popularMovieLiveData.postValue(Error(it))
    })
}

sealed class PopularMovieState : ViewStates {
    data class HighlightMovie(val movie: MovieBO) : PopularMovieState()
    data class Error(val error: Throwable) : PopularMovieState()
}
