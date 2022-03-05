package com.tier.scooters.base.data.mapper

import com.tier.scooters.base.data.remote.model.Scooter
import com.tier.scooters.screens.mapscooters.data.local.ScooterClusterItem
import javax.inject.Inject

class ScooterMapper @Inject constructor() : Mapper<Scooter, ScooterClusterItem> {
    override fun from(e: ScooterClusterItem): Scooter {
        return Scooter(
            value = e.snippet,
            displayName = e.title,
            latitude = e.position.latitude,
            longitude = e.position.longitude
        )
    }

    override fun to(t: Scooter): ScooterClusterItem {
        return ScooterClusterItem(
            title = t.displayName ?: "",
            snippet = t.value ?: "",
            latitude = t.latitude,
            longitude = t.longitude,
        )
    }
}