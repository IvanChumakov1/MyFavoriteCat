package com.example.data.DataSource.Database.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.data.DataSource.Database.Entity.DBCatEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CatDao {
    @Query("SELECT * FROM DBCatEntity")
    fun getAllCats(): Flow<List<DBCatEntity>>

    @Insert
    suspend fun insert(cat: DBCatEntity)

    @Delete
    suspend fun delete(cat: DBCatEntity)

    @Update
    suspend fun update(cat: DBCatEntity)

}