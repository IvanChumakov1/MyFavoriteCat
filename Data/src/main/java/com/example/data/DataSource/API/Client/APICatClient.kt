package com.example.data.DataSource.API.Client

import android.util.Log
import com.example.data.DataSource.API.Entity.toCatEntity
import com.example.data.DataSource.API.Service.CatAPIService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiCatClient @Inject constructor(private var httpClient: OkHttpClient) {
    private lateinit var apiService: CatAPIService

    fun getApiService(): CatAPIService {
        if (!::apiService.isInitialized) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            httpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
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