package org.lucashos.data.domain.mapper

import org.lucashos.core.base.BaseMapper
import org.lucashos.data.domain.response.GenreResponse
import org.lucashos.domain.entity.GenreBO
import org.lucashos.domain.entity.MovieBO

object GenreMapper : BaseMapper<GenreResponse, GenreBO> {
    override fun from(from: GenreResponse): GenreBO = GenreBO(from.id, from.name)

    override fun to(to: GenreBO): GenreResponse {
        TODO("Not yet implemented")
    }

}