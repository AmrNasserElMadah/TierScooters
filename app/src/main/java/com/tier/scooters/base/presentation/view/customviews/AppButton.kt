package com.tier.scooters.base.presentation.view.customviews

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.tier.scooters.base.data.extentions.toDp
import com.tier.scooters.base.data.extentions.toSp
import com.tier.scooters.base.presentation.view.theme.ColorPrimary
import com.tier.scooters.base.presentation.view.theme.ColorWhite

//Sample for using it
//AppButton(onClick = { }, text = "Test", cornerRadius = R.dimen._5sdp.toDp())

@Composable
fun AppButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(
            com.intuit.sdp.R.dimen._50sdp.toDp()
        ),
    backgroundColor: Color = ColorPrimary,
    cornerRadius: Dp = com.intuit.sdp.R.dimen._5sdp.toDp(),
    text: String = "",
    textColor: Color = ColorWhite,
    fontSize: TextUnit = com.intuit.ssp.R.dimen._12ssp.toSp(),
    enabled: Boolean = true,
    loading: Boolean = false,
    content: @Composable (() -> Unit) = {
        AppText(
            text = text,
            fontSize = fontSize,
            color = textColor
        )
    },
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(cornerRadius),
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
        enabled = enabled
    ) {
        if (!loading) {
            content()
        } else {
            CircularProgressIndicator(modifier = Modifier.size(com.intuit.sdp.R.dimen._30sdp.toDp()))
        }
    }
}