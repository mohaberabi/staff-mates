package com.erabigroupstaffmate.uihub.components.webview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.erabigroupstaffmate.uihub.components.body.AppScaffold
import com.erabigroupstaffmate.uihub.components.design.MainAppBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebViewScreen(
    initialUrl: String,
    onBack: () -> Unit
) {
    AppScaffold(
        topAppBar = {
            MainAppBar(
                onBackClick = onBack,
                showBackButton = true
            )
        }
    ) { padding ->
        CustomWebView(
            modifier = Modifier.fillMaxSize().padding(padding),
            initialUrl = initialUrl,
            onLoaded = { _, _ -> },
            onLoading = { _, _ -> },
            isLoading = {}
        )

    }
}

