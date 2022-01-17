package com.moneyme.exam.core.events.listeners

import com.moneyme.exam.core.events.ApplicationResumedEvent
import com.moneyme.exam.core.events.CountriesSynchronizationEvent
import com.moneyme.exam.core.events.managers.CountriesSynchronizeManager
import com.moneyme.exam.core.extensions.launch
import com.moneyme.exam.core.extensions.logDebug
import com.moneyme.exam.core.providers.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SynchronizeCountriesListener @Inject constructor(
    private val countriesSynchronizeManager: CountriesSynchronizeManager,
    private val coroutineContextProvider: CoroutineContextProvider,
    private val eventBus: EventBus
) {

    @Subscribe(threadMode = ThreadMode.ASYNC)
    fun onEvent(event: ApplicationResumedEvent) {
        GlobalScope.launch(
            coroutineContextProvider = coroutineContextProvider,
            work = {
                countriesSynchronizeManager.synchronize()
            },
            onSuccess = {
                logDebug("countries synchronized.")
            },
            onFailure = {
                eventBus.postSticky(CountriesSynchronizationEvent(false))
            }
        )
    }
}