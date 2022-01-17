package com.moneyme.exam.core.networking.services

import com.moneyme.exam.core.networking.entities.CountryApi
import retrofit2.http.GET

interface AppService {

    @GET("all")
    suspend fun getAllCountries(): List<CountryApi>
}