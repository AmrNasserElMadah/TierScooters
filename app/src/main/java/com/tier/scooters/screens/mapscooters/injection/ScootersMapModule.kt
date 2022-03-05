package com.tier.scooters.screens.mapscooters.injection

import com.tier.scooters.base.data.remote.network.retrofit.TierScootersAPI
import com.tier.scooters.screens.mapscooters.data.remote.ScootersRemoteDataSource
import com.tier.scooters.screens.mapscooters.domain.repository.ScootersRepository
import com.tier.scooters.screens.mapscooters.domain.repository.ScootersRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class ScootersMapModule {

    @Provides
    fun providesScootersRemoteDataSource(tierScootersAPI: TierScootersAPI) =
        ScootersRemoteDataSource(tierScootersAPI = tierScootersAPI)

    @Provides
    fun providesScootersRepository(scootersRemoteDataSource: ScootersRemoteDataSource)
            : ScootersRepository =
        ScootersRepositoryImp(scootersRemoteDataSource = scootersRemoteDataSource)
}