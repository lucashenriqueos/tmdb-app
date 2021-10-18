package org.lucashos.feature.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_movie_detail.iv_movie_detail_folder
import kotlinx.android.synthetic.main.activity_movie_detail.rv_movie_detail_related
import kotlinx.android.synthetic.main.activity_movie_detail.tv_movie_detail_overview
import kotlinx.android.synthetic.main.activity_movie_detail.tv_movie_detail_rating
import kotlinx.android.synthetic.main.activity_movie_detail.tv_movie_detail_release_date
import kotlinx.android.synthetic.main.activity_movie_detail.tv_similar_titles
import kotlinx.android.synthetic.main.layout_toolbar.ctv_toolbar_favourite
import kotlinx.android.synthetic.main.layout_toolbar.iv_toolbar_share
import kotlinx.android.synthetic.main.layout_toolbar.tv_toolbar_title
import org.lucashos.core.base.BaseActivity
import org.lucashos.core.dialog.ErrorDialog
import org.lucashos.core.extension.gone
import org.lucashos.core.extension.loadImage
import org.lucashos.core.extension.toDateFormat
import org.lucashos.core.extension.visible
import org.lucashos.domain.entity.MovieBO
import org.lucashos.domain.entity.MovieDetailBO
import org.lucashos.domain.entity.MoviesListBO
import org.lucashos.feature.R
import java.util.logging.Logger
import javax.inject.Inject

class MovieDetailActivity : BaseActivity(R.layout.activity_movie_detail) {

    @Inject
    lateinit var viewModel: MovieDetailViewModel

    lateinit var movie: MovieDetailBO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieId = intent.getLongExtra(MOVIE_ID_EXTRA, -1)
        initObservers()
        initViews()
        setupRecyclerView()
        viewModel.getMovieDetail(movieId)
        viewModel.getSimilarTitles(movieId)
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_movie_detail_related.layoutManager = layoutManager
    }

    private fun initViews() {
        ctv_toolbar_favourite.visible()
        iv_toolbar_share.visible()
        ctv_toolbar_favourite.setOnClickListener {
            viewModel.updateFavourite(movie)
        }
    }

    private fun initObservers() {
        viewModel.movieDetailLiveData.observe({ lifecycle }, { state ->
            when (state) {
                is MovieDetailState.DetailsLoaded -> handleSuccess(state.movie)
                is MovieDetailState.Error -> handleError(state.error)
                is MovieDetailState.EmptyList -> {
                }
            }
        })

        viewModel.movieFavouriteLiveData.observe({ lifecycle }, {
            it.fold(::handleError, ::toggleFavourite)
        })

        viewModel.similarMoviesLiveData.observe({ lifecycle }, {
            it.fold(::handleSimilarMoviesError, ::handleSimilarMoviesSuccess)
        })
    }

    private fun handleSimilarMoviesSuccess(moviesListBO: MoviesListBO) {
        if (moviesListBO.movies.isEmpty())
            hideSimilarTitles()
        val adapter = RelatedMoviesAdapter(moviesListBO.movies) {
            goToMovieDetails(it)
        }
        rv_movie_detail_related.adapter = adapter
    }

    private fun handleSimilarMoviesError(throwable: Throwable) {
        hideSimilarTitles()
    }

    private fun hideSimilarTitles() {
        rv_movie_detail_related.gone()
        tv_similar_titles.gone()
    }

    private fun handleError(error: Throwable?) {
        Log.e("error", "An error occurred", error)
        ErrorDialog(this).showDialog()
    }

    private fun handleSuccess(movie: MovieDetailBO) {
        this.movie = movie
        tv_toolbar_title.text = movie.title
        tv_movie_detail_release_date.text =
            movie.releaseDate?.toDateFormat() ?: getString(R.string.unkown_release_date)
        tv_movie_detail_rating.text = movie.rating.toString()
        tv_movie_detail_overview.text = movie.overview

        toggleFavourite(movie.isFavourite)

        iv_movie_detail_folder loadImage getString(R.string.images_base_url, movie.posterPath)
    }

    private fun toggleFavourite(isFav: Boolean) {
        ctv_toolbar_favourite.isChecked = isFav
    }

    private fun goToMovieDetails(movie: MovieBO) = startActivity(getStartIntent(this, movie.id))

    companion object {
        private const val MOVIE_ID_EXTRA = "MOVIE_ID_EXTRA"

        fun getStartIntent(context: Context, movieId: Long) = Intent(context, MovieDetailActivity::class.java).apply {
            putExtra(MOVIE_ID_EXTRA, movieId)
        }
    }
}