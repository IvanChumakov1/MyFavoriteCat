package com.example.data.DataSource.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.DataSource.Database.DAO.CatDao
import com.example.data.DataSource.Database.Entity.DBCatEntity

@Database(entities = [DBCatEntity::class], version = 1)
abstract class CatDatabase : RoomDatabase() {
    abstract fun catDao(): CatDao

    companion object {
        @Volatile
        private var instance: CatDatabase? = null

        fun getInstance(context: Context): CatDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): CatDatabase {
            return Room.databaseBuilder(context, CatDatabase::class.java, "Cat-db").build()
        }
    }
}