package org.lucashos.data.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_movies")
class MovieEntity(@PrimaryKey val id: Long)