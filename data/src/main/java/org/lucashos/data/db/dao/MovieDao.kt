package org.lucashos.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import org.lucashos.data.domain.entity.MovieEntity

@Dao
interface MovieDao {
    @Query("select count(*) from favourite_movies where id = :movieId")
    fun findFavourite(movieId: Long): Single<Int>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movie: MovieEntity): Completable

    @Delete
    fun delete(movie: MovieEntity): Single<Int>
}