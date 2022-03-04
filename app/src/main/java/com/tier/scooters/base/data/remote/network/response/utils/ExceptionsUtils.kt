package com.tier.scooters.base.data.remote.network.response.utils

import retrofit2.HttpException
import java.io.IOException
import java.lang.IllegalStateException
import java.lang.NullPointerException
import java.net.SocketTimeoutException
import java.util.NoSuchElementException

internal class ExceptionsUtils private constructor() {
    companion object {
        fun isConnectivityRelatedException(throwable: Throwable?): Boolean {
            return throwable is IOException
        }

        fun isSocketTimeoutException(throwable: Throwable?): Boolean {
            return throwable is SocketTimeoutException
        }

        fun userIsUnAuthorizedException(throwable: Throwable?): Boolean {
            if (throwable == null) return false
            if (throwable is HttpException) {
                return throwable.code() == 401
            } else if (throwable.cause != null && throwable.cause is HttpException) {
                val httpException = throwable.cause as HttpException?
                return httpException!!.code() == 401
            }
            return false
        }

        fun isUnProcessableEntityException(throwable: Throwable?): Boolean {
            if (throwable == null) return false
            if (throwable is HttpException) {
                return throwable.code() == 422
            } else if (throwable.cause != null && throwable.cause is HttpException) {
                val httpException = throwable.cause as HttpException?
                return httpException!!.code() == 422
            }
            return false
        }

        fun isFieldException(throwable: Throwable?): Boolean {
            if (throwable == null) return false
            if (throwable is HttpException) {
                return throwable.code() == 400
            } else if (throwable.cause != null && throwable.cause is HttpException) {
                val httpException = throwable.cause as HttpException?
                return httpException!!.code() == 400
            }
            return false
        }

        fun isServerCrashedException(throwable: Throwable?): Boolean {
            if (throwable == null) return false
            if (throwable is HttpException) {
                return throwable.code() == 500
            } else if (throwable.cause != null && throwable.cause is HttpException) {
                val httpException = throwable.cause as HttpException?
                return httpException!!.code() == 500
            }
            return false
        }

        fun isServerDownException(throwable: Throwable?): Boolean {
            if (throwable == null) return false
            if (throwable is HttpException) {
                return throwable.code() == 503
            } else if (throwable.cause != null && throwable.cause is HttpException) {
                val httpException = throwable.cause as HttpException?
                return httpException!!.code() == 503
            }
            return false
        }

        fun isRateLimitExceededException(throwable: Throwable?): Boolean {
            if (throwable == null) return false
            if (throwable is HttpException) {
                return throwable.code() == 429
            } else if (throwable.cause != null && throwable.cause is HttpException) {
                val httpException = throwable.cause as HttpException?
                return httpException!!.code() == 429
            }
            return false
        }

        fun isEmptyContentException(throwable: Throwable?): Boolean {
            return throwable is NoSuchElementException || throwable is NullPointerException
        }
    }

    init {
        throw IllegalStateException("No Instances.")
    }
}