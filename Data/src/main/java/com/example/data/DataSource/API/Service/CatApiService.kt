package com.example.data.DataSource.API.Service

import com.example.data.DataSource.API.Entity.SearchResponseAPIEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "6266644e"
interface CatAPIService {
    @GET("/")
    fun getCatList(
        @Query("s") title: String,
        @Query("y") year: String?,
        @Query("apikey") key: String? = API_KEY,
    ): Call<SearchResponseAPIEntity>

    companion object {
        const val BASE_URL: String = "https://www.omdbapi.com/"
    }
}