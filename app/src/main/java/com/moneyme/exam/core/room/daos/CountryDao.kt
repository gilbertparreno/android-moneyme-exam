package com.moneyme.exam.core.room.daos

import androidx.room.Dao
import androidx.room.Query
import com.moneyme.exam.core.room.base.BaseRoomDao
import com.moneyme.exam.core.room.entities.Country

@Dao
interface CountryDao : BaseRoomDao<Country> {

    @Query("SELECT * FROM countries")
    suspend fun findAll(): List<Country>

    @Query("SELECT * FROM countries WHERE id = :id")
    suspend fun find(id: Int): Country?

    @Query("SELECT * FROM countries WHERE name = :name")
    suspend fun getCountryByName(name: String): Country?
}