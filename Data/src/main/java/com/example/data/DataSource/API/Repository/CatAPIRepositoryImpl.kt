package com.example.data.DataSource.API.Repository

import com.example.data.DataSource.API.Entity.toCatEntity
import com.example.data.DataSource.API.Service.CatAPIService
import com.example.domain.Entity.CatEntity
import com.example.domain.Repository.APICatRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatAPIRepositoryImpl @Inject constructor(private val api: CatAPIService) :
    APICatRepository {

    override fun getCats(
        title: String,
        year: String?
    ): List<CatEntity>? {
        return api
            .getCatList(title = title, year = year)
            .execute()
            .body()
            ?.Search
            ?.toList()
            ?.map { it.toCatEntity() }
    }
}