package com.tier.scooters.base.domain.interactor

import kotlinx.coroutines.flow.Flow

abstract class FlowUseCaseNoParam<Type> where Type : Any {
    abstract suspend fun build(): Flow<Type>
}