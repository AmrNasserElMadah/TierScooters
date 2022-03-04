package com.tier.scooters.screens.mapscooters.domain.repository

import com.tier.scooters.base.data.remote.model.Scooter
import com.tier.scooters.base.data.remote.network.response.GenericResponse
import com.tier.scooters.base.data.remote.network.response.error.GenericError
import com.tier.scooters.base.data.remote.network.response.success.GenericResultsResponse
import com.tier.scooters.screens.mapscooters.data.remote.ScootersRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ScootersRepositoryImp @Inject constructor(
    private val scootersRemoteDataSource: ScootersRemoteDataSource
) : ScootersRepository {
    override suspend fun loadScooters(): Flow<GenericResponse<GenericResultsResponse<Scooter>, GenericError>> =
        scootersRemoteDataSource.loaScooters()
}