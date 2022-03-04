package com.tier.scooters.base.presentation.enums

import androidx.compose.ui.graphics.Color
import com.tier.scooters.base.presentation.view.theme.ColorLightGray
import com.tier.scooters.base.presentation.view.theme.ColorPrimary
import com.tier.scooters.base.presentation.view.theme.ColorRed

enum class TextFieldStatus {
    EMPTY, VALID, INVALID
}

fun TextFieldStatus.getTextFieldColor(
    validColor: Color = ColorPrimary,
    invalidColor: Color = ColorRed,
    emptyColor: Color = ColorLightGray
): Color =
    when (this) {
        TextFieldStatus.VALID -> validColor
        TextFieldStatus.INVALID -> invalidColor
        else -> emptyColor
    }
