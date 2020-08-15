package org.lucashos.feature.topmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.lucashos.core.base.BaseViewModel
import org.lucashos.domain.entity.MovieBO
import org.lucashos.domain.usecase.ListTopMoviesUseCase
import org.lucashos.domain.utils.Either

class TopMoviesViewModel(private val topMoviesUseCase: ListTopMoviesUseCase): BaseViewModel() {
    private val _topMoviesLiveData: MutableLiveData<Either<Throwable,List<MovieBO>>> = MutableLiveData()

    val topMoviesLiveData: LiveData<Either<Throwable,List<MovieBO>>>
        get() = _topMoviesLiveData

    fun getTopMovies() {
        disposables.add(topMoviesUseCase.execute(Unit).subscribe({
            _topMoviesLiveData.value = Either.Right(it)
        }, {
            _topMoviesLiveData.value = Either.Left(it)
        }))
    }
}