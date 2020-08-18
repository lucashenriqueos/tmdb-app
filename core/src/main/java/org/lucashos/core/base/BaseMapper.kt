package org.lucashos.core.base

interface BaseMapper<From, To> {
    fun from(from: From): To

    fun to(to: To): From

    fun fromList(fromList: List<From>): List<To> = fromList.map { from(it) }

    fun toList(toList: List<To>): List<From> = toList.map { to(it) }
}