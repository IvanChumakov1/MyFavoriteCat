package com.example.data.DataSource.Database


import com.example.data.DataSource.Database.DAO.CatDao
import com.example.data.DataSource.Database.Entity.toCatEntity
import com.example.data.DataSource.Database.Entity.toDBCatEntity
import com.example.domain.Entity.CatEntity
import com.example.domain.Repository.DBCatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DBCatRepositoryImpl @Inject constructor(private val catDao: CatDao) :
    DBCatRepository {

    override val allCats: Flow<List<CatEntity>> =
        catDao.getAllCats().map { cat -> cat.map { it.toCatEntity() } }

    override suspend fun insert(cat: CatEntity) {
        catDao.insert(cat.toDBCatEntity())
    }

    override suspend fun delete(cat: CatEntity) {
        catDao.delete(cat.toDBCatEntity())
    }

    override suspend fun update(cat: CatEntity) {
        catDao.update(cat.toDBCatEntity())
    }
}