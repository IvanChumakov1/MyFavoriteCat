package com.example.myfavoritecat.di

import com.example.data.DataSource.API.Client.ApiCatClient
import com.example.data.DataSource.API.Service.CatAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient

@Module
@InstallIn(SingletonComponent::class)
object APICatModule {
    @Provides
    fun provideAPICatService(httpClient: OkHttpClient): CatAPIService {
        return ApiCatClient(httpClient).getApiService()
    }

    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().build()
    }
}