package com.tier.scooters.screens.mapscooters.presentation.view.screen

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.tier.scooters.R
import com.tier.scooters.base.data.extentions.toDp
import com.tier.scooters.base.data.remote.network.response.NetworkResponse
import com.tier.scooters.base.presentation.view.activity.BaseActivity
import com.tier.scooters.base.presentation.view.customviews.AppCheckPermissionsScreen
import com.tier.scooters.base.presentation.view.customviews.AppLoadingScreen
import com.tier.scooters.base.presentation.view.customviews.AppMapScreen
import com.tier.scooters.base.presentation.view.customviews.AppTopBar
import com.tier.scooters.base.presentation.view.theme.ColorPrimary
import com.tier.scooters.base.presentation.view.theme.ColorTransparent
import com.tier.scooters.base.presentation.view.theme.ColorWhite
import com.tier.scooters.screens.mapscooters.data.local.ScooterClusterItem
import com.tier.scooters.screens.mapscooters.presentation.viewmodel.ScootersMapViewModel
import com.tier.scooters.screens.scooterdetails.presentation.view.screen.ScooterDetailsBottomSheetScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScootersMapScreen(
    activity: BaseActivity,
    scootersMapViewModel: ScootersMapViewModel
) {

    val response = scootersMapViewModel.response.value
    val bottomSheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden,
        confirmStateChange = {
            it != ModalBottomSheetValue.HalfExpanded
        })

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            ScooterDetailsBottomSheetScreen(scooter = scootersMapViewModel.selectedScooterMarker.value)
        },
        sheetBackgroundColor = ColorWhite,
        sheetShape = RoundedCornerShape(
            topStart = com.intuit.sdp.R.dimen._22sdp.toDp(),
            topEnd = com.intuit.sdp.R.dimen._22sdp.toDp()
        ), modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(ColorTransparent)
        ) {
            AppTopBar(
                title = stringResource(id = R.string.scooters_map_title),
                titleTextColor = ColorWhite,
                backgroundColor = ColorPrimary
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(ColorTransparent),
            ) {
                AppCheckPermissionsScreen(
                    requiredPermissions = arrayListOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    onPermissionsGrantedScreen = {
                        AppLoadingScreen(
                            containerModifier = Modifier
                                .fillMaxSize(),
                            isError = response.error != null,
                            errorMessage = activity.getErrorMessage(
                                response.error ?: NetworkResponse.Initialization()
                            ),
                            isLoading = response.loading,
                            onRetryClicked = {
                                scootersMapViewModel.loadScooters()
                            },
                            content = {
                                val coroutineScope = rememberCoroutineScope()

                                AppMapScreen(context = activity,
                                    clusterItems = scootersMapViewModel.response.value.data,
                                    onMarkerClicked = { selectedScooterMarker ->
                                        coroutineScope.launch {
                                            bottomSheetState.animateTo(ModalBottomSheetValue.Expanded)
                                        }
                                        scootersMapViewModel.setSelectedScooterMarker(
                                            scootersMapViewModel.convertToScooterModel(
                                                selectedScooterMarker as ScooterClusterItem
                                            )
                                        )
                                    })
                            })
                    },
                    openSettings = {
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri: Uri = Uri.fromParts("package", activity.packageName, null)
                        intent.data = uri
                        activity.startActivity(intent)
                    },
                )
            }
        }
    }
}