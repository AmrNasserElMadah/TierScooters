package com.tier.scooters.base.presentation.view.customviews

import android.content.Context
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.android.gms.maps.MapView
import com.google.maps.android.clustering.ClusterItem
import com.tier.scooters.R
import com.tier.scooters.util.MapUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun AppMapScreen(
    context: Context,
    clusterItems: List<ClusterItem>?,
    onMarkerClicked: (clusterItem: ClusterItem) -> Unit
) {
    val mapView = rememberMapViewWithLifecycle()
    val coroutineScope = rememberCoroutineScope()

    AndroidView({ mapView })
    { map ->
        coroutineScope.launch(Dispatchers.Main) {
            map.getMapAsync { googleMap ->
                MapUtils.prepareMap(
                    map = googleMap,
                    clusterItems = clusterItems,
                    context = context
                ) { selectedMarker ->
                    onMarkerClicked(selectedMarker)
                }
            }
        }
    }
}

@Composable
fun rememberMapViewWithLifecycle(): MapView {
    val context = LocalContext.current
    val mapView = remember {
        MapView(context).apply {
            id = R.id.map
        }
    }

    // Makes MapView follow the lifecycle of this composable
    val lifecycleObserver = rememberMapLifecycleObserver(mapView)
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle) {
        lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycle.removeObserver(lifecycleObserver)
        }
    }

    return mapView
}

@Composable
fun rememberMapLifecycleObserver(mapView: MapView): LifecycleEventObserver =
    remember(mapView) {
        LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> mapView.onCreate(Bundle())
                Lifecycle.Event.ON_START -> mapView.onStart()
                Lifecycle.Event.ON_RESUME -> mapView.onResume()
                Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                Lifecycle.Event.ON_STOP -> mapView.onStop()
                Lifecycle.Event.ON_DESTROY -> mapView.onDestroy()
                else -> throw IllegalStateException()
            }
        }
    }