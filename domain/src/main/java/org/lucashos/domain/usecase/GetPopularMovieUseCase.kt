package org.lucashos.domain.usecase

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import org.lucashos.core.base.BaseUseCase
import org.lucashos.domain.entity.GenreBO
import org.lucashos.domain.entity.MovieBO
import org.lucashos.domain.repository.MovieRepository

class GetPopularMovieUseCase(
    private val movieRepository: MovieRepository
) : BaseUseCase<MovieBO, Unit> {

    override fun execute(params: Unit): Single<MovieBO> {
        return Single.zip(movieRepository.getRandomPopularMovie(), movieRepository.getGenres(), BiFunction<MovieBO,
                List<GenreBO>, MovieBO> { movie, genres ->
            val movieGenres = genres.filter { genre -> movie.genresIds.contains(genre.id) }
            movie.genresList.addAll(movieGenres)

            return@BiFunction movie
        })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}