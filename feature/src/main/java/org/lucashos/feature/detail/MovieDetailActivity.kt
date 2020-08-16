package org.lucashos.feature.detail

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
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

    lateinit var movie: MovieDetailBO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
        val movieId = intent.getIntExtra(MOVIE_ID_EXTRA, -1)
        initViews()
        viewModel.getMovieDetail(movieId)
    }

    fun initViews() {
        ll_movie_detail_favourite.setOnClickListener {
            viewModel.updateFavourite(movie)
        }
    }

    fun initObservers() {
        viewModel.movieDetailLiveData.observe({lifecycle}, {
            it.fold(::handleError, ::handleSuccess)
        })

        viewModel.movieFavouriteLiveData.observe({lifecycle}, {
            it.fold(::handleError, ::toggleFavourite)
        })
    }

    fun handleError(error: Throwable) {
        ErrorDialog(this).showDialog()
    }

    fun handleSuccess(movie: MovieDetailBO) {
        this.movie = movie
        tv_movie_detail_title.text = movie.title
        tv_movie_detail_release_date.text = movie.releaseDate
        tv_movie_detail_rating.text = movie.rating.toString()
        tv_movie_Detail_overview.text = movie.overview
        tv_movie_Detail_overview.text = movie.overview

        toggleFavourite(movie.isFavourite)

        picasso
            .load("${getString(R.string.images_base_url)}${movie.posterPath.substring(1)}")
            .fit()
            .centerCrop()
            .into(iv_movie_detail_folder)
    }

    private fun toggleFavourite(isFav: Boolean) {
        if (isFav) {
            iv_movie_detail_favourite.visibility = VISIBLE
            iv_movie_detail_not_favourite.visibility = GONE
        } else {
            iv_movie_detail_favourite.visibility = GONE
            iv_movie_detail_not_favourite.visibility = VISIBLE
        }
    }

    companion object {
        val MOVIE_ID_EXTRA = "MOVIE_ID_EXTRA"
    }
}