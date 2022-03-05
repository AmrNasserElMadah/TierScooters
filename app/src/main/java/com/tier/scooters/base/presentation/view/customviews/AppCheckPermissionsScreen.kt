package com.tier.scooters.base.presentation.view.customviews

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.PermissionsRequired
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.tier.scooters.R
import com.tier.scooters.base.data.extentions.toDp
import com.tier.scooters.base.data.extentions.toSp
import com.tier.scooters.base.presentation.view.theme.ColorRed

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AppCheckPermissionsScreen(
    requiredPermissions: List<String>,
    requiredPermissionsState: MultiplePermissionsState = rememberMultiplePermissionsState(
        requiredPermissions
    ),
    permissionReasonText: String = stringResource(id = R.string.to_see_scooters),
    openSettings: () -> Unit = {},
    onPermissionsGrantedScreen: @Composable () -> Unit,
    onPermissionsRationaleScreen: @Composable (() -> Unit)? = null,
    onPermissionsNotGrantedScreen: @Composable () -> Unit = {
        AppText(
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = com.intuit.sdp.R.dimen._30sdp.toDp()),
            textAlign = TextAlign.Center,
            text = stringResource(
                id = R.string.must_permission_access, permissionReasonText

            ),
            fontSize = com.intuit.ssp.R.dimen._14ssp.toSp(),
            color = ColorRed
        )

        Spacer(modifier = Modifier.height(com.intuit.sdp.R.dimen._10sdp.toDp()))

        AppButton(
            modifier = Modifier.wrapContentSize(),
            onClick = {
                if (requiredPermissionsState.shouldShowRationale) {
                    requiredPermissionsState.launchMultiplePermissionRequest()
                } else {
                    openSettings.invoke()
                }
            },
            text =
            stringResource(id = if (requiredPermissionsState.shouldShowRationale) R.string.give_permissions_access else R.string.open_settings)
        )
    }
) {
    LaunchedEffect(key1 = "Check permissions") {
        requiredPermissionsState.launchMultiplePermissionRequest()
    }

    PermissionsRequired(
        multiplePermissionsState = requiredPermissionsState,
        permissionsNotGrantedContent = { onPermissionsNotGrantedScreen.invoke() },
        permissionsNotAvailableContent = {
            onPermissionsRationaleScreen?.invoke() ?: onPermissionsNotGrantedScreen.invoke()
        }) {
        onPermissionsGrantedScreen.invoke()
    }
}