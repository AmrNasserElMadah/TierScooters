package com.tier.scooters.base.domain.interactor

import kotlinx.coroutines.flow.Flow

abstract class FlowUseCase<in Params, Type> where Type : Any {
    abstract suspend fun build(params: Params): Flow<Type>
}