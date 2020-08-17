package org.lucashos.feature.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_related_movies.view.*
import org.lucashos.domain.entity.MovieBO
import org.lucashos.feature.R

class RelatedMoviesAdapter(private val moviesList: List<MovieBO>, private val picasso: Picasso) :
    RecyclerView.Adapter<RelatedMoviesAdapter.RelatedMoviesViewHolder>() {

    val onClick: PublishSubject<MovieBO> = PublishSubject.create()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelatedMoviesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_related_movies, parent, false)
        return RelatedMoviesViewHolder(view, picasso)
    }

    override fun getItemCount(): Int = moviesList.size

    override fun onBindViewHolder(holder: RelatedMoviesViewHolder, position: Int) {
        holder.bind(moviesList[position], onClick)
    }

    class RelatedMoviesViewHolder(itemView: View, private val picasso: Picasso) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(
            movie: MovieBO,
            onClick: PublishSubject<MovieBO>
        ) {
            movie.posterPath?.let {
                picasso
                    .load("${itemView.context.getString(R.string.images_base_url)}${it.substring(1)}")
                    .fit()
                    .centerCrop()
                    .into(itemView.iv_related_movie_thumb)
            }
            itemView.tv_related_movie_title.text = movie.title
            itemView.setOnClickListener {
                onClick.onNext(movie)
            }
        }
    }
}