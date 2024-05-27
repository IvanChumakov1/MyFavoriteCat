package com.example.data.DataSource.API.Client

import com.example.data.DataSource.API.Service.CatAPIService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiCatClient @Inject constructor(private val httpClient: OkHttpClient) {
    private lateinit var apiService: CatAPIService

    fun getApiService(): CatAPIService {
        if (!::apiService.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(CatAPIService.BASE_URL)
                .client(httpClient)
                .addConverterFactory(
                    GsonConverterFactory.create()
                ).build()

            apiService = retrofit.create(CatAPIService::class.java)
        }

        return apiService
    }
}