package com.moneyme.exam

import android.app.Application
import com.moneyme.exam.core.callbacks.AppActivityCallbacks
import com.moneyme.exam.core.di.AppComponent
import com.moneyme.exam.core.di.AppModule
import com.moneyme.exam.core.di.DaggerAppComponent
import com.moneyme.exam.core.events.initializer.EventBusInitializer
import com.moneyme.exam.core.networking.di.NetworkModule
import com.moneyme.exam.core.room.di.RoomModule
import timber.log.Timber
import javax.inject.Inject

class MoneyMeApplication : Application() {

    @Inject lateinit var eventBusInitializer: EventBusInitializer
    @Inject lateinit var appActivityCallbacks: AppActivityCallbacks

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule())
            .roomModule(RoomModule(this))
            .build()
            .also {
                it.inject(this)
            }

        eventBusInitializer.initialize()

        registerActivityLifecycleCallbacks(appActivityCallbacks)
    }
}