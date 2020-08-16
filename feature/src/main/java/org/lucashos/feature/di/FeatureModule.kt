package org.lucashos.feature.di

import android.content.Context
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import org.lucashos.core.di.CoreModule
import org.lucashos.domain.repository.MovieRepository
import org.lucashos.domain.usecase.ListTopMoviesUseCase
import org.lucashos.feature.topmovies.TopMoviesActivity
import org.lucashos.feature.topmovies.TopMoviesViewModel

@Module
abstract class FeatureModule {

    @ContributesAndroidInjector(modules = [ProvidingModule::class, CoreModule::class])
    abstract fun provideTopMoviesActivity(): TopMoviesActivity

    @Module
    class ProvidingModule {

        @Provides
        fun provideListTopMoviesUseCase(movieRepository: MovieRepository) =
            ListTopMoviesUseCase(movieRepository)

        @Provides
        fun providesTopMoviesViewModel(useCase: ListTopMoviesUseCase) = TopMoviesViewModel(useCase)
    }

}