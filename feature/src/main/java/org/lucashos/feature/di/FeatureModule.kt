package org.lucashos.feature.di

import dagger.Module
import dagger.Provides
import org.lucashos.feature.dummy.FeatureDummy

@Module
class FeatureModule {
    @Provides
    fun provideDummy() = FeatureDummy()
}