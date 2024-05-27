package com.example.myfavoritecat.di

import android.content.Context
import com.example.data.DataSource.Database.CatDatabase
import com.example.data.DataSource.Database.DAO.CatDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DBCatModule {
    @Provides
    fun provideCatDatabase(@ApplicationContext context: Context): CatDatabase {
        return CatDatabase.getInstance(context)
    }

    @Provides
    fun provideCatDao(catDatabase: CatDatabase): CatDao {
        return catDatabase.catDao()
    }
}