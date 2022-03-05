package com.tier.scooters.base.presentation.view.customviews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.tier.scooters.R
import com.tier.scooters.base.data.extentions.toDp
import com.tier.scooters.base.presentation.view.theme.ColorEbonyClay
import com.tier.scooters.base.presentation.view.theme.ColorRed
import com.tier.scooters.base.presentation.view.theme.ColorTransparent
import com.tier.scooters.base.presentation.view.theme.ColorWhiteAlabaster

@Composable
fun AppLoadingScreen(
    containerModifier: Modifier = Modifier.fillMaxSize(),
    backgroundColor: Color = ColorTransparent,
    content: @Composable ColumnScope.() -> Unit,
    isLoading: Boolean = false,
    isDimmedLoading: Boolean = false,
    onDimmedLoadingDismissRequest: () -> Unit = {},
    isError: Boolean = false,
    errorMessage: String = stringResource(id = R.string.unknown_error_message),
    errorContent: @Composable () -> Unit = {
        AppText(
            text = errorMessage,
            color = ColorRed,
            textAlign = TextAlign.Center
        )
    },
    errorView: @Composable () -> Unit = {
        AppErrorScreen(
            errorContent = errorContent,
            onRetryClicked = onRetryClicked
        )
    },
    onRetryClicked: () -> Unit = {}
) {
    Column(
        modifier = containerModifier.background(backgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isDimmedLoading) {
            Dialog(
                onDismissRequest = onDimmedLoadingDismissRequest,
                DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(com.intuit.sdp.R.dimen._30sdp.toDp())
                        .padding(top = com.intuit.sdp.R.dimen._8sdp.toDp()),
                    color = ColorWhiteAlabaster
                )
            }
        }

        if (!isLoading) {
            if (isError) {
                errorView.invoke()
            } else {
                content.invoke(this)
            }
        } else {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(com.intuit.sdp.R.dimen._30sdp.toDp())
                    .padding(top = com.intuit.sdp.R.dimen._8sdp.toDp()),
                color = ColorEbonyClay
            )
        }
    }
}