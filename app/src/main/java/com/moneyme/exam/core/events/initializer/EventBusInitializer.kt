package com.moneyme.exam.core.events.initializer

import com.moneyme.exam.MoneyMeApplication
import com.moneyme.exam.core.events.listeners.SynchronizeCountriesListener
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventBusInitializer @Inject constructor() {

    @Inject lateinit var eventBus: EventBus
    @Inject lateinit var synchronizeCountriesListener: SynchronizeCountriesListener

    fun initialize() {
        MoneyMeApplication.appComponent.inject(this)
        eventBus.register(synchronizeCountriesListener)
    }
}