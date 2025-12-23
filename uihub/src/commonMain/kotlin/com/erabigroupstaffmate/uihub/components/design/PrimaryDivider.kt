package com.erabigroupstaffmate.uihub.components.design

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun PrimaryDivider(
    height: Dp = 0.7.dp,
) {


    HorizontalDivider(
        thickness = height,
        color = Color.LightGray.copy(alpha = 0.3f),
    )
}