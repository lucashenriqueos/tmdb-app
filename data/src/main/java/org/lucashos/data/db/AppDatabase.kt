package org.lucashos.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import org.lucashos.data.db.dao.MovieDao
import org.lucashos.data.domain.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}