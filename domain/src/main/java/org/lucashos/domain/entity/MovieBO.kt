package org.lucashos.domain.entity

class MovieBO(
    val id: Long,
    val title: String?,
    val releaseDate: String?,
    val posterPath: String?,
    val rating: Double?,
    val genresIds: List<Long> = listOf(),
    val genresList: MutableList<GenreBO> = mutableListOf()
) {
    fun getGenresAsString(): String {
        return genresList.toString().replace(Regex("\\[|]"), "")
    }
}
