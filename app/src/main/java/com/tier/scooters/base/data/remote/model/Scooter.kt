package com.tier.scooters.base.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Scooter(
    @Expose
    @SerializedName("value")
    var value: String? = null,
    @Expose
    @SerializedName("display_name")
    var displayName: String? = null,

    @SerializedName("latitude")
    val latitude: Double = 0.0,

    @SerializedName("longitude")
    val longitude: Double = 0.0
)