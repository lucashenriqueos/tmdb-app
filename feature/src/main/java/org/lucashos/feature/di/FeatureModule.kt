package org.lucashos.feature.di

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import org.lucashos.core.di.CoreModule
import org.lucashos.domain.repository.MovieRepository
import org.lucashos.domain.usecase.CheckFavoriteMovieUseCase
import org.lucashos.domain.usecase.GetMovieDetailUseCase
import org.lucashos.domain.usecase.ListTopMoviesUseCase
import org.lucashos.domain.usecase.UpdateFavoriteMovieUseCase
import org.lucashos.feature.detail.MovieDetailActivity
import org.lucashos.feature.detail.MovieDetailViewModel
import org.lucashos.feature.topmovies.TopMoviesActivity
import org.lucashos.feature.topmovies.TopMoviesViewModel

@Module
abstract class FeatureModule {

    @ContributesAndroidInjector(modules = [ProvidingModule::class, CoreModule::class])
    abstract fun provideTopMoviesActivity(): TopMoviesActivity

    @ContributesAndroidInjector(modules = [ProvidingModule::class, CoreModule::class])
    abstract fun provideMovieDetailActivity(): MovieDetailActivity

    @Module
    class ProvidingModule {

        @Provides
        fun provideListTopMoviesUseCase(movieRepository: MovieRepository) =
            ListTopMoviesUseCase(movieRepository)

        @Provides
        fun provideGetMovieDetailUseCase(
            movieRepository: MovieRepository,
            checkFavouriteUseCase: CheckFavoriteMovieUseCase
        ) =
            GetMovieDetailUseCase(movieRepository, checkFavouriteUseCase)

        @Provides
        fun providCheckFavoriteMovieUseCase(movieRepository: MovieRepository) =
            CheckFavoriteMovieUseCase(movieRepository)

        @Provides
        fun provideUpdateFavouriteUseCase(movieRepository: MovieRepository) =
            UpdateFavoriteMovieUseCase(movieRepository)

        @Provides
        fun providesTopMoviesViewModel(useCase: ListTopMoviesUseCase) = TopMoviesViewModel(useCase)

        @Provides
        fun providesMovieDetailViewModel(
            getMovieDetailUseCase: GetMovieDetailUseCase,
            updateFavouriteUseCase: UpdateFavoriteMovieUseCase
        ) = MovieDetailViewModel(getMovieDetailUseCase, updateFavouriteUseCase)
    }

}