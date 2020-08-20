package org.lucashos.feature.detail

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_detail.*
import org.lucashos.core.base.BaseActivity
import org.lucashos.core.dialog.ErrorDialog
import org.lucashos.core.extension.gone
import org.lucashos.core.extension.toDateFormat
import org.lucashos.core.extension.visible
import org.lucashos.domain.entity.MovieBO
import org.lucashos.domain.entity.MovieDetailBO
import org.lucashos.domain.entity.MoviesListBO
import org.lucashos.feature.R
import javax.inject.Inject

class MovieDetailActivity : BaseActivity(R.layout.activity_movie_detail) {

    @Inject
    lateinit var viewModel: MovieDetailViewModel

    lateinit var movie: MovieDetailBO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieId = intent.getIntExtra(MOVIE_ID_EXTRA, -1)
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

    fun initViews() {
        ll_movie_detail_favourite.setOnClickListener {
            viewModel.updateFavourite(movie)
        }
    }

    fun initObservers() {
        viewModel.movieDetailLiveData.observe({ lifecycle }, {
            it.fold(::handleError, ::handleSuccess)
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
        val adapter = RelatedMoviesAdapter(moviesListBO.movies)
        rv_movie_detail_related.adapter = adapter
        adapter.onClick.subscribe(::loadMovieDetails)
    }

    private fun handleSimilarMoviesError(throwable: Throwable) {
        hideSimilarTitles()
    }

    private fun hideSimilarTitles() {
        ll__movie_detail_related.gone()
    }

    fun handleError(error: Throwable) {
        ErrorDialog(this).showDialog()
    }

    fun handleSuccess(movie: MovieDetailBO) {
        this.movie = movie
        tv_movie_detail_title.text = movie.title
        tv_movie_detail_release_date.text =
            movie.releaseDate?.toDateFormat() ?: getString(R.string.unkown_release_date)
        tv_movie_detail_rating.text = movie.rating.toString()
        tv_movie_detail_overview.text = movie.overview

        toggleFavourite(movie.isFavourite)

        movie.posterPath?.let {
            Glide.with(this)
                .load("${getString(R.string.images_base_url)}${it.substring(1)}")
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

    fun loadMovieDetails(movie: MovieBO) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.MOVIE_ID_EXTRA, movie.id)
        startActivity(intent)
    }

    companion object {
        val MOVIE_ID_EXTRA = "MOVIE_ID_EXTRA"
    }
}