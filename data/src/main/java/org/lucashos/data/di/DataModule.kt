package org.lucashos.data.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.lucashos.core.dataBaseName
import org.lucashos.data.db.AppDatabase
import org.lucashos.data.repository.MovieRepositoryImpl
import org.lucashos.domain.repository.MovieRepository

val dataKoinModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, dataBaseName
        ).build()
    }
    single {
        get<AppDatabase>().movieDao()
    }
}