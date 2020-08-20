package org.lucashos.tmdb

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.lucashos.core.di.coreKoinModule
import org.lucashos.data.di.dataKoinModule

class TmdbApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TmdbApplication)
            modules(coreKoinModule, dataKoinModule)
        }
    }
}