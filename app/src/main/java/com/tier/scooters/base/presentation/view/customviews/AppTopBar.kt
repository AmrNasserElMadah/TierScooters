package com.tier.scooters.base.presentation.view.customviews

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.tier.scooters.base.data.extentions.toDp
import com.tier.scooters.base.data.extentions.toSp
import com.tier.scooters.base.presentation.view.theme.ColorBlack
import com.tier.scooters.base.presentation.view.theme.ColorTransparent

@Composable
fun AppTopBar(
    title: String,
    modifier: Modifier = Modifier,
    titleTextColor: Color = ColorBlack,
    titleTextSize: TextUnit = com.intuit.ssp.R.dimen._14ssp.toSp(),
    backgroundColor: Color = ColorTransparent,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        contentColor = titleTextColor,
        backgroundColor = backgroundColor,
        title = {
            AppText(
                text = title,
                color = titleTextColor,
                fontSize = titleTextSize,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        modifier = modifier
            .height(com.intuit.sdp.R.dimen._40sdp.toDp())
            .fillMaxWidth(),
        elevation = 0.dp,
        navigationIcon = navigationIcon,
        actions = actions
    )
}