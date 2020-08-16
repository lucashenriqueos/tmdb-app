package org.lucashos.feature.topmovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.rv_movie_list.view.*
import org.lucashos.domain.entity.MovieBO
import org.lucashos.feature.R

class TopMoviesAdapter(private val moviesList: List<MovieBO>, private val picasso: Picasso) :
    RecyclerView.Adapter<TopMoviesAdapter.TopMoviesViewHolder>() {

    val onClick: PublishSubject<MovieBO> = PublishSubject.create()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMoviesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_movie_list, parent, false)
        return TopMoviesViewHolder(view, picasso)
    }

    override fun getItemCount(): Int = moviesList.size

    override fun onBindViewHolder(holder: TopMoviesViewHolder, position: Int) {
        holder.bind(moviesList[position], onClick)
    }

    class TopMoviesViewHolder(itemView: View, private val picasso: Picasso) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(
            movie: MovieBO,
            onClick: PublishSubject<MovieBO>
        ) {
            picasso
                .load("${itemView.context.getString(R.string.images_base_url)}${movie.posterPath.substring(1)}")
                .fit()
                .centerCrop()
                .into(itemView.iv_item_movie_folder)
            itemView.tv_item_title.text = movie.title
            itemView.tv_item_release_date.text = movie.releaseDate
            itemView.tv_item_rating.text = movie.rating.toString()
            itemView.setOnClickListener {
                onClick.onNext(movie)
            }
        }
    }
}