package com.unsoed.norwichcityfc.network

import com.unsoed.norwichcityfc.data.TeamResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    // Endpoint untuk mengambil detail tim
    @GET("teams/{id}")
    suspend fun getTeamDetails(@Path("id") teamId: Int): TeamResponse
}