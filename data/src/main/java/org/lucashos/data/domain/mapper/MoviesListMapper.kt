package org.lucashos.data.domain.mapper

import org.lucashos.core.base.BaseMapper
import org.lucashos.data.domain.response.MoviesListResponse
import org.lucashos.domain.entity.MoviesListBO

object MoviesListMapper : BaseMapper<MoviesListResponse, MoviesListBO> {
    override fun from(from: MoviesListResponse): MoviesListBO = MoviesListBO(
        page = from.page,
        totalPages = from.totalPages,
        movies = MovieMapper.fromList(from.movies)
    )

    override fun to(to: MoviesListBO): MoviesListResponse {
        TODO("Not yet implemented")
    }
}