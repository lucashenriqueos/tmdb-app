package org.lucashos.feature.di

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import org.lucashos.core.di.CoreModule
import org.lucashos.core.di.scope.ActivityScope
import org.lucashos.domain.repository.MovieRepository
import org.lucashos.domain.usecase.CheckFavoriteMovieUseCase
import org.lucashos.domain.usecase.GetMovieDetailUseCase
import org.lucashos.domain.usecase.GetPopularMovieUseCase
import org.lucashos.domain.usecase.GetSimilarMoviesUseCase
import org.lucashos.domain.usecase.ListTopMoviesUseCase
import org.lucashos.domain.usecase.SearchMoviesUseCase
import org.lucashos.domain.usecase.UpdateFavoriteMovieUseCase
import org.lucashos.feature.detail.MovieDetailActivity
import org.lucashos.feature.topmovies.TopMoviesActivity

@Module
abstract class FeatureModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [UseCaseModule::class])
    abstract fun provideTopMoviesActivity(): TopMoviesActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [UseCaseModule::class])
    abstract fun provideMovieDetailActivity(): MovieDetailActivity

    @Module(includes = [CoreModule::class])
    class UseCaseModule {

        @Provides
        fun provideListTopMoviesUseCase(movieRepository: MovieRepository) =
            ListTopMoviesUseCase(movieRepository)

        @Provides
        fun provideGetSimilarMoviesUseCase(movieRepository: MovieRepository) =
            GetSimilarMoviesUseCase(movieRepository)

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
        fun provideSearchMoviesUseCase(movieRepository: MovieRepository) =
            SearchMoviesUseCase(movieRepository)

        @Provides
        fun providePopularMovieUseCase(movieRepository: MovieRepository) =
            GetPopularMovieUseCase(movieRepository)
    }

}