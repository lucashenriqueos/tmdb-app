package org.lucashos.feature.topmovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.lucashos.core.extension.loadImage
import org.lucashos.core.extension.orElse
import org.lucashos.core.extension.toDateFormat
import org.lucashos.domain.entity.MovieBO
import org.lucashos.feature.R
import org.lucashos.feature.databinding.ItemTopMoviesBinding

class TopMoviesAdapter(private val moviesList: List<MovieBO>, private val onClick: (MovieBO) -> Unit) :
    RecyclerView.Adapter<TopMoviesAdapter.TopMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMoviesViewHolder {
        val binding = ItemTopMoviesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false

        )
        return TopMoviesViewHolder(binding)
    }

    override fun getItemCount(): Int = moviesList.size

    override fun onBindViewHolder(holder: TopMoviesViewHolder, position: Int) {
        holder.bind(moviesList[position], onClick)
    }

    class TopMoviesViewHolder(private val binding: ItemTopMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            movie: MovieBO,
            onClick: (MovieBO) -> Unit
        ) {
            with(binding) {
                ivItemMovieFolder loadImage root.context.getString(R.string.images_base_url, movie.posterPath)
                tvItemTitle.text = movie.title
                tvItemRating.text = movie.rating.toString()
                tvItemReleaseDate.text = movie.releaseDate?.toDateFormat()
                    .orElse(root.context.getString(R.string.unkown_release_date))
                binding.root.setOnClickListener {
                    onClick(movie)
                }
            }
        }
    }
}


