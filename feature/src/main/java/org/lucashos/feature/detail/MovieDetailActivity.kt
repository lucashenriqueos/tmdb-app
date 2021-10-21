package org.lucashos.feature.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
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
import org.lucashos.feature.databinding.ActivityMovieDetailBinding
import javax.inject.Inject

class MovieDetailActivity : BaseActivity() {

    override val binding by lazy { ActivityMovieDetailBinding.inflate(layoutInflater) }

    @Inject
    lateinit var viewModel: MovieDetailViewModel

    private lateinit var movie: MovieDetailBO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieId = intent.getLongExtra(MOVIE_ID_EXTRA, -1)
        initObservers()
        initViews()
        viewModel.getMovieDetail(movieId)
        viewModel.getSimilarTitles(movieId)
    }

    private fun initViews() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvMovieDetailRelated.layoutManager = layoutManager

        with(binding.toolbar) {
            ivShare.visible()
            ctvFavourite.apply {
                visible()
                setOnClickListener {
                    viewModel.updateFavourite(movie)
                }
            }
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
        binding.rvMovieDetailRelated.adapter = adapter
    }

    private fun handleSimilarMoviesError(throwable: Throwable) {
        hideSimilarTitles()
    }

    private fun hideSimilarTitles() {
        binding.rvMovieDetailRelated.gone()
        binding.tvSimilarTitles.gone()
    }

    private fun handleError(error: Throwable?) {
        Log.e("error", "An error occurred", error)
        ErrorDialog(this).showDialog()
    }

    private fun handleSuccess(movie: MovieDetailBO) {
        this.movie = movie
        with(binding) {
            toolbar.tvTitle.text = movie.title
            tvMovieDetailReleaseDate.text = movie.releaseDate?.toDateFormat() ?: getString(R.string.unkown_release_date)
            tvMovieDetailRating.text = movie.rating.toString()
            tvMovieDetailOverview.text = movie.overview
            ivMovieDetailFolder loadImage getString(R.string.images_base_url, movie.posterPath)
            toggleFavourite(movie.isFavourite)
        }
    }

    private fun toggleFavourite(isFav: Boolean) {
        binding.toolbar.ctvFavourite.isChecked = isFav
    }

    private fun goToMovieDetails(movie: MovieBO) = startActivity(getStartIntent(this, movie.id))

    companion object {
        private const val MOVIE_ID_EXTRA = "MOVIE_ID_EXTRA"

        fun getStartIntent(context: Context, movieId: Long) = Intent(context, MovieDetailActivity::class.java).apply {
            putExtra(MOVIE_ID_EXTRA, movieId)
        }
    }
}