package org.lucashos.core.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient(baseUrl: String) {
    private val authToken =
        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYzEwNDY4NzY5MjE5ZDg5MzQzNjM0YzE0MzI4MGUxNyIsInN1YiI6IjViYzYyMmI0YzNhMzY4MmQ2MDA4NTVkNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.tWAJy69RqCBn4u8TzoDX989EcktbiXD5On7wnSjKak0"

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