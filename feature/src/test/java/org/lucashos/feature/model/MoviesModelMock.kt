package org.lucashos.feature.model

import org.lucashos.domain.entity.GenreBO
import org.lucashos.domain.entity.MovieBO
import org.lucashos.domain.entity.MovieDetailBO
import org.lucashos.domain.entity.TopRatedMoviesBO

fun createTopRatedMoviesMock(): TopRatedMoviesBO {
    return TopRatedMoviesBO(
        page = 1,
        totalPages = 1,
        movies = arrayListOf(
            MovieBO(
                id = 1,
                title = "Mocky",
                releaseDate = "2019-10-10",
                genres = arrayListOf(1),
                overview = "This is a mocky film",
                posterPath = "/mocky.jpg",
                rating = 8.0
            )
        )
    )
}

fun createMovieDetailMock(): MovieDetailBO {
    return MovieDetailBO(
                id = 1,
                title = "Mocky",
                releaseDate = "2019-10-10",
                genres = arrayListOf(createGenreBOMock()),
                overview = "This is a mocky film",
                posterPath = "/mocky.jpg",
                rating = 8.0
            )

}

fun createGenreBOMock(): GenreBO {
    return GenreBO(
                id = 1,
                genre = "drama"
            )

}