package org.lucashos.tmdb.di

import dagger.Module
import dagger.Provides
import org.lucashos.tmdb.dummy.ApplicationDummy

@Module
class ApplicationProvider {
    @Provides
    fun providesApplicationDummy(): ApplicationDummy = ApplicationDummy()
}