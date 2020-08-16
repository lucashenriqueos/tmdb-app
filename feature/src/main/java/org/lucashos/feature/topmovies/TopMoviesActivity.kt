package org.lucashos.feature.topmovies

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_top_movies.*
import org.lucashos.core.base.BaseActivity
import org.lucashos.domain.entity.TopRatedMoviesBO
import org.lucashos.feature.R
import javax.inject.Inject

class TopMoviesActivity : BaseActivity(R.layout.activity_top_movies) {

    @Inject
    lateinit var topMoviesViewModel: TopMoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
        topMoviesViewModel.getTopMovies()
    }

    private fun initObservers() {
        topMoviesViewModel.topMoviesLiveData.observe({ lifecycle }) {
            it.fold(::handleMovieError, ::handleMovieSuccess)
        }
    }

    private fun handleMovieSuccess(topRatedMovies: TopRatedMoviesBO) {
        rv_movies_list.layoutManager = LinearLayoutManager(this)
        rv_movies_list.adapter = TopMoviesAdapter(topRatedMovies.movies)
    }

    private fun handleMovieError(error: Throwable) {
        TODO("Not yet implemented")
    }
}