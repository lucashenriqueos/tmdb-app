package org.lucashos.feature.detail

import android.os.Bundle
import android.util.Log
import com.squareup.picasso.Picasso
import org.lucashos.core.base.BaseActivity
import org.lucashos.core.dialog.ErrorDialog
import org.lucashos.domain.entity.MovieDetailBO
import org.lucashos.feature.R
import javax.inject.Inject

class MovieDetailActivity : BaseActivity(R.layout.activity_movie_detail) {

    @Inject
    lateinit var viewModel: MovieDetailViewModel

    @Inject
    lateinit var picasso: Picasso

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
        val movieId = intent.getIntExtra(MOVIE_ID_EXTRA, -1)
        viewModel.getMovieDetail(movieId)
    }

    fun initObservers() {
        viewModel.movieDetailLiveData.observe({lifecycle}, {
            it.fold(::handleError, ::handleSuccess)
        })
    }

    fun handleError(error: Throwable) {
        ErrorDialog(this).showDialog()
    }

    fun handleSuccess(movie: MovieDetailBO) {
        Log.d("d", movie.title)
    }

    companion object {
        val MOVIE_ID_EXTRA = "MOVIE_ID_EXTRA"
    }
}