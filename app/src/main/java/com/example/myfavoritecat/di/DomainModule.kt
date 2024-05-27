package com.example.myfavoritecat.di

import com.example.data.DataSource.API.Repository.CatAPIRepositoryImpl
import com.example.data.DataSource.Database.DBCatRepositoryImpl
import com.example.domain.Repository.APICatRepository
import com.example.domain.Repository.DBCatRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {
    @Binds
    fun provideApiRepositury(repository: CatAPIRepositoryImpl): APICatRepository

    @Binds
    fun provideDBRepository(repository: DBCatRepositoryImpl): DBCatRepository
}