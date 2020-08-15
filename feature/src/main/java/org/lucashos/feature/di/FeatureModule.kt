package org.lucashos.feature.di

import dagger.Module
import dagger.Provides
import org.lucashos.domain.repository.MovieRepository
import org.lucashos.domain.usecase.ListTopMoviesUseCase
import org.lucashos.feature.dummy.FeatureDummy

@Module
class FeatureModule {
    @Provides
    fun provideDummy() = FeatureDummy()

    @Provides
    fun provideListTopMoviesUseCase(movieRepository: MovieRepository) = ListTopMoviesUseCase(movieRepository)
}