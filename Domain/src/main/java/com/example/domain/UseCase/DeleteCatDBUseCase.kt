package com.example.domain.UseCase

import com.example.domain.Entity.CatEntity
import com.example.domain.Repository.DBCatRepository
import javax.inject.Inject

class DeleteCatDBUseCase @Inject constructor(private val dbCatsRepository: DBCatRepository) {
    suspend fun invoke(cat: CatEntity) {
        dbCatsRepository.delete(cat)
    }
}