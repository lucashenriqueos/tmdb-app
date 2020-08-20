package org.lucashos.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import org.lucashos.core.R
import org.lucashos.core.api.ApiClient

@Module
class CoreModule {

    @Provides
    fun providesApiClient(context: Context): ApiClient =
        ApiClient(context.getString(R.string.base_url))
}