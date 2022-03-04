package com.tier.scooters.base.data.extentions

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType

@OptIn(ExperimentalUnitApi::class)
@Composable
fun Int.toSp(): TextUnit {
    return TextUnit(dimensionResource(id = this).value, type = TextUnitType.Sp)
}

@Composable
fun Int.toDp(): Dp {
    return dimensionResource(id = this)
}

@Composable
fun Int.toIntValue(): Int {
    return dimensionResource(id = this).value.toInt()
}