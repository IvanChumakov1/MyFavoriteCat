package com.example.domain.Repository

import com.example.domain.Entity.CatEntity
import kotlinx.coroutines.flow.Flow

interface DBCatRepository {
    val allCats: Flow<List<CatEntity>>
    suspend fun insert(cat: CatEntity)
    suspend fun delete(cat: CatEntity)
}