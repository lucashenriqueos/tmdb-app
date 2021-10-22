package org.lucashos.feature.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.lucashos.core.extension.loadImage
import org.lucashos.domain.entity.MovieBO
import org.lucashos.feature.R
import org.lucashos.feature.databinding.ItemRelatedMoviesBinding

class RelatedMoviesAdapter(private val moviesList: List<MovieBO>, private val onClick: (MovieBO) -> Unit) :
    RecyclerView.Adapter<RelatedMoviesAdapter.RelatedMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelatedMoviesViewHolder {
        val binding = ItemRelatedMoviesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RelatedMoviesViewHolder(binding)
    }

    override fun getItemCount(): Int = moviesList.size

    override fun onBindViewHolder(holder: RelatedMoviesViewHolder, position: Int) {
        holder.bind(moviesList[position], onClick)
    }

    class RelatedMoviesViewHolder(private val binding: ItemRelatedMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            movie: MovieBO,
            onClick: (MovieBO) -> Unit
        ) {
            with(binding) {
                ivRelatedMovieThumb loadImage root.context.getString(R.string.images_base_url, movie.posterPath)
                tvRelatedMovieTitle.text = movie.title
                root.setOnClickListener {
                    onClick(movie)
                }
            }
        }
    }
}
