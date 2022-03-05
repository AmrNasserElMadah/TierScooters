package com.tier.scooters.base.data.remote.util

import com.tier.scooters.base.data.remote.network.response.NetworkResponse

sealed class Response<T>(
    var data: T? = null,
    var loading: Boolean = false,
    var error: NetworkResponse<Any, Any>? = null,
) {
    class Success<T>(data: T) : Response<T>(data = data)
    class Loading<T>(loading: Boolean) : Response<T>(loading = loading)
    class Error<T>(error: NetworkResponse<Any, Any>? = NetworkResponse.Initialization()) :
        Response<T>(error = error)
}