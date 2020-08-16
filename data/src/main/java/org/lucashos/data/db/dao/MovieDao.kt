package org.lucashos.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single
import org.lucashos.data.domain.entity.MovieEntity

@Dao
interface MovieDao {
    @Query("select id from favourite_movies")
    fun findFavourite(id: Int): Single<Int>


    @Insert
    fun insert(movie: MovieEntity)

    @Delete
    fun delete(movie: MovieEntity)
}