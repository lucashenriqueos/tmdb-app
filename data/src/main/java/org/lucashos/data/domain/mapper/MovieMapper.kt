package org.lucashos.data.domain.mapper

import org.lucashos.core.base.BaseMapper
import org.lucashos.data.domain.response.MovieResponse
import org.lucashos.domain.entity.MovieBO

object MovieMapper : BaseMapper<MovieResponse, MovieBO> {
    override fun from(from: MovieResponse): MovieBO = MovieBO(
        id = from.id,
        title = from.title,
        rating = from.rating,
        releaseDate = from.releaseDate,
        posterPath = from.posterPath
    )

    override fun to(to: MovieBO): MovieResponse {
        TODO("Not yet implemented")
    }
}