package com.example.data.DataSource.Database.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.Entity.CatEntity

@Entity
data class DBCatEntity(
    @PrimaryKey
    val name: String,
    val image_link: String
)

fun DBCatEntity.toCatEntity(): CatEntity {
    return CatEntity(
        name = name,
        image_link = image_link
    )
}

fun CatEntity.toDBCatEntity(): DBCatEntity {
    return DBCatEntity(
        name = name,
        image_link = image_link
    )
}