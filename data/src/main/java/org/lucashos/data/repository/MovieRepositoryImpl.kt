package org.lucashos.data.repository

import io.reactivex.Single
import org.lucashos.domain.entity.GenreBO
import org.lucashos.domain.entity.MovieBO
import org.lucashos.domain.repository.MovieRepository

class MovieRepositoryImpl : MovieRepository {
    override fun listTopMovies(): Single<List<MovieBO>> = Single.just(
        listOf(
            MovieBO(
                title = "Mocky Fight Club",
                genres = listOf(GenreBO(1, "Drama")),
                overview = "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.",
                posterPath = "/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg",
                releaseDate = "1999-10-15"
            )
        )
    )
}