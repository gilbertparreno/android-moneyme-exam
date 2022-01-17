package com.moneyme.exam.main.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneyme.exam.core.events.managers.CountriesSynchronizeManager
import com.moneyme.exam.core.extensions.SingleLiveEvent
import com.moneyme.exam.core.extensions.launch
import com.moneyme.exam.core.networking.taskStatus.TaskStatus
import com.moneyme.exam.core.providers.CoroutineContextProvider
import com.moneyme.exam.core.room.repositories.CountryRepository
import com.moneyme.exam.main.entities.CountryItem
import com.moneyme.exam.main.factories.MainFactory
import kotlinx.coroutines.delay
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val countryRepository: CountryRepository,
    private val coroutineContextProvider: CoroutineContextProvider,
    private val countriesSynchronizeManager: CountriesSynchronizeManager
) : ViewModel() {

    val countriesEvent = SingleLiveEvent<TaskStatus<List<CountryItem>>>()

    fun getCountries(ignoreUpdatesFromApi: Boolean = true) {
        viewModelScope.launch(
            coroutineContextProvider = coroutineContextProvider,
            work = {
                delay(250)
                if (!ignoreUpdatesFromApi) {
                    countriesSynchronizeManager.synchronize(false)
                }
                MainFactory.getCountryItems(countryRepository.findAll())
            },
            onSuccess = {
                countriesEvent.value = TaskStatus.success(it)
            },
            onFailure = {
                countriesEvent.value = TaskStatus.error(it)
            }
        )
    }
}