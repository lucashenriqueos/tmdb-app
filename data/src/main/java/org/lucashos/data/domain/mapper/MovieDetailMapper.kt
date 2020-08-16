package org.lucashos.data.domain.mapper

import org.lucashos.core.base.BaseMapper
import org.lucashos.data.domain.response.MovieDetailResponse
import org.lucashos.data.domain.response.MovieResponse
import org.lucashos.domain.entity.MovieBO
import org.lucashos.domain.entity.MovieDetailBO

object MovieDetailMapper : BaseMapper<MovieDetailResponse, MovieDetailBO> {
    override fun from(from: MovieDetailResponse): MovieDetailBO = MovieDetailBO(
        title = from.title,
        rating = from.rating,
        releaseDate = from.releaseDate,
        posterPath = from.posterPath,
        overview = from.overview,
        genres = GenreMapper.fromList(from.genres)
    )

    override fun to(to: MovieDetailBO): MovieDetailResponse {
        TODO("Not yet implemented")
    }
}