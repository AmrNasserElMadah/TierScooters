package com.tier.scooters.base.data.remote.network.retrofit

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import com.tier.scooters.BuildConfig
import com.tier.scooters.base.data.remote.network.interceptors.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitClient @Inject constructor(
    private val baseURL: String,
    private val httpClient: OkHttpClient.Builder,
    private val authInterceptor: AuthInterceptor,
    private val httpLoggingInterceptor: HttpLoggingInterceptor,
    private val chuckerInterceptor: ChuckerInterceptor,
    private val builder: Retrofit.Builder,
    private val gson: Gson
) {

    fun getInstance(): Retrofit {
        httpClient.addInterceptor(authInterceptor)

        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(httpLoggingInterceptor)
        }
        httpClient.addInterceptor(chuckerInterceptor)
        builder.baseUrl(baseURL)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))

        builder.client(httpClient.build())
        return builder.build()
    }
}



