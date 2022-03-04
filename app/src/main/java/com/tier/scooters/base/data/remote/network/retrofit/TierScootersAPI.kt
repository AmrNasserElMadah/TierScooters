package com.tier.scooters.base.data.remote.network.retrofit

import com.tier.scooters.base.data.remote.model.Scooter
import com.tier.scooters.base.data.remote.network.response.GenericResponse
import com.tier.scooters.base.data.remote.network.response.error.GenericError
import com.tier.scooters.base.data.remote.network.response.success.GenericResultsResponse
import retrofit2.http.GET

interface TierScootersAPI {
    @GET("b/5fa8ff8dbd01877eecdb898f")
    suspend fun loadTierScooters(): GenericResponse<GenericResultsResponse<Scooter>, GenericError>
}