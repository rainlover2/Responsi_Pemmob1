package com.unsoed.norwichcityfc.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.football-data.org/v4/"

    private const val API_TOKEN = "36876c4bb3db4e7c930d0f62cf57fb64"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(API_TOKEN))
        .build()

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }
}