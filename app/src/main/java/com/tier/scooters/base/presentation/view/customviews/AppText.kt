package com.tier.scooters.base.presentation.view.customviews

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.intuit.ssp.R
import com.tier.scooters.base.data.extentions.toSp
import com.tier.scooters.base.presentation.enums.TextFieldStatus
import com.tier.scooters.base.presentation.view.theme.ColorEbonyClay
import com.tier.scooters.base.presentation.view.theme.ColorRed

@Composable
fun AppText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = ColorEbonyClay,
    textDirection: TextDirection? = null,
    textAlign: TextAlign? = if (textDirection != null) null else TextAlign.Start,
    fontSize: TextUnit = R.dimen._12ssp.toSp(),
    singleLine: Boolean = false,
    ellipsize: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    textFieldStatus: TextFieldStatus = TextFieldStatus.VALID
) {
    Text(
        modifier = modifier.alpha(if (textFieldStatus == TextFieldStatus.EMPTY) 0.5f else 1f),
        text = text,
        color = if (textFieldStatus == TextFieldStatus.VALID) color else if (textFieldStatus == TextFieldStatus.INVALID) ColorRed else ColorEbonyClay,
        fontSize = fontSize,
        style = TextStyle(textDirection = textDirection, textAlign = textAlign),
        maxLines = if (singleLine) 1 else maxLines,
        overflow = if (ellipsize) TextOverflow.Ellipsis else TextOverflow.Clip
    )
}

@Composable
fun AppText(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    color: Color = ColorEbonyClay,
    textDirection: TextDirection? = null,
    textAlign: TextAlign? = if (textDirection != null) null else TextAlign.Start,
    fontSize: TextUnit = R.dimen._12ssp.toSp(),
    singleLine: Boolean = false,
    ellipsize: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    textFieldStatus: TextFieldStatus = TextFieldStatus.VALID,
) {
    Text(
        modifier = modifier.alpha(if (textFieldStatus == TextFieldStatus.EMPTY) 0.5f else 1f),
        text = text,
        color = if (textFieldStatus == TextFieldStatus.VALID) color else if (textFieldStatus == TextFieldStatus.INVALID) ColorRed else ColorEbonyClay,
        fontSize = fontSize,
        style = TextStyle(textDirection = textDirection, textAlign = textAlign),
        maxLines = if (singleLine) 1 else maxLines,
        overflow = if (ellipsize && singleLine) TextOverflow.Ellipsis else TextOverflow.Clip
    )
}