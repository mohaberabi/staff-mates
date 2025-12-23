package com.erabigroupstaffmate.uihub.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.AssistChip
import androidx.compose.runtime.Composable
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun AppAssistedChip(
    onClick: () -> Unit,
    isSelected: Boolean,
    label: @Composable () -> Unit
) {
    val color = if (isSelected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.background
    }

    val labelColor = if (isSelected) {
        Color.White
    } else {
        MaterialTheme.typography.labelLarge.color
    }

    AssistChip(
        border = BorderStroke(
            width = if (isSelected) 2.dp else 1.dp,
            color = if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray
        ),
        colors = AssistChipDefaults.assistChipColors(
            containerColor = color,
            labelColor = labelColor,
        ),
        onClick = onClick,
        label = label,
    )
}


