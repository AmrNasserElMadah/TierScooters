package com.tier.scooters.base.presentation.view.customviews

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.tier.scooters.base.data.extentions.toDp
import com.tier.scooters.base.presentation.view.theme.ColorRed
import com.tier.scooters.base.presentation.view.theme.ColorWhite
import com.tier.scooters.R

@Composable
fun AppErrorScreen(
    errorContent: @Composable (() -> Unit),
    onRetryClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        errorContent()

        Spacer(modifier = Modifier.height(com.intuit.sdp.R.dimen._10sdp.toDp()))

        AppButton(
            modifier = Modifier.padding(horizontal = com.intuit.sdp.R.dimen._16sdp.toDp()),
            onClick = onRetryClicked,
            text = stringResource(id = R.string.retry),
            textColor = ColorWhite,
            backgroundColor = ColorRed
        )
    }
}