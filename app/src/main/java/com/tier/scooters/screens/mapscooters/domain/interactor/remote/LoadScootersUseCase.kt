package com.tier.scooters.screens.mapscooters.domain.interactor.remote

import com.tier.scooters.base.data.remote.model.Scooter
import com.tier.scooters.base.data.remote.network.response.NetworkResponse
import com.tier.scooters.base.data.remote.network.response.error.GenericError
import com.tier.scooters.base.domain.interactor.FlowUseCaseNoParam
import com.tier.scooters.screens.mapscooters.domain.repository.ScootersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadScootersUseCase @Inject constructor(
    private val repository: ScootersRepository
) : FlowUseCaseNoParam<NetworkResponse<List<Scooter>, GenericError>>() {
    override suspend fun build(): Flow<NetworkResponse<List<Scooter>, GenericError>> =
        repository.loadScooters()
}