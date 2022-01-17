package com.moneyme.exam.core.room.di

import android.content.Context
import androidx.room.Room
import com.moneyme.exam.core.room.daos.CountryDao
import com.moneyme.exam.core.room.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(
    private val context: Context
) {

    @Provides
    @Singleton
    fun provideRoomDatabase(): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app-db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCountryDao(appDatabase: AppDatabase): CountryDao {
        return appDatabase.countryDao()
    }
}