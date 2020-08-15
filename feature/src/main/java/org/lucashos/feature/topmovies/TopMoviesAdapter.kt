package org.lucashos.feature.topmovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_movie_list.view.*
import org.lucashos.domain.entity.MovieBO
import org.lucashos.feature.R

class TopMoviesAdapter(private val moviesList: List<MovieBO>) :
    RecyclerView.Adapter<TopMoviesAdapter.TopMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_movie_list, parent, false)
        return TopMoviesViewHolder(view)
    }

    override fun getItemCount(): Int = moviesList.size

    override fun onBindViewHolder(holder: TopMoviesViewHolder, position: Int) {
        holder.bind(moviesList[position])
    }

    class TopMoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: MovieBO) {
            itemView.tv_item_title.text = movie.title
            itemView.tv_item_release_date.text = movie.releaseDate
            itemView.tv_item_rating.text = movie.rating.toString()
        }
    }
}