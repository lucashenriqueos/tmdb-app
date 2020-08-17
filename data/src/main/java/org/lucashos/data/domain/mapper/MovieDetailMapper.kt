package org.lucashos.data.domain.mapper

import org.lucashos.core.base.BaseMapper
import org.lucashos.data.domain.response.MovieDetailResponse
import org.lucashos.domain.entity.MovieDetailBO

object MovieDetailMapper : BaseMapper<MovieDetailResponse, MovieDetailBO> {
    override fun from(from: MovieDetailResponse): MovieDetailBO = MovieDetailBO(
        id = from.id,
        title = from.title,
        rating = from.rating,
        releaseDate = from.releaseDate,
        posterPath = from.posterPath,
        overview = from.overview
    )

    override fun to(to: MovieDetailBO): MovieDetailResponse {
        TODO("Not yet implemented")
    }
}