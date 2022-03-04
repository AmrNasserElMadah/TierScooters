package com.tier.scooters.base.presentation.view.activity

import android.content.Intent
import android.view.MenuItem
import androidx.activity.ComponentActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.tier.scooters.R
import com.tier.scooters.application.App
import com.tier.scooters.base.data.remote.network.response.NetworkResponse
import com.tier.scooters.screens.main.presentation.view.activity.MainActivity
import com.tier.scooters.util.Constants
import com.tier.scooters.util.EventsConstants
import com.tier.scooters.util.NetworkUtils
import javax.inject.Inject

abstract class BaseActivity : ComponentActivity() {
    @Inject
    lateinit var app: App

    @Inject
    lateinit var firebaseAnalytics: FirebaseAnalytics

    @Inject
    lateinit var firebaseCrashlytics: FirebaseCrashlytics

    private var errorMessage = ""

    fun getErrorMessage(
        networkError: NetworkResponse<Any, Any> = NetworkResponse.Initialization()
    ): String {
        when (networkError) {
            is NetworkResponse.NetworkError -> {
                errorMessage = if (isNetworkConnected) {
                    getString(R.string.internet_connection_problem)
                } else {
                    getString(R.string.no_internet_message)
                }
            }
            is NetworkResponse.AuthorizationError -> {
                app.sendFireBaseTrack(EventsConstants.EVENT_LOGOUT_UNAUTHORIZED, null, null)
                val mainActivityIntent = Intent(this, MainActivity::class.java)
                mainActivityIntent.putExtra(Constants.KEY_SHOW_UNAUTHORIZED_MESSAGE, true)
                mainActivityIntent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(mainActivityIntent)
            }
            is NetworkResponse.ServerError -> {
                errorMessage =
                    getString(R.string.server_crashed)
            }
            is NetworkResponse.MaintenanceError -> {
                errorMessage =
                    getString(R.string.server_down)
            }
            is NetworkResponse.TimeOutError -> {
                errorMessage = getString(R.string.request_timeout)
            }
            is NetworkResponse.UnknownError -> {
                errorMessage =
                    getString(R.string.unknown_error_message)
            }
            else -> {
                errorMessage =
                    getString(R.string.unknown_error_message)
            }
        }
        return errorMessage
    }

    private val isNetworkConnected: Boolean
        get() = NetworkUtils.haveActiveNetwork(applicationContext)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}