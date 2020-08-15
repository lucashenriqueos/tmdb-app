package org.lucashos.domain.usecase

import io.reactivex.Single

interface BaseUseCase<T, in Param> {
    fun execute(params: Param): Single<T>
}