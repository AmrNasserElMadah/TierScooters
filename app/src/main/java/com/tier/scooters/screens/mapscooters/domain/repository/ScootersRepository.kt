package com.tier.scooters.screens.mapscooters.domain.repository

import com.tier.scooters.base.data.remote.model.Scooter
import com.tier.scooters.base.data.remote.network.response.NetworkResponse
import com.tier.scooters.base.data.remote.network.response.error.GenericError
import kotlinx.coroutines.flow.Flow

interface ScootersRepository {
    suspend fun loadScooters(): Flow<NetworkResponse<List<Scooter>, GenericError>>
}