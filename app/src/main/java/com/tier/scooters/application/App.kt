package com.tier.scooters.application

import android.app.Application
import android.os.Bundle
import com.facebook.stetho.Stetho
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.tier.scooters.BuildConfig
import com.tier.scooters.util.EventsConstants
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {
    @Inject
    lateinit var firebaseAnalytics: FirebaseAnalytics

    @Inject
    lateinit var firebaseCrashlytics: FirebaseCrashlytics

    override fun onCreate() {
        super.onCreate()
        initStetho()
        sendOpenEventToFirebase()
    }

    private fun initStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                    .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                    .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                    .build()
            )
        }
    }

    private fun sendOpenEventToFirebase() {
        val bundle = Bundle()
        bundle.putString(EventsConstants.EVENT_START, EventsConstants.EVENT_OPENED_APP)
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, bundle)
    }

    fun sendFireBaseTrack(eventName: String?, keys: Array<String?>?, values: Array<String?>?) {
        val bundle = Bundle()
        try {
            if (keys != null) {
                for (i in keys.indices) bundle.putString(keys[i], values!![i])
                firebaseAnalytics.logEvent(eventName ?: "", bundle)
            } else {
                bundle.putString("Screen", eventName)
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle)
            }
        } catch (e: Exception) {
            val message = e.localizedMessage
            if (message != null) firebaseCrashlytics.log(message)
        }
    }
}