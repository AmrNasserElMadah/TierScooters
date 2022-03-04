package com.tier.scooters.base.data.remote.network.response.error

import com.google.gson.annotations.SerializedName

data class GenericError(
    @SerializedName("non_field_errors")
    val errors: List<String>
)