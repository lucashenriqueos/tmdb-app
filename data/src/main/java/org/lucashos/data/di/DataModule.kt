package org.lucashos.data.di

import dagger.Module
import dagger.Provides
import org.lucashos.data.dummy.DataDummy

@Module
class DataModule {
    @Provides
    fun provideDataDummy(): DataDummy = DataDummy()
}