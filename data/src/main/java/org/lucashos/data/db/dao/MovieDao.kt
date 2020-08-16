package org.lucashos.data.db.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import org.lucashos.data.domain.entity.MovieEntity

@Dao
interface MovieDao {
    @Query("select count(*) from favourite_movies where id = :movieId")
    fun findFavourite(movieId: Int): Single<Int>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movie: MovieEntity): Completable

    @Delete
    fun delete(movie: MovieEntity): Single<Int>
}