package com.tier.scooters.base.data.remote.util

import com.tier.scooters.BuildConfig
import com.tier.scooters.base.injection.modules.NetworkModule

class BaseUrlUtils {

    companion object {
        fun getBaseUrl(): String {
            return when (BuildConfig.BuildType) {
                BuildType.Development.name -> {
                    NetworkModule.ApiEndpointsConstants.DevelopmentUrlDomain
                }
                BuildType.Staging.name -> {
                    NetworkModule.ApiEndpointsConstants.StagingUrlDomain
                }
                BuildType.Production.name -> {
                    NetworkModule.ApiEndpointsConstants.ProductionUrlDomain
                }
                else -> {
                    NetworkModule.ApiEndpointsConstants.DevelopmentUrlDomain
                }
            }
        }
    }
}

enum class BuildType {
    Development, Staging, Production
}