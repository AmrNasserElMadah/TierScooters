package com.tier.scooters.base.domain.interactor

abstract class FlowUseCaseNoReturn<in Params> {
    abstract suspend fun build(params: Params)
}