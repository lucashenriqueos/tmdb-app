package org.lucashos.feature.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_related_movies.view.iv_related_movie_thumb
import kotlinx.android.synthetic.main.item_related_movies.view.tv_related_movie_title
import org.lucashos.core.extension.loadImage
import org.lucashos.domain.entity.MovieBO
import org.lucashos.feature.R

class RelatedMoviesAdapter(private val moviesList: List<MovieBO>, private val onClick: (MovieBO) -> Unit) :
    RecyclerView.Adapter<RelatedMoviesAdapter.RelatedMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelatedMoviesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_related_movies, parent, false)
        return RelatedMoviesViewHolder(view)
    }

    override fun getItemCount(): Int = moviesList.size

    override fun onBindViewHolder(holder: RelatedMoviesViewHolder, position: Int) {
        holder.bind(moviesList[position], onClick)
    }

    class RelatedMoviesViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(
            movie: MovieBO,
            onClick: (MovieBO) -> Unit
        ) {
            with(itemView) {
                iv_related_movie_thumb loadImage context.getString(R.string.images_base_url, movie.posterPath)
                tv_related_movie_title.text = movie.title
                setOnClickListener {
                    onClick(movie)
                }
            }
        }
    }
}
