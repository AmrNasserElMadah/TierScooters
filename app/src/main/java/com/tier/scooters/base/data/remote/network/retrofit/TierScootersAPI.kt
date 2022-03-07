package com.tier.scooters.base.data.remote.network.retrofit

import com.tier.scooters.base.data.remote.model.Scooter
import com.tier.scooters.base.data.remote.network.response.NetworkResponse
import com.tier.scooters.base.data.remote.network.response.error.GenericError
import retrofit2.http.GET

interface TierScootersAPI {
    @GET("b/5fa8ff8dbd01877eecdb898f")
    suspend fun loadTierScooters(): NetworkResponse<List<Scooter>, GenericError>
}