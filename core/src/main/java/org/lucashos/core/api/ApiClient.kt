package org.lucashos.core.api

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class ApiClient {
    private val authToken = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYzEwNDY4NzY5MjE5ZDg5MzQzNjM0YzE0MzI4MGUxNyIsInN1YiI6IjViYzYyMmI0YzNhMzY4MmQ2MDA4NTVkNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.tWAJy69RqCBn4u8TzoDX989EcktbiXD5On7wnSjKak0"
    private val baseUrl = "https://api.themoviedb.org/3/"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(configureGson()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(createHttpClient())
        .build()

    private fun configureGson() = GsonBuilder()
        .create()

    private fun createHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer $authToken")
                .build()
            chain.proceed(chain.request())
        }
        .build()
}