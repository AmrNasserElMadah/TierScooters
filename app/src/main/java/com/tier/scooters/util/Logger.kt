package com.tier.scooters.util

import android.util.Log
import com.tier.scooters.BuildConfig

object Logger {

    fun <T : Any> d(clazz: T, msg: String) {
        if (BuildConfig.DEBUG) {
            Log.d(clazz::class.java.simpleName, msg)
        }
    }

    fun e(t: Throwable) {
        if (BuildConfig.DEBUG) {
            t.printStackTrace()
        }
    }
}