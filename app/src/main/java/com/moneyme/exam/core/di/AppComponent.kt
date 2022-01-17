package com.moneyme.exam.core.di

import com.moneyme.exam.MoneyMeApplication
import com.moneyme.exam.core.events.initializer.EventBusInitializer
import com.moneyme.exam.core.networking.di.NetworkModule
import com.moneyme.exam.core.room.di.RoomModule
import com.moneyme.exam.details.fragments.DetailsFragment
import com.moneyme.exam.main.activities.MainActivity
import com.moneyme.exam.main.fragments.MainFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, NetworkModule::class, RoomModule::class])
@Singleton
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: MainFragment)
    fun inject(detailsFragment: DetailsFragment)

    fun inject(moneyMeApplication: MoneyMeApplication)
    fun inject(eventBusInitializer: EventBusInitializer)
}