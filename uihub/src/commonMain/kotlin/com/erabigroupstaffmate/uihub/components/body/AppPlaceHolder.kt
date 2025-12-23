package com.erabigroupstaffmate.uihub.components.body

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.erabigroupstaffmate.uihub.components.buttons.AppButton

@Composable
fun AppPlaceHolder(
    modifier: Modifier = Modifier,
    title: String = "",
    retryLabel: String? = null,
    subtitle: String? = null,
    onRetry: (() -> Unit)? = null
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            ),
        )

        subtitle?.let {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = it,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                ),
            )
        }
        if (onRetry != null) {
            AppButton(
                onClick = onRetry,
                label = retryLabel ?: "Try again"
            )
        }
    }
}

