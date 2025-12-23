package com.erabigroupstaffmate.uihub.components.body

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.erabigroupstaffmate.uihub.components.buttons.AppButton
import com.erabigroupstaffmate.uihub.designsystem.ErrorRed


@Composable
fun AppErrorBox(
    modifier: Modifier = Modifier.fillMaxSize()
        .padding(8.dp),
    onRetry: () -> Unit = {},
    errorTitle: String = "",
    errorSubtitle: String = "",
    loading: Boolean = false
) {
    AppErrorBox(
        modifier = modifier,
        onRetry = onRetry,
        title = errorTitle,
        subtitle = errorSubtitle,
        loading = loading
    )

}


@Composable
private fun AppErrorBox(
    modifier: Modifier = Modifier,
    title: String = "",
    subtitle: String = "",
    onRetry: () -> Unit = {},
    buttonLabel: String = "Try again",
    loading: Boolean = false
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge.copy(
                color = ErrorRed,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(
            modifier = Modifier.height(8.dp),
        )
        Text(
            text = subtitle,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray)
        )
        Spacer(
            modifier = Modifier.height(8.dp),
        )
        AppButton(
            label = buttonLabel,
            onClick = onRetry,
            loading = loading,
            loadingColor = MaterialTheme.colorScheme.onPrimary,
            buttonColor = ErrorRed,
            labelColor = MaterialTheme.colorScheme.onPrimary
        )
    }
}
