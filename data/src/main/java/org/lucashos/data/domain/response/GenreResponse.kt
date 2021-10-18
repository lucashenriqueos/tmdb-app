package org.lucashos.data.domain.response

import org.lucashos.domain.entity.GenreBO

data class GenreListResponse(val genres: List<GenreResponse>)

data class GenreResponse(val id: Long, val name: String) {
    fun toBO() = GenreBO(id, name)
}