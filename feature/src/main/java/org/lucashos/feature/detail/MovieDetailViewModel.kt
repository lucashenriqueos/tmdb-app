package org.lucashos.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.lucashos.core.base.BaseViewModel
import org.lucashos.domain.entity.MovieDetailBO
import org.lucashos.domain.entity.TopRatedMoviesBO
import org.lucashos.domain.usecase.GetMovieDetailUseCase
import org.lucashos.domain.utils.Either
import java.lang.Exception

class MovieDetailViewModel(private val useCase: GetMovieDetailUseCase) : BaseViewModel() {
    private val _movieDetailLiveData: MutableLiveData<Either<Throwable, MovieDetailBO>> =
        MutableLiveData()

    val movieDetailLiveData: LiveData<Either<Throwable, MovieDetailBO>>
        get() = _movieDetailLiveData

    fun getMovieDetail(id: Int) {
        if (id < 0)
            _movieDetailLiveData.value = Either.Left(Exception())
        else
            disposables.add(useCase.execute(id).subscribe({
                _movieDetailLiveData.value = Either.Right(it)
            }, {
                _movieDetailLiveData.value = Either.Left(it)
            }))
    }

}