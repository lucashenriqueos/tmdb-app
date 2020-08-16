package org.lucashos.data.domain.mapper

import org.lucashos.core.base.BaseMapper
import org.lucashos.data.domain.response.GenreResponse
import org.lucashos.data.domain.response.TopRatedMoviesResponse
import org.lucashos.domain.entity.GenreBO
import org.lucashos.domain.entity.MovieBO
import org.lucashos.domain.entity.TopRatedMoviesBO

object TopRatedMoviesMapper : BaseMapper<TopRatedMoviesResponse, TopRatedMoviesBO> {
    override fun from(from: TopRatedMoviesResponse): TopRatedMoviesBO = TopRatedMoviesBO(
        page = from.page,
        totalPages = from.totalPages,
        movies = MovieMapper.fromList(from.movies)
    )

    override fun to(to: TopRatedMoviesBO): TopRatedMoviesResponse {
        TODO("Not yet implemented")
    }
}