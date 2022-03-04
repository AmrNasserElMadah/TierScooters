package com.tier.scooters.base.data.remote.network.response.error

import com.google.gson.annotations.SerializedName

data class GenericError(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)