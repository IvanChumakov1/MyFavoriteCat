package com.example.data.DataSource.Database.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.Entity.CatEntity

@Entity
data class DBCatEntity(
    @PrimaryKey
    val imdbID: String,
    val Title: String,
    val Year: String,
    val Poster: String
)

fun DBCatEntity.toCatEntity(): CatEntity {
    return CatEntity(
        imdbID = imdbID,
        Title = Title,
        Year = Year,
        Poster = Poster
    )
}

fun CatEntity.toDBCatEntity(): DBCatEntity {
    return DBCatEntity(
        imdbID = imdbID,
        Title = Title,
        Year = Year,
        Poster = Poster
    )
}