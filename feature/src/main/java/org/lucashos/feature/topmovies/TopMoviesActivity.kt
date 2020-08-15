package org.lucashos.feature.topmovies

import android.os.Bundle
import android.util.Log
import org.lucashos.core.base.BaseActivity
import org.lucashos.feature.R
import javax.inject.Inject

class TopMoviesActivity : BaseActivity(R.layout.activity_top_movies) {

    @Inject
    lateinit var topMoviesViewModel: TopMoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        topMoviesViewModel.topMoviesLiveData.observe({ lifecycle }, {
            it.fold({

            }, {movies ->
                Log.d("d", movies[0].title)
            })
        })
        topMoviesViewModel.getTopMovies()
    }
}