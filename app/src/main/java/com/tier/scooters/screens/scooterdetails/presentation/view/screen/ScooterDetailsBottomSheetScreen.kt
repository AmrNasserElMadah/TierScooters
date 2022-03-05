package com.tier.scooters.screens.scooterdetails.presentation.view.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.tier.scooters.base.data.extentions.toDp
import com.tier.scooters.base.data.remote.model.Scooter
import com.tier.scooters.base.presentation.view.customviews.AppText

@Composable
fun ScooterDetailsBottomSheetScreen(
    scooter: Scooter
) {
    Spacer(modifier = Modifier.height(com.intuit.sdp.R.dimen._100sdp.toDp()))
    AppText(
        text = scooter.displayName ?: "",
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(com.intuit.sdp.R.dimen._100sdp.toDp()))
}