package com.moneyme.exam.core.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moneyme.exam.core.room.daos.CountryDao
import com.moneyme.exam.core.room.entities.Country

@Database(entities = [Country::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
}