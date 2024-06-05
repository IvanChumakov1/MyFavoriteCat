package com.example.myfavoritecat.CatApi

import retrofit2.http.GET
import retrofit2.http.Query


const val API_KEY = "fkm6S0TW3x9BCB1TfNJQCQ==OaccOgZyOVLEB3en"
interface ApiCatsService {

    @GET("cats")
    suspend fun getCatsByName(
        @Query("name") name: String,
        @Query("X-Api-Key") key: String? = API_KEY): List<ApiCats>
}