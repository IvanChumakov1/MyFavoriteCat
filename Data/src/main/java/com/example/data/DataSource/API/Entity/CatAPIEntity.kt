package com.example.data.DataSource.API.Entity

import com.example.domain.Entity.CatEntity

data class CatAPIEntity(
    val name: String,
    val image_link: String?, // Сделаем nullable, так как в старом API его не было
    val length: String?,      // Сделаем nullable по той же причине
    val origin: String?,      // Сделаем nullable по той же причине
    val family_friendly: Int?, // Изменено на Int, так как в новом API это число
    val shedding: Int?,        // Изменено на Int
    val general_health: Int?,   // Изменено на Int
    val playfulness: Int?,     // Изменено на Int
    val meowing: Int?,         // Изменено на Int, может отсутствовать в новом API
    val children_friendly: Int?,// Изменено на Int
    val stranger_friendly: Int?,// Изменено на Int, может отсутствовать в новом API
    val grooming: Int?,        // Изменено на Int
    val intelligence: Int?,    // Изменено на Int
    val other_pets_friendly: Int?,// Изменено на Int
    val max_weight: Double?,   // Изменено на Double
    val max_life_expectancy: Double?, // Изменено на Double
)

fun CatAPIEntity.toCatEntity(): CatEntity {
    return CatEntity(
        name = name,
        image_link = image_link ?: "", // Обработка null значений
        length = length ?: "",         // ��бработка null значений
        origin = origin ?: "",         // Обработка null значений
        family_friendly = family_friendly?.toString() ?: "", // Преобразование Int в String
        shedding = shedding?.toString() ?: "",             // Преобразование Int в String
        general_health = general_health?.toString() ?: "", // Преобразование Int в String
        playfulness = playfulness?.toString() ?: "",       // Преобразование Int в String
        meowing = meowing?.toString() ?: "",               // Преобразование Int в String
        children_friendly = children_friendly?.toString() ?: "", // Преобразование Int в String
        stranger_friendly = stranger_friendly?.toString() ?: "", // Преобразование Int в String
        grooming = grooming?.toString() ?: "",             // Преобразование Int в String
        intelligence = intelligence?.toString() ?: "",     // Преобразование Int в String
        other_pets_friendly = other_pets_friendly?.toString() ?: "", // Преобразование Int в String
        max_weight = max_weight?.toString() ?: "",         // Преобразование Double в String
        life_expectancy = max_life_expectancy?.toString() ?: "" // Преобразование Double в String, используем max_life_expectancy
    )
}