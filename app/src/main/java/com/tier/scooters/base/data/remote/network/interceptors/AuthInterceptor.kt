package com.tier.scooters.base.data.remote.network.interceptors

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class AuthInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var authorisedRequest: Request = chain.request()

        authorisedRequest = authorisedRequest.newBuilder()
            .addHeader(
                "secret-key",
                "$2b$10\$VE0tRqquld4OBl7LDeo9v.afsyRXFlXcQzmj1KpEB6K1wG2okzQcK"
            ).addHeader(
                "Content-Type",
                "application/json"
            )
            .build()
        return chain.proceed(authorisedRequest)
    }
}