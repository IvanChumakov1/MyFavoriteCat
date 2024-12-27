package com.example.data.DataSource.Database.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.Entity.CatEntity

@Entity
data class DBCatEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val image_link: String,
    val length: String,
    val origin: String,
    val family_friendly: String,
    val shedding: String,
    val general_health: String,
    val playfulness: String,
    val meowing: String,
    val children_friendly: String,
    val stranger_friendly: String,
    val grooming: String,
    val intelligence: String,
    val other_pets_friendly: String,
    val max_weight: String,
    val life_expectancy: String
)

fun DBCatEntity.toCatEntity(): CatEntity {
    return CatEntity(
        id = id,
        name = name,
        image_link = image_link,
        length = length,
        origin = origin,
        family_friendly = family_friendly,
        shedding = shedding,
        general_health = general_health,
        playfulness = playfulness,
        meowing = meowing,
        children_friendly = children_friendly,
        stranger_friendly = stranger_friendly,
        grooming = grooming,
        intelligence = intelligence,
        other_pets_friendly = other_pets_friendly,
        max_weight = max_weight,
        life_expectancy = life_expectancy
    )
}

fun CatEntity.toDBCatEntity(): DBCatEntity {
    return DBCatEntity(
        id = id,
        name = name,
        image_link = image_link,
        length = length,
        origin = origin,
        family_friendly = family_friendly,
        shedding = shedding,
        general_health = general_health,
        playfulness = playfulness,
        meowing = meowing,
        children_friendly = children_friendly,
        stranger_friendly = stranger_friendly,
        grooming = grooming,
        intelligence = intelligence,
        other_pets_friendly = other_pets_friendly,
        max_weight = max_weight,
        life_expectancy = life_expectancy
    )
}