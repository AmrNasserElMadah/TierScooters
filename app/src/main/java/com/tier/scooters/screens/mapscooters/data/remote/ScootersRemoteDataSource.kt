package com.tier.scooters.screens.mapscooters.data.remote

import com.tier.scooters.base.data.remote.network.retrofit.TierScootersAPI
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ScootersRemoteDataSource @Inject constructor(
    private val tierScootersAPI: TierScootersAPI
) {
    suspend fun loaScooters() =
        flow {
            val response = tierScootersAPI.loadTierScooters()
            emit(response)
        }
}