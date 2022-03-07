package com.tier.scooters.base.data.remote.network.response

import com.tier.scooters.base.data.remote.network.response.utils.ExceptionsUtils
import com.tier.scooters.base.data.remote.network.response.utils.ExceptionsUtils.Companion.isConnectivityRelatedException
import com.tier.scooters.base.data.remote.network.response.utils.ExceptionsUtils.Companion.isFieldException
import com.tier.scooters.base.data.remote.network.response.utils.ExceptionsUtils.Companion.isRateLimitExceededException
import com.tier.scooters.base.data.remote.network.response.utils.ExceptionsUtils.Companion.isServerCrashedException
import com.tier.scooters.base.data.remote.network.response.utils.ExceptionsUtils.Companion.isServerDownException
import com.tier.scooters.base.data.remote.network.response.utils.ExceptionsUtils.Companion.isSocketTimeoutException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

sealed class NetworkResponse<out T : Any, out U : Any> {
    data class Initialization(val dummy: String = "") : NetworkResponse<Nothing, Nothing>()
    data class Success<T : Any>(val body: T?) : NetworkResponse<T, Nothing>()
    data class ApiBodyError<U : Any>(val body: U, val code: Int) : NetworkResponse<Nothing, U>()
    data class NetworkError(val error: IOException?) : NetworkResponse<Nothing, Nothing>()
    data class AuthorizationError(val error: Throwable?) : NetworkResponse<Nothing, Nothing>()
    data class MaintenanceError(val error: Throwable?) : NetworkResponse<Nothing, Nothing>()
    data class ServerError(val error: Throwable?) : NetworkResponse<Nothing, Nothing>()
    data class TimeOutError(val error: Throwable?) : NetworkResponse<Nothing, Nothing>()
    data class UnknownError(val error: Throwable?) : NetworkResponse<Nothing, Nothing>()
}

fun NetworkResponse<Any, Any>.isNeedingShowError() = this is NetworkResponse.NetworkError
        || this is NetworkResponse.AuthorizationError
        || this is NetworkResponse.MaintenanceError
        || this is NetworkResponse.ServerError
        || this is NetworkResponse.TimeOutError
        || this is NetworkResponse.UnknownError


private fun getHttpExceptionIfFound(throwable: Throwable): HttpException? {
    if (throwable is HttpException) return throwable
    return if (throwable.cause != null && throwable.cause is HttpException) throwable.cause as HttpException? else null
}

private fun getIOExceptionIfFound(throwable: Throwable): IOException? {
    if (throwable is IOException) return throwable
    return if (throwable.cause != null && throwable.cause is IOException) throwable.cause as IOException? else null
}

private fun getSocketTimeoutExceptionIfFound(throwable: Throwable): SocketTimeoutException? {
    if (throwable is SocketTimeoutException) return throwable
    return if (throwable.cause != null && throwable.cause is SocketTimeoutException) throwable.cause as SocketTimeoutException? else null
}

fun Throwable.mapThrowableToNetworkError(): NetworkResponse<Any, Any> {
    if (isSocketTimeoutException(
            getSocketTimeoutExceptionIfFound(
                this
            )
        )
    ) return NetworkResponse.TimeOutError(this)
    else if (isConnectivityRelatedException(
            getIOExceptionIfFound(
                this
            )
        )
    ) return NetworkResponse.NetworkError(
        getIOExceptionIfFound(
            this
        )
    )
    else if (ExceptionsUtils.userIsUnAuthorizedException(
            getHttpExceptionIfFound(
                this
            )
        )
    )
        return NetworkResponse.AuthorizationError(
            getHttpExceptionIfFound(
                this
            )
        )
    else if (isFieldException(
            getHttpExceptionIfFound(
                this
            )
        )
    ) return NetworkResponse.ApiBodyError(
        code = getHttpExceptionIfFound(
            this
        )?.code() ?: 0,
        body = this
    ) else if (isServerCrashedException(
            getHttpExceptionIfFound(
                this
            )
        ) || isServerDownException(
            getHttpExceptionIfFound(
                this
            )
        )
    ) return NetworkResponse.ServerError(error = this)
    else if (isRateLimitExceededException(
            getHttpExceptionIfFound(
                this
            )
        )
    ) return NetworkResponse.ServerError(error = this)
    else return NetworkResponse.UnknownError(
        this
    )
}