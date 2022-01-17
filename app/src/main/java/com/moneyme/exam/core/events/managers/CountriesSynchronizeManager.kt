package com.moneyme.exam.core.events.managers

import com.moneyme.exam.core.events.CountriesSynchronizationEvent
import com.moneyme.exam.core.networking.entities.CountryApi
import com.moneyme.exam.core.networking.repositories.AppRepository
import com.moneyme.exam.core.room.entities.Country
import com.moneyme.exam.core.room.repositories.CountryRepository
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountriesSynchronizeManager @Inject constructor(
    private val appRepository: AppRepository,
    private val eventBus: EventBus,
    private val countryRepository: CountryRepository
) {

    suspend fun synchronize(postSyncEvent: Boolean = true) {
        val countries = appRepository.getAllCountries()
        synchronizeCountries(countries)
        if (postSyncEvent) {
            eventBus.postSticky(CountriesSynchronizationEvent(true))
        }
    }

    private suspend fun synchronizeCountries(countryApis: List<CountryApi>) {
        val forUpdate = mutableListOf<Country>()
        val forInsert = mutableListOf<Country>()
        countryApis.forEach { countryApi ->
            val localCountry = countryApi.name?.let {
                countryRepository.getCountryByName(it)
            }
            if (localCountry != null) {
                forUpdate.add(countryApi.toLocalCountry(localCountry))
            } else {
                forInsert.add(countryApi.toLocalCountry(Country()))
            }
        }
        countryRepository.update(*forUpdate.toTypedArray())
        countryRepository.insert(*forInsert.toTypedArray())
        val forDeletion = countryRepository.findAll()
            .filter { localCountry ->
                countryApis.firstOrNull { it.name == localCountry.name } == null
            }
        countryRepository.delete(*forDeletion.toTypedArray())
    }
}