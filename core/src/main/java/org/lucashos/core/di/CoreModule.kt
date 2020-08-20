package org.lucashos.core.di

import org.koin.dsl.module
import org.lucashos.core.api.ApiClient
import org.lucashos.core.baseUrl

val coreKoinModule = module {
    single {
        ApiClient(baseUrl)
    }
}