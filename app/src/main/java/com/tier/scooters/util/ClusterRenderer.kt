package com.tier.scooters.util

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.TextView
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

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBeforeClusterRendered(
        cluster: Cluster<ClusterItem?>,
        markerOptions: MarkerOptions
    ) {
        val clusterIcon: Drawable = context.resources.getDrawable(R.drawable.ic_circle_shape)
        mClusterIconGenerator.setBackground(clusterIcon)

        val isLessLimit = cluster.size < 10
        val tvClusterSize = TextView(context)
        tvClusterSize.text = if (isLessLimit) cluster.size.toString() else "+9"
        tvClusterSize.textSize =
            context.resources.getDimensionPixelOffset(com.intuit.ssp.R.dimen._4ssp).toFloat()
        tvClusterSize.setTextColor(context.resources.getColor(android.R.color.white))
        mClusterIconGenerator.setContentView(tvClusterSize)

        mClusterIconGenerator.setContentPadding(
            context.resources.getDimensionPixelOffset(if (isLessLimit) com.intuit.sdp.R.dimen._10sdp else com.intuit.sdp.R.dimen._7sdp),
            context.resources.getDimensionPixelOffset(com.intuit.sdp.R.dimen._5sdp),
            0, 0
        )

        val icon = mClusterIconGenerator.makeIcon()
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon))
    }
}