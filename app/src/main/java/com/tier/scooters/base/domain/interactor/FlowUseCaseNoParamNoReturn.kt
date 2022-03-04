package com.tier.scooters.base.domain.interactor

abstract class FlowUseCaseNoParamNoReturn {
    abstract suspend fun build()
}