package com.moneyme.exam.core.networking.repositories

import com.moneyme.exam.core.networking.services.AppService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(
    private val appService: AppService
) {
    suspend fun getAllCountries() = appService.getAllCountries()
}