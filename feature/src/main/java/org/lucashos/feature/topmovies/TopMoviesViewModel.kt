package org.lucashos.feature.topmovies

import androidx.lifecycle.MutableLiveData
import org.lucashos.domain.entity.MovieBO
import org.lucashos.domain.usecase.ListTopMoviesUseCase

class TopMoviesViewModel(val topMoviesUseCase: ListTopMoviesUseCase) {
    private val _topMoviesLiveData: MutableLiveData<List<MovieBO>> = MutableLiveData()

    fun getTopMovies() {
        topMoviesUseCase.execute(Unit).subscribe({

        }, {

        })
    }
}