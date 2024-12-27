package com.example.domain.UseCase

import android.util.Log
import com.example.domain.Entity.CatEntity
import com.example.domain.Repository.APICatRepository
import javax.inject.Inject

class GetCatsAPIUseCase @Inject constructor(private val catRepository: APICatRepository) {
    fun invoke(name: String): List<CatEntity>? {
        //Log.d("Hello", catRepository.getCats(name, year).toString())
        return catRepository.getCats(name)
    }
}