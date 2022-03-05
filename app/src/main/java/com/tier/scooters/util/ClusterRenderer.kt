package com.tier.scooters.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.TextView
import androidx.core.graphics.drawable.toBitmap
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterItem
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.google.maps.android.ui.IconGenerator
import com.tier.scooters.R

class ClusterRenderer(
    val context: Context,
    map: GoogleMap?,
    clusterManager: ClusterManager<ClusterItem>
) :
    DefaultClusterRenderer<ClusterItem>(context, map, clusterManager) {
    private val mClusterIconGenerator = IconGenerator(context)
    override fun onBeforeClusterItemRendered(
        item: ClusterItem,
        markerOptions: MarkerOptions
    ) {
        val clusterIcon: Drawable = context.resources.getDrawable(R.drawable.ic_launcher_foreground)
        val markerDescriptor =
            BitmapDescriptorFactory.fromBitmap(clusterIcon.toBitmap())
        markerOptions.icon(markerDescriptor)
    }

    override fun onBeforeClusterRendered(
        cluster: Cluster<ClusterItem?>,
        markerOptions: MarkerOptions
    ) {
        val isLessLimit = cluster.size < 10
        val tvClusterSize = TextView(context)
        tvClusterSize.text = if (isLessLimit) cluster.size.toString() else "+9"
        tvClusterSize.textSize =
            context.resources.getDimensionPixelOffset(com.intuit.ssp.R.dimen._4ssp).toFloat()
        tvClusterSize.setTextColor(context.resources.getColor(R.color.colorPrimary))
        mClusterIconGenerator.setContentView(tvClusterSize)
        val icon = mClusterIconGenerator.makeIcon()
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon))
    }
}