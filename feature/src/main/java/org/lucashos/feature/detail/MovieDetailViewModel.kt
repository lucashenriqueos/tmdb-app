package org.lucashos.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.lucashos.core.base.BaseViewModel
import org.lucashos.domain.entity.MovieDetailBO
import org.lucashos.domain.entity.MoviesListBO
import org.lucashos.domain.usecase.GetMovieDetailUseCase
import org.lucashos.domain.usecase.GetSimilarMoviesUseCase
import org.lucashos.domain.usecase.UpdateFavoriteMovieUseCase
import org.lucashos.domain.utils.Either

class MovieDetailViewModel(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val updateFavouriteUseCase: UpdateFavoriteMovieUseCase,
    private val similarMoviesUseCase: GetSimilarMoviesUseCase
) : BaseViewModel() {
    private val _movieDetailLiveData: MutableLiveData<Either<Throwable, MovieDetailBO>> =
        MutableLiveData()

    val movieDetailLiveData: LiveData<Either<Throwable, MovieDetailBO>>
        get() = _movieDetailLiveData

    private val _movieFavouriteLiveData: MutableLiveData<Either<Throwable, Boolean>> =
        MutableLiveData()

    val movieFavouriteLiveData: LiveData<Either<Throwable, Boolean>>
        get() = _movieFavouriteLiveData

    private val _similarMoviesLiveData: MutableLiveData<Either<Throwable, MoviesListBO>> =
        MutableLiveData()

    val similarMoviesLiveData: LiveData<Either<Throwable, MoviesListBO>>
        get() = _similarMoviesLiveData

    fun getMovieDetail(id: Int) {
        if (id < 0)
            _movieDetailLiveData.value = Either.Left(Exception())
        else
            disposables.add(getMovieDetailUseCase.execute(id).subscribe({
                _movieDetailLiveData.value = Either.Right(it)
            }, {
                _movieDetailLiveData.value = Either.Left(it)
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

    fun getSimilarTitles(id: Int) {
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