package com.erabigroupstaffmate.features.kiosk.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.erabigroupstaffmate.uihub.components.body.AppScaffold
import com.erabigroupstaffmate.uihub.components.common.AppPrimaryCard
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.app_name
import com.erabigroupstaffmate.uihub.resources.attendance
import com.erabigroupstaffmate.uihub.resources.ic_settings
import com.erabigroupstaffmate.uihub.resources.manual_attend
import com.erabigroupstaffmate.uihub.resources.settings

import org.jetbrains.compose.resources.stringResource


@Composable
fun KioskHomeScreen(
    onNavAction: (KioskHomeNavAction) -> Unit,
) {
    AppScaffold { padding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                stringResource(Res.string.app_name),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            AppPrimaryCard(
                modifier = Modifier.fillMaxWidth(),
                innerModifier = Modifier.wrapContentHeight().fillMaxWidth(),
                onClick = {
                    onNavAction(KioskHomeNavAction.Attendance)

                },
                title = Res.string.attendance,
                icon = Res.drawable.manual_attend,
            )

            AppPrimaryCard(
                modifier = Modifier.fillMaxWidth(),
                innerModifier = Modifier.wrapContentHeight().fillMaxWidth(),
                onClick = {
                    onNavAction(KioskHomeNavAction.Settings)

                },
                title = Res.string.settings,
                icon = Res.drawable.ic_settings,
            )
        }
    }
}

