package org.lucashos.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.lucashos.core.base.BaseViewModel
import org.lucashos.domain.entity.MovieDetailBO
import org.lucashos.domain.usecase.GetMovieDetailUseCase
import org.lucashos.domain.usecase.UpdateFavoriteMovieUseCase
import org.lucashos.domain.utils.Either

class MovieDetailViewModel(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val updateFavouriteUseCase: UpdateFavoriteMovieUseCase
) : BaseViewModel() {
    private val _movieDetailLiveData: MutableLiveData<Either<Throwable, MovieDetailBO>> =
        MutableLiveData()

    val movieDetailLiveData: LiveData<Either<Throwable, MovieDetailBO>>
        get() = _movieDetailLiveData

    private val _movieFavouriteLiveData: MutableLiveData<Either<Throwable, Boolean>> =
        MutableLiveData()

    val movieFavouriteLiveData: LiveData<Either<Throwable, Boolean>>
        get() = _movieFavouriteLiveData

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
                _movieFavouriteLiveData.value = Either.Right(it)
            }, {
                _movieFavouriteLiveData.value = Either.Left(it)
            })
        )
    }

}