package com.example.domain.UseCase

import com.example.domain.Entity.CatEntity
import com.example.domain.Repository.APICatRepository
import javax.inject.Inject

class GetCatsAPIUseCase @Inject constructor(private val catRepository: APICatRepository) {
    fun invoke(title: String, year: String?): List<CatEntity>? {
        return catRepository.getCats(title, year)
    }
}