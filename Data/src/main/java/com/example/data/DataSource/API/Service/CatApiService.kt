package com.example.data.DataSource.API.Service

import com.example.data.DataSource.API.Entity.SearchResponseAPIEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "fkm6S0TW3x9BCB1TfNJQCQ==OaccOgZyOVLEB3en"
interface CatAPIService {
    @GET("/v1/cats/")
    fun getCatList(
        @Query("name") name: String,
        @Query("X-Api-Key") key: String? = API_KEY,
    ): Call<SearchResponseAPIEntity>

    companion object {
        const val BASE_URL: String = "https://api.api-ninjas.com"
    }
}