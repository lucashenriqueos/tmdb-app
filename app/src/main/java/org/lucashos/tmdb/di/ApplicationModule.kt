package org.lucashos.tmdb.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.lucashos.core.di.CoreModule
import org.lucashos.data.di.DataModule
import org.lucashos.feature.di.FeatureModule
import org.lucashos.tmdb.MainActivity

@Module
abstract class ApplicationModule {

    @ContributesAndroidInjector(modules = [DataModule::class, CoreModule::class, FeatureModule::class, ApplicationProvider::class])
    abstract fun providesMainActivity(): MainActivity


}