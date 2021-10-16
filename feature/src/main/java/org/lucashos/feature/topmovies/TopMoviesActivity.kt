package org.lucashos.feature.topmovies

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_top_movies.rv_movies_list
import org.lucashos.core.base.BaseActivity
import org.lucashos.core.dialog.ErrorDialog
import org.lucashos.domain.entity.MovieBO
import org.lucashos.domain.entity.MoviesListBO
import org.lucashos.feature.R
import org.lucashos.feature.detail.MovieDetailActivity
import javax.inject.Inject

class TopMoviesActivity : BaseActivity(R.layout.activity_top_movies) {

    @Inject
    lateinit var topMoviesViewModel: TopMoviesViewModel

    lateinit var scrollListener: EndlessRecyclerViewScrollListener

    var totalPages = 0

    var movies: ArrayList<MovieBO> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRecyclerView()
        initObservers()
        topMoviesViewModel.getTopMovies()
    }

    private fun initObservers() {
        topMoviesViewModel.moviesListLiveData.observe({ lifecycle }) {
            it.fold(::handleMovieError, ::handleMovieSuccess)
        }
    }

    private fun handleMovieSuccess(moviesList: MoviesListBO) {
        val startSize = movies.size
        totalPages = moviesList.totalPages
        movies.addAll(moviesList.movies)
        (rv_movies_list.adapter as TopMoviesAdapter).notifyItemRangeChanged(startSize, movies.size)
    }

    private fun setupRecyclerView() {
        with (rv_movies_list) {
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
                goToMovieDetails(it)
            }

        }
    }

    private fun handleMovieError(error: Throwable) = ErrorDialog(this).showDialog()

    private fun loadNextPage(page: Int) {
        if (page > totalPages) return
        topMoviesViewModel.getTopMovies(page + 1)
    }

    private fun goToMovieDetails(movie: MovieBO) = startActivity(MovieDetailActivity.getStartIntent(this, movie.id))
}