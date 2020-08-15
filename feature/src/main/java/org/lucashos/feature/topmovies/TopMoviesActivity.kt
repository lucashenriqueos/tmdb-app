package org.lucashos.feature.topmovies

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_top_movies.*
import org.lucashos.core.base.BaseActivity
import org.lucashos.domain.entity.MovieBO
import org.lucashos.feature.R
import javax.inject.Inject

class TopMoviesActivity : BaseActivity(R.layout.activity_top_movies) {

    @Inject
    lateinit var topMoviesViewModel: TopMoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        topMoviesViewModel.topMoviesLiveData.observe({ lifecycle }) {
            it.fold(::handleMovieError, ::handleMovieSuccess)
        }
        topMoviesViewModel.getTopMovies()
    }

    private fun handleMovieSuccess(movies: List<MovieBO>) {
        rv_movies_list.layoutManager = LinearLayoutManager(this)
        rv_movies_list.adapter = TopMoviesAdapter(movies)
    }

    private fun handleMovieError(error: Throwable) {
    }
}