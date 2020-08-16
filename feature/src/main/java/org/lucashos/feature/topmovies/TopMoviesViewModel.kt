package org.lucashos.feature.topmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.lucashos.core.base.BaseViewModel
import org.lucashos.domain.entity.TopRatedMoviesBO
import org.lucashos.domain.usecase.ListTopMoviesUseCase
import org.lucashos.domain.utils.Either

class TopMoviesViewModel(private val topMoviesUseCase: ListTopMoviesUseCase) : BaseViewModel() {
    private val _topMoviesLiveData: MutableLiveData<Either<Throwable, TopRatedMoviesBO>> =
        MutableLiveData()

    val topMoviesLiveData: LiveData<Either<Throwable, TopRatedMoviesBO>>
        get() = _topMoviesLiveData

    fun getTopMovies(page: Int = 1) {
        disposables.add(topMoviesUseCase.execute(page).subscribe({
            _topMoviesLiveData.value = Either.Right(it)
        }, {
            _topMoviesLiveData.value = Either.Left(it)
        }))
    }
}