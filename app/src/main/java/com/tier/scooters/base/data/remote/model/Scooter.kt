package com.tier.scooters.base.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Scooter(
        @Expose
        @SerializedName("value")
        var value: String?,
        @Expose
        @SerializedName("display_name")
        var displayName: String?
)