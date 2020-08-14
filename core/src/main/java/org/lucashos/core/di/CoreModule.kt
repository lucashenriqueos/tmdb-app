package org.lucashos.core.di

import dagger.Module
import dagger.Provides

@Module
interface CoreModule {

    @Provides
    fun providesApiClient() {

    }
}