package org.lucashos.core.base

import io.reactivex.Single

interface BaseUseCase<T, in Param> {
    fun execute(params: Param): Single<T>
}