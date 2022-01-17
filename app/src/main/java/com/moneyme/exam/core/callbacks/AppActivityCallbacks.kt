package com.moneyme.exam.core.callbacks

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.moneyme.exam.core.events.ApplicationResumedEvent
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppActivityCallbacks @Inject constructor(
    private val eventBus: EventBus
) : Application.ActivityLifecycleCallbacks {
    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {}
    override fun onActivityStarted(activity: Activity) {}
    override fun onActivityResumed(activity: Activity) {
        eventBus.post(ApplicationResumedEvent(activity))
    }
    override fun onActivityPaused(activity: Activity) {}
    override fun onActivityStopped(activity: Activity) {}
    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {}
    override fun onActivityDestroyed(activity: Activity) {}
}