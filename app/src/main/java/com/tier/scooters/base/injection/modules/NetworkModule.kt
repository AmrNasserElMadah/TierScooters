package com.tier.scooters.base.injection.modules

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tier.scooters.base.data.remote.network.interceptors.AuthInterceptor
import com.tier.scooters.base.data.remote.network.retrofit.RetrofitClient
import com.tier.scooters.base.data.remote.network.retrofit.TierScootersAPI
import com.tier.scooters.base.data.remote.util.BaseUrlUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
open class NetworkModule {

    object DaggerNamedConstants {
        const val BASE_URL = "baseUrlString"
    }

    object ApiEndpointsConstants {
        const val ProductionUrlDomain = "https://api.jsonbin.io/"
        const val DevelopmentUrlDomain = "https://api.jsonbin.io/"
        const val StagingUrlDomain = "https://api.jsonbin.io/"
    }

    @Provides
    @Singleton
    @Named(value = DaggerNamedConstants.BASE_URL)
    fun providesBaseUrl() = BaseUrlUtils.getBaseUrl()

    @Provides
    @Singleton
    fun provideHttpClient() = OkHttpClient()

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor()

    @Provides
    @Singleton
    fun provideAuthInterceptor() =
        AuthInterceptor()

    @Provides
    @Singleton
    fun provideChuckerInterceptor(context: Context) = ChuckerInterceptor(context)

    @Provides
    @Singleton
    fun provideRetrofit() = Retrofit.Builder()

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    @Singleton
    fun provideRetrofitClient(
        @Named(DaggerNamedConstants.BASE_URL) baseURL: String,
        httpClient: OkHttpClient,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        chuckerInterceptor: ChuckerInterceptor,
        builder: Retrofit.Builder,
        gson: Gson,
        authInterceptor: AuthInterceptor,
    )
            : Retrofit = RetrofitClient(
        baseURL = baseURL,
        httpClient = httpClient.newBuilder(),
        authInterceptor = authInterceptor,
        httpLoggingInterceptor = httpLoggingInterceptor,
        chuckerInterceptor = chuckerInterceptor,
        builder = builder,
        gson = gson
    ).getInstance()

    @Provides
    @Singleton
    fun provideActivationAgentAPI(retrofit: Retrofit)
            : TierScootersAPI = retrofit.create(TierScootersAPI::class.java)
}