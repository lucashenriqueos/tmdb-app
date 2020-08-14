package org.lucashos.tmdb.di

import android.content.Context
import dagger.Module
import dagger.Provides
import org.lucashos.tmdb.TmdbApplication
import javax.inject.Singleton

@Module
internal class ApplicationModule {
    @Provides
    @Singleton
    fun provideContext(application: TmdbApplication): Context = application.applicationContext
}