package org.lucashos.feature.topmovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_movie_list.view.*
import org.lucashos.core.extension.toDateFormat
import org.lucashos.domain.entity.MovieBO
import org.lucashos.feature.R

class TopMoviesAdapter(private val moviesList: List<MovieBO>) :
    RecyclerView.Adapter<TopMoviesAdapter.TopMoviesViewHolder>() {

    val onClick: PublishSubject<MovieBO> = PublishSubject.create()

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
            onClick: PublishSubject<MovieBO>
        ) {
            movie.posterPath?.let {
                Glide.with(itemView)
                    .load("${itemView.context.getString(R.string.images_base_url)}${it.substring(1)}")
                    .into(itemView.iv_item_movie_folder)
            }
            itemView.tv_item_title.text = movie.title
            itemView.tv_item_release_date.text = movie.releaseDate?.toDateFormat() ?: itemView.context.getString(R.string.unkown_release_date)
            itemView.tv_item_rating.text = movie.rating.toString()
            itemView.setOnClickListener {
                onClick.onNext(movie)
            }
        }
    }
}