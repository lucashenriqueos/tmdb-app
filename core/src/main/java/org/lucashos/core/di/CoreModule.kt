package org.lucashos.core.di

import android.content.Context
import android.net.Uri
import com.squareup.picasso.Cache
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import org.lucashos.core.R
import org.lucashos.core.api.ApiClient
import java.lang.Exception

@Module
class CoreModule {

    @Provides
    fun providesApiClient(context: Context): ApiClient =
        ApiClient(context.getString(R.string.base_url))

    @Provides
    fun providesPicasso(context: Context, apiClient: ApiClient): Picasso = Picasso.Builder(context)
        .downloader(OkHttp3Downloader(apiClient.httpClient))
        .loggingEnabled(true)
        .build()
}