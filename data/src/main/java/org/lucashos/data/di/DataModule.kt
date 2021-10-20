package org.lucashos.data.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import org.lucashos.core.api.ApiClient
import org.lucashos.data.db.AppDatabase
import org.lucashos.data.db.dao.MovieDao
import org.lucashos.data.repository.MovieRepositoryImpl
import org.lucashos.data.service.TmdbApiService
import org.lucashos.domain.repository.MovieRepository
import javax.inject.Singleton

@Module(includes = [DataModule.Binder::class])
class DataModule {

    @Provides
    fun provideMovieDb(context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "movie-db"
    ).build()

    @Provides
    fun provideTmdbApiService(apiClient: ApiClient): TmdbApiService =
        apiClient.retrofit.create(TmdbApiService::class.java)

    @Provides
    fun provideMovieDao(dataBase: AppDatabase): MovieDao = dataBase.movieDao()

    @Module
    abstract class Binder {
        @Binds
        @Reusable
        abstract fun bindMovieRepository(repositoryImpl: MovieRepositoryImpl): MovieRepository
    }

}