package com.example.domain.Repository

import com.example.domain.Entity.CatEntity

interface APICatRepository {
    fun getCats(name: String): List<CatEntity>?
}