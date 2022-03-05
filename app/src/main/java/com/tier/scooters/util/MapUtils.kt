package com.tier.scooters.util

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import com.google.maps.android.clustering.ClusterManager

class MapUtils {
    companion object MapUtils {
        @SuppressLint("PotentialBehaviorOverride", "MissingPermission")
        fun prepareMap(
            map: GoogleMap,
            clusterItems: List<ClusterItem>?,
            context: Context,
            onMarkerClicked: (clusterItem: ClusterItem) -> Unit
        ) {
            val gpsTracker = GPSTracker(context)

            val clusterManager = ClusterManager<ClusterItem>(context, map)
            clusterManager.renderer = ClusterRenderer(context, map, clusterManager)
            map.setOnCameraIdleListener(clusterManager)

            map.isMyLocationEnabled = true
            map.uiSettings.isZoomControlsEnabled = true
            map.uiSettings.isMyLocationButtonEnabled = true
            map.setOnMyLocationButtonClickListener(GoogleMap.OnMyLocationButtonClickListener {
                moveMapToMyLocation(map, gpsTracker)
                return@OnMyLocationButtonClickListener false
            })

            map.setOnMarkerClickListener(clusterManager)

            if (clusterItems.isNullOrEmpty().not()) {
                clusterManager.addItems(clusterItems)
                clusterManager.setOnClusterItemClickListener {
                    onMarkerClicked.invoke(it)
                    clusterManager.cluster()
                    return@setOnClusterItemClickListener false
                }
            }
            moveMapToMyLocation(map, gpsTracker)
        }

        private fun moveMapToMyLocation(map: GoogleMap, gpsTracker: GPSTracker) {
            map.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(
                        gpsTracker.getLatitude(), gpsTracker.getLongitude()
                    ),
                    5f
                )
            )
        }
    }
}