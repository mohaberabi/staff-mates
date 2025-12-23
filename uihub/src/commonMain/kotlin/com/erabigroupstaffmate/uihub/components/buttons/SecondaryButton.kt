package com.erabigroupstaffmate.uihub.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    label: String = "",
    loading: Boolean = false,
) {
    Button(
        enabled = enabled,
        onClick = {
            if (!loading && enabled) {
                onClick()
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (enabled) MaterialTheme.colorScheme.background
            else Color.DarkGray,
            disabledContentColor = Color.DarkGray,
        ),
        border = BorderStroke(
            width = if (enabled) 2.dp else 0.dp,
            color = if (enabled) Color.Gray else Color.Transparent
        )
    ) {
        Box(
            modifier = Modifier.padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(15.dp)
                    .alpha(if (loading) 1f else 0f),
                strokeWidth = 4.dp,
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                modifier = Modifier
                    .alpha(if (!loading) 1f else 0f),
                text = label,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = if (enabled) MaterialTheme.colorScheme.primary else Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                )
            )
        }
    }
}