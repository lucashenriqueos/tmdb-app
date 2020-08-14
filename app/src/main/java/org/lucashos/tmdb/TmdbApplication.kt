package org.lucashos.tmdb

import android.app.Application
import org.lucashos.tmdb.di.DaggerApplicationComponent

class TmdbApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    fun initDagger() {
        DaggerApplicationComponent.factory().create(this)
    }
}