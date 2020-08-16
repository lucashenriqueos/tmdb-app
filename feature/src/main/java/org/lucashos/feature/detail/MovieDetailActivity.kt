package org.lucashos.feature.detail

import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*
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
        tv_movie_detail_title.text = movie.title
        tv_movie_detail_release_date.text = movie.releaseDate
        tv_movie_detail_rating.text = movie.rating.toString()
        tv_movie_Detail_overview.text = movie.overview
        picasso
            .load("${getString(R.string.images_base_url)}${movie.posterPath.substring(1)}")
            .fit()
            .centerCrop()
            .into(iv_movie_detail_folder)
    }

    companion object {
        val MOVIE_ID_EXTRA = "MOVIE_ID_EXTRA"
    }
}