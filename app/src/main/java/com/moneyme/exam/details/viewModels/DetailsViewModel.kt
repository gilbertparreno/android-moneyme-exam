package com.moneyme.exam.details.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneyme.exam.core.extensions.SingleLiveEvent
import com.moneyme.exam.core.extensions.launch
import com.moneyme.exam.core.networking.taskStatus.TaskStatus
import com.moneyme.exam.core.providers.CoroutineContextProvider
import com.moneyme.exam.core.room.entities.Country
import com.moneyme.exam.core.room.repositories.CountryRepository
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val countryRepository: CountryRepository,
    private val coroutineContextProvider: CoroutineContextProvider,
) : ViewModel() {

    val countryEvent = SingleLiveEvent<TaskStatus<Country>>()

    fun getCountry(id: Int) {
        viewModelScope.launch(
            coroutineContextProvider = coroutineContextProvider,
            work = {
                countryRepository.find(id)
            },
            onSuccess = {
                countryEvent.value = TaskStatus.success(it!!)
            },
            onFailure = {
                countryEvent.value = TaskStatus.error(it)
            }
        )
    }
}