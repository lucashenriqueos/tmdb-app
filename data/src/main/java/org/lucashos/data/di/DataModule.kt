package org.lucashos.data.di

import dagger.Module
import dagger.Provides
import org.lucashos.data.dummy.DataDummy
import org.lucashos.data.repository.MovieRepositoryImpl
import org.lucashos.domain.repository.MovieRepository

@Module
class DataModule {
    @Provides
    fun provideDataDummy(): DataDummy = DataDummy()

    @Provides
    fun provideMovieRepository(): MovieRepository = MovieRepositoryImpl()
}