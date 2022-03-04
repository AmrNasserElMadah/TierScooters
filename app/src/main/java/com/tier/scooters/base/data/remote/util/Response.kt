package com.tier.scooters.base.data.remote.util

import com.tier.scooters.base.data.remote.network.response.NetworkResponse

sealed class Response<T>(
    var data: T? = null,
    var loading: Boolean? = false,
    var knownError: NetworkResponse<Any, Any>? = null,
    var unknownThrowable: Throwable? = null
) {
    class Success<T>(data: T) : Response<T>(data = data)
    class Loading<T>(loading: Boolean?) : Response<T>(loading = loading)
    class KnownError<T>(throwable: NetworkResponse<Any, Any>?) : Response<T>(knownError = throwable)
    class UnknownError<T>(throwable: Throwable?) : Response<T>(unknownThrowable = throwable)
}