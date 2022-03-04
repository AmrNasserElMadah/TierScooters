package com.tier.scooters.screens.mapscooters.domain.repository

import com.tier.scooters.base.data.remote.model.Scooter
import com.tier.scooters.base.data.remote.network.response.GenericResponse
import com.tier.scooters.base.data.remote.network.response.error.GenericError
import com.tier.scooters.base.data.remote.network.response.success.GenericResultsResponse
import kotlinx.coroutines.flow.Flow

interface ScootersRepository {
    suspend fun loadScooters(): Flow<GenericResponse<GenericResultsResponse<Scooter>, GenericError>>
}