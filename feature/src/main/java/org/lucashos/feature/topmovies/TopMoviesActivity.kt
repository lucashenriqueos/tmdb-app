package org.lucashos.feature.topmovies

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_top_movies.*
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

    @Inject
    lateinit var picasso: Picasso

    lateinit var scrollListener: EndlessRecyclerViewScrollListener

    var searchTitle = ""

    var movies: ArrayList<MovieBO> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRecyclerView()
        initObservers()
        topMoviesViewModel.getTopMovies()
    }

    private fun initObservers() {
        topMoviesViewModel.moviesLIstLiveData.observe({ lifecycle }) {
            it.fold(::handleMovieError, ::handleMovieSuccess)
        }
    }

    private fun handleMovieSuccess(moviesList: MoviesListBO) {
        movies.addAll(moviesList.movies)
        (rv_movies_list.adapter as TopMoviesAdapter).notifyDataSetChanged()
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        rv_movies_list.layoutManager = layoutManager
         scrollListener = object :
            EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                loadNextPage(page)
            }
        }
        rv_movies_list.addOnScrollListener(scrollListener)
        val adapter = TopMoviesAdapter(
            movies,
            picasso
        )
        rv_movies_list.adapter = adapter
        adapter.onClick.subscribe(::loadMovieDetails)
    }

    private fun handleMovieError(error: Throwable) = ErrorDialog(this).showDialog()

    private fun loadNextPage(page: Int) {
        if (searchTitle.isEmpty())
            topMoviesViewModel.getTopMovies(page + 1)
        else
            performSearch(searchTitle, page + 1)
    }

    fun loadMovieDetails(movie: MovieBO) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.MOVIE_ID_EXTRA, movie.id)
        startActivity(intent)
    }
}