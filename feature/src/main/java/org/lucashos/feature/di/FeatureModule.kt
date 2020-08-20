package org.lucashos.feature.di

import org.koin.dsl.module
import org.lucashos.domain.usecase.*
import org.lucashos.feature.detail.MovieDetailViewModel
import org.lucashos.feature.topmovies.TopMoviesViewModel

val featureKoinModule = module {
    single { ListTopMoviesUseCase(get()) }

    single { GetSimilarMoviesUseCase(get()) }

    single { CheckFavoriteMovieUseCase(get()) }

    single { UpdateFavoriteMovieUseCase(get()) }

    single { SearchMoviesUseCase(get()) }

    single { GetMovieDetailUseCase(get(), get()) }

    factory { TopMoviesViewModel(get(), get()) }

    factory {
        MovieDetailViewModel(
            get(),
            get(),
            get()
        )
    }
}