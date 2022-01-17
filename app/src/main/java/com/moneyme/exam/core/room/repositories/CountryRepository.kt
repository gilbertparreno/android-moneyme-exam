package com.moneyme.exam.core.room.repositories

import com.moneyme.exam.core.room.base.BaseRoomRepository
import com.moneyme.exam.core.room.daos.CountryDao
import com.moneyme.exam.core.room.entities.Country
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryRepository @Inject constructor(
    private val countryDao: CountryDao
) : BaseRoomRepository<Country, CountryDao>(countryDao) {

    override suspend fun findAll(): List<Country> = countryDao.findAll()

    override suspend fun find(id: Int) = countryDao.find(id)

    suspend fun getCountryByName(name: String) = countryDao.getCountryByName(name)
}