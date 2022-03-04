package com.tier.scooters.screens.mapscooters.domain.interactor.remote

import com.tier.scooters.base.data.remote.model.Scooter
import com.tier.scooters.base.data.remote.network.response.GenericResponse
import com.tier.scooters.base.data.remote.network.response.error.GenericError
import com.tier.scooters.base.data.remote.network.response.success.GenericResultsResponse
import com.tier.scooters.base.domain.interactor.FlowUseCaseNoParam
import com.tier.scooters.screens.mapscooters.domain.repository.ScootersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadScootersUseCase @Inject constructor(
    private val repository: ScootersRepository
) : FlowUseCaseNoParam<GenericResponse<GenericResultsResponse<Scooter>, GenericError>>() {
    override suspend fun build(): Flow<GenericResponse<GenericResultsResponse<Scooter>, GenericError>> =
        repository.loadScooters()
}