package org.lucashos.core.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    val apiKey = "bc10468769219d89343634c143280e17"
    val baseUrl = "https://api.themoviedb.org/3/"

    fun getRetrofit() = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(configureGson()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(createHttpClient())
        .build()

    private fun configureGson() = GsonBuilder()
        .create()

    private fun createHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            chain.proceed(chain.request())
        }
        .build()
}