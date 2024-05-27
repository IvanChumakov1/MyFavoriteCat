package com.example.domain.Repository

import com.example.domain.Entity.CatEntity

interface APICatRepository {
    fun getCats(title: String, year: String?): List<CatEntity>?
}