package org.lucashos.tmdb.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import org.lucashos.tmdb.TmdbApplication
import javax.inject.Singleton

@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class
    ]
)
@Singleton
interface ApplicationComponent : AndroidInjector<TmdbApplication> {
    @Component.Factory
    interface Factory : AndroidInjector.Factory<TmdbApplication>
}