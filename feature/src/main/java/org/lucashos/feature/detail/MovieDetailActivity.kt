package org.lucashos.feature.detail

import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*
import org.lucashos.core.base.BaseActivity
import org.lucashos.core.dialog.ErrorDialog
import org.lucashos.core.extension.gone
import org.lucashos.core.extension.toDateFormat
import org.lucashos.core.extension.visible
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
        tv_movie_detail_release_date.text = movie.releaseDate?.toDateFormat() ?: getString(R.string.unkown_release_date)
        tv_movie_detail_rating.text = movie.rating.toString()
        tv_movie_Detail_overview.text = movie.overview
        tv_movie_Detail_overview.text = movie.overview

        toggleFavourite(movie.isFavourite)

        movie.posterPath?.let {
            picasso
                .load("${getString(R.string.images_base_url)}${it.substring(1)}")
                .fit()
                .centerCrop()
                .into(iv_movie_detail_folder)
        }
    }

    private fun toggleFavourite(isFav: Boolean) {
        if (isFav) {
            iv_movie_detail_favourite.visible()
            iv_movie_detail_not_favourite.gone()
        } else {
            iv_movie_detail_favourite.gone()
            iv_movie_detail_not_favourite.visible()
        }
    }

    companion object {
        val MOVIE_ID_EXTRA = "MOVIE_ID_EXTRA"
    }
}