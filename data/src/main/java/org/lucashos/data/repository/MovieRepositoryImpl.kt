package org.lucashos.data.repository

import io.reactivex.Single
import org.lucashos.data.db.dao.MovieDao
import org.lucashos.data.domain.entity.MovieEntity
import org.lucashos.data.service.TmdbApiService
import org.lucashos.domain.entity.GenreBO
import org.lucashos.domain.entity.MovieBO
import org.lucashos.domain.entity.MovieDetailBO
import org.lucashos.domain.entity.MoviesListBO
import org.lucashos.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val service: TmdbApiService,
    private val dao: MovieDao
) : MovieRepository {

    override fun listTopMovies(page: Int): Single<MoviesListBO> = service.getTopMovies(page).map { it.toBO() }

    override fun searchMovies(title: String, page: Int): Single<MoviesListBO> = service.searchMovies(title, page)
        .map { it.toBO() }

    override fun getSimilarMovies(id: Long): Single<MoviesListBO> = service.getSimilarMovies(id).map { it.toBO() }

    override fun getMovieDetail(id: Long): Single<MovieDetailBO> = service.getMovie(id).map { it.toBO() }

    override fun getRandomPopularMovie(): Single<MovieBO> =
        service.getPopularMovies()
            .map { it.movies.random() }
            .map { it.toBO() }

    override fun getGenres(): Single<List<GenreBO>> =
        service.getGenres()
            .map {
                it.genres.map { genre ->
                    genre.toBO()
                }
            }

    override fun findFavourite(id: Long): Single<Int> = dao.findFavourite(id)

    override fun addFavourite(id: Long): Single<Any> = dao.insert(MovieEntity(id)).toSingleDefault(true)

    override fun removeFavourite(id: Long): Single<Int> = dao.delete(MovieEntity(id))


}