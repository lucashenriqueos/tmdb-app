package org.lucashos.tmdb.di

import android.content.Context
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import org.lucashos.core.di.CoreModule
import org.lucashos.data.di.DataModule
import org.lucashos.feature.di.FeatureModule
import org.lucashos.tmdb.TmdbApplication
import javax.inject.Singleton

@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationComponent.ApplicationProviderModule::class,
        DataModule::class,
        CoreModule::class,
        FeatureModule::class
    ]
)
@Singleton
interface ApplicationComponent : AndroidInjector<TmdbApplication> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<TmdbApplication>

    @Module
    class ApplicationProviderModule {
        @Provides
        @Singleton
        fun provideContext(application: TmdbApplication): Context = application.applicationContext
    }
}