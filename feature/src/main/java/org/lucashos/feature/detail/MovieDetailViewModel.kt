package org.lucashos.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.lucashos.core.base.BaseViewModel
import org.lucashos.core.base.ViewStates
import org.lucashos.domain.entity.MovieDetailBO
import org.lucashos.domain.entity.MoviesListBO
import org.lucashos.domain.usecase.GetMovieDetailUseCase
import org.lucashos.domain.usecase.GetSimilarMoviesUseCase
import org.lucashos.domain.usecase.UpdateFavoriteMovieUseCase
import org.lucashos.domain.utils.Either
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val updateFavouriteUseCase: UpdateFavoriteMovieUseCase,
    private val similarMoviesUseCase: GetSimilarMoviesUseCase
) : BaseViewModel() {
    private val _movieDetailLiveData: MutableLiveData<ViewStates> =
        MutableLiveData()

    val movieDetailLiveData: LiveData<ViewStates>
        get() = _movieDetailLiveData

    private val _movieFavouriteLiveData: MutableLiveData<Either<Throwable, Boolean>> =
        MutableLiveData()

    val movieFavouriteLiveData: LiveData<Either<Throwable, Boolean>>
        get() = _movieFavouriteLiveData

    private val _similarMoviesLiveData: MutableLiveData<Either<Throwable, MoviesListBO>> =
        MutableLiveData()

    val similarMoviesLiveData: LiveData<Either<Throwable, MoviesListBO>>
        get() = _similarMoviesLiveData

    fun getMovieDetail(id: Long) {
        if (id < 0)
            _movieDetailLiveData.postValue(MovieDetailState.EmptyList)
        else
            disposables.add(getMovieDetailUseCase.execute(id).subscribe({
                _movieDetailLiveData.postValue(MovieDetailState.DetailsLoaded(it))
            }, {
                _movieDetailLiveData.postValue(MovieDetailState.Error(it))
            }))
    }

    fun updateFavourite(movie: MovieDetailBO) {
        disposables.add(
            updateFavouriteUseCase.execute(
                UpdateFavoriteMovieUseCase.Params(
                    movie.id,
                    movie.isFavourite
                )
            ).subscribe({
                movie.isFavourite = it
                _movieFavouriteLiveData.value = Either.Right(it)
            }, {
                _movieFavouriteLiveData.value = Either.Left(it)
            })
        )
    }

    fun getSimilarTitles(id: Long) {
        if (id < 0)
            _similarMoviesLiveData.value = Either.Left(Exception())
        else
            disposables.add(
                similarMoviesUseCase.execute(id)
                    .subscribe({
                        _similarMoviesLiveData.value = Either.Right(it)
                    }, {
                        _similarMoviesLiveData.value = Either.Left(it)
                    })
            )
    }
}

sealed class SimilarMoviesState : ViewStates {
}

sealed class MovieDetailState : ViewStates {
    data class DetailsLoaded(val movie: MovieDetailBO) : MovieDetailState()
    data class Error(val error: Throwable?) : ViewStates
    object EmptyList : MovieDetailState()
}