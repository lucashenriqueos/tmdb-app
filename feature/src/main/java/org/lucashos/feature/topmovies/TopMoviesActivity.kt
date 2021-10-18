package org.lucashos.feature.topmovies

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_top_movies.iv_highlight_movie_folder
import kotlinx.android.synthetic.main.activity_top_movies.rv_movies_list
import kotlinx.android.synthetic.main.activity_top_movies.tv_highlight_genres
import kotlinx.android.synthetic.main.layout_toolbar.tv_toolbar_title
import org.lucashos.core.base.BaseActivity
import org.lucashos.core.extension.loadImage
import org.lucashos.domain.entity.MovieBO
import org.lucashos.domain.entity.MoviesListBO
import org.lucashos.feature.R
import org.lucashos.feature.detail.MovieDetailActivity
import javax.inject.Inject

class TopMoviesActivity : BaseActivity(R.layout.activity_top_movies) {

    @Inject
    lateinit var viewModel: TopMoviesViewModel

    lateinit var scrollListener: EndlessRecyclerViewScrollListener

    var totalPages = 0

    var movies: ArrayList<MovieBO> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRecyclerView()
        initObservers()
        viewModel.getTopMovies()
        viewModel.getPopularMovie()
    }

    private fun initObservers() {
        viewModel.moviesListLiveData.observe({ lifecycle }) {
            it.fold(::handleError, ::handleMoviesListSuccess)
        }
        viewModel.popularMovieLiveData.observe({ lifecycle }) {
            when (it) {
                is PopularMovieState.HighlightMovie -> handleHighlightMoviesSuccess(it)
                is PopularMovieState.Error -> handleError(it.error)
            }
        }
    }

    private fun handleHighlightMoviesSuccess(highlight: PopularMovieState.HighlightMovie) {
        with(highlight.movie) {
            tv_highlight_genres.text = this.getGenresAsString()
            iv_highlight_movie_folder loadImage getString(R.string.images_base_url, this.posterPath)
            iv_highlight_movie_folder.setOnClickListener {
                goToMovieDetails(this.id)
            }
        }
    }

    private fun handleMoviesListSuccess(moviesList: MoviesListBO) {
        val startSize = movies.size
        totalPages = moviesList.totalPages
        movies.addAll(moviesList.movies)
        (rv_movies_list.adapter as TopMoviesAdapter).notifyItemRangeChanged(startSize, movies.size)
    }

    private fun setupRecyclerView() {
        with(rv_movies_list) {
            val linearLayoutManager = LinearLayoutManager(this@TopMoviesActivity, RecyclerView.HORIZONTAL, false)

            layoutManager = linearLayoutManager

            scrollListener = object :
                EndlessRecyclerViewScrollListener(linearLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    loadNextPage(page)
                }
            }
            addOnScrollListener(scrollListener)

            adapter = TopMoviesAdapter(movies) {
                goToMovieDetails(it.id)
            }
        }
    }

    private fun handleError(error: Throwable) {
        Log.e("error", "An error occurred", error)
        showError()
    }

    private fun loadNextPage(page: Int) {
        if (page > totalPages) return
        viewModel.getTopMovies(page + 1)
    }

    private fun goToMovieDetails(id: Long) = startActivity(MovieDetailActivity.getStartIntent(this, id))
}