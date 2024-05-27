package com.example.data.DataSource.API.Entity

import com.example.domain.Entity.CatEntity

data class CatAPIEntity(
    val Title: String,
    val Year: String,
    val Poster: String,
    val imdbID: String
)

fun CatAPIEntity.toCatEntity(): CatEntity {
    return CatEntity(
        imdbID = imdbID,
        Title = Title,
        Year = Year,
        Poster = Poster
    )
}