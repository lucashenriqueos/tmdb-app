package org.lucashos.core.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.lucashos.core.authToken
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient(baseUrl: String) {

    val httpClient: OkHttpClient = createHttpClient()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(configureGson()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(httpClient)
        .build()

    private fun configureGson() = GsonBuilder()
        .create()

    private fun createHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            chain.proceed(
                chain.request()
                    .newBuilder()
                    .addHeader("Authorization", authToken)
                    .build()
            )
        }
        .build()
}