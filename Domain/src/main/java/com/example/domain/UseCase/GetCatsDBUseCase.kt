package com.example.domain.UseCase

import com.example.domain.Entity.CatEntity
import com.example.domain.Repository.DBCatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCatsDBUseCase @Inject constructor(private val dbCatsRepository: DBCatRepository) {
    fun invoke(): Flow<List<CatEntity>> {
        return dbCatsRepository.allCats
    }
}