package com.example.apprestaurantesv4

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface PasoCAPIService {
    @GET
    suspend fun getAllRestaurants(@Url url: String): Response<PasoERestaurantResponse>
}