package com.unsoed.norwichcityfc.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val apiToken: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("X-Auth-Token", "36876c4bb3db4e7c930d0f62cf57fb64") // Tambahkan header token di sini
            .build()
        return chain.proceed(request)
    }
}