package com.example.data.DataSource.API.Entity

import com.example.domain.Entity.CatEntity

data class CatAPIEntity(
    val name: String,
    val image_link: String,
)

fun CatAPIEntity.toCatEntity(): CatEntity {
    return CatEntity(
        name = name,
        image_link = image_link
    )
}