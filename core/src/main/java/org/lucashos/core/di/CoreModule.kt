package org.lucashos.core.di

import dagger.Module
import dagger.Provides
import org.lucashos.core.dummy.CoreDummy

@Module
class CoreModule {

    @Provides
    fun providesCoreDummy(): CoreDummy = CoreDummy()
}