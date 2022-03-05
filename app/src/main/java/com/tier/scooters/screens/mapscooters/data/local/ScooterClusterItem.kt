package com.tier.scooters.screens.mapscooters.data.local

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

class ScooterClusterItem(
    private val title: String = "",
    private val snippet: String = "",
    latitude: Double = 0.0,
    longitude: Double = 0.0
) : ClusterItem {

    private val position: LatLng = LatLng(latitude, longitude)

    override fun getPosition(): LatLng {
        return position
    }

    override fun getTitle(): String {
        return title
    }

    override fun getSnippet(): String {
        return snippet
    }
}
