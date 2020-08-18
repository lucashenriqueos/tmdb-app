package org.lucashos.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import org.lucashos.core.api.ApiClient
import org.lucashos.data.db.AppDatabase
import org.lucashos.data.repository.MovieRepositoryImpl
import org.lucashos.domain.repository.MovieRepository

@Module
class DataModule {

    @Provides
    fun provideMovieRepository(apiClient: ApiClient, appDatabase: AppDatabase): MovieRepository = MovieRepositoryImpl(apiClient, appDatabase)

    @Provides
    fun provideMovieDb(context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "movie-db"
    ).build()
}