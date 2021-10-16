package org.lucashos.feature.topmovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_movie_list.view.iv_item_movie_folder
import kotlinx.android.synthetic.main.item_movie_list.view.tv_item_rating
import kotlinx.android.synthetic.main.item_movie_list.view.tv_item_release_date
import kotlinx.android.synthetic.main.item_movie_list.view.tv_item_title
import org.lucashos.core.extension.loadImage
import org.lucashos.core.extension.orElse
import org.lucashos.core.extension.toDateFormat
import org.lucashos.domain.entity.MovieBO
import org.lucashos.feature.R

class TopMoviesAdapter(private val moviesList: List<MovieBO>, private val onClick: (MovieBO) -> Unit) :
    RecyclerView.Adapter<TopMoviesAdapter.TopMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMoviesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_list, parent, false)
        return TopMoviesViewHolder(view)
    }

    override fun getItemCount(): Int = moviesList.size

    override fun onBindViewHolder(holder: TopMoviesViewHolder, position: Int) {
        holder.bind(moviesList[position], onClick)
    }

    class TopMoviesViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(
            movie: MovieBO,
            onClick: (MovieBO) -> Unit
        ) {
            with(itemView) {
                iv_item_movie_folder loadImage context.getString(R.string.images_base_url, movie.posterPath)
                tv_item_title.text = movie.title
                tv_item_rating.text = movie.rating.toString()
                tv_item_release_date.text = movie.releaseDate?.toDateFormat()
                    .orElse(context.getString(R.string.unkown_release_date))
                setOnClickListener {
                    onClick(movie)
                }
            }
        }
    }
}

