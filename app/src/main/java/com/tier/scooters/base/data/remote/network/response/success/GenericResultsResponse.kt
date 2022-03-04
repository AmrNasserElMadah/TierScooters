package com.tier.scooters.base.data.remote.network.response.success

import com.google.gson.annotations.SerializedName

data class GenericResultsResponse<S>(
    @SerializedName("results")
    var results: ArrayList<S>,
    @SerializedName("count")
    var count: Int
)