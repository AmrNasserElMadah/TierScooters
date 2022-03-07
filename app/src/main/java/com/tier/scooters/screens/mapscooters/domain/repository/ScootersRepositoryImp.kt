package com.tier.scooters.screens.mapscooters.domain.repository

import com.tier.scooters.base.data.remote.model.Scooter
import com.tier.scooters.base.data.remote.network.response.NetworkResponse
import com.tier.scooters.base.data.remote.network.response.error.GenericError
import com.tier.scooters.screens.mapscooters.data.remote.ScootersRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ScootersRepositoryImp @Inject constructor(
    private val scootersRemoteDataSource: ScootersRemoteDataSource
) : ScootersRepository {
    override suspend fun loadScooters(): Flow<NetworkResponse<List<Scooter>, GenericError>> =
        scootersRemoteDataSource.loaScooters()
}