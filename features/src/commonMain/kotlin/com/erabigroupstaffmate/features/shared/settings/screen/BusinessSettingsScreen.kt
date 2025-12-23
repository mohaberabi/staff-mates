package com.erabigroupstaffmate.features.shared.settings.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.erabigroupstaffmate.uihub.components.body.AppErrorBox
import com.erabigroupstaffmate.uihub.components.body.AppScaffold
import com.erabigroupstaffmate.uihub.components.design.AppListItem
import com.erabigroupstaffmate.uihub.components.design.AppLoader
import com.erabigroupstaffmate.uihub.components.design.MainAppBar
import com.erabigroupstaffmate.uihub.components.design.PrimaryDivider
import com.erabigroupstaffmate.features.shared.settings.viewmodel.business.BusinessSettingsState
import com.erabigroupstaffmate.features.shared.settings.viewmodel.business.BusinessSettingsViewModel
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.business_day
import com.erabigroupstaffmate.uihub.resources.business_settings
import com.erabigroupstaffmate.uihub.resources.end_day_hr
import com.erabigroupstaffmate.uihub.resources.start_day_hr
import com.erabigroupstaffmate.uihub.resources.work_hours_explain
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusinessSettingsScreen(
    viewmodel: BusinessSettingsViewModel = koinViewModel(),
    onBackClick: () -> Unit,
) {
    val state by viewmodel.state.collectAsStateWithLifecycle()
    AppScaffold(
        topAppBar = {
            MainAppBar(
                title = stringResource(Res.string.business_settings),
                onBackClick = onBackClick,
                showBackButton = true
            )
        }
    ) { padding ->
        when (state) {
            BusinessSettingsState.Error -> AppErrorBox()
            BusinessSettingsState.Loading -> AppLoader()
            is BusinessSettingsState.Loaded -> {
                BusinessSettingsLoadedBody(
                    modifier = Modifier.padding(
                        padding
                    ),
                    state = state as BusinessSettingsState.Loaded
                )
            }

        }

    }
}

@Composable
private fun BusinessSettingsLoadedBody(
    modifier: Modifier = Modifier,
    state: BusinessSettingsState.Loaded
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            AppListItem(
                leading = Res.string.business_day,
                trailing = state.businessDate,
            )
        }

        item {
            AppListItem(
                leading = Res.string.start_day_hr,
                trailing = state.startWorkHr,
            )
        }

        item {
            AppListItem(
                leading = Res.string.end_day_hr,
                trailing = state.endWorkHr,
            )
        }

        item {
            PrimaryDivider()
        }

        item {
            Text(
                stringResource(Res.string.work_hours_explain),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.Center,
                color = Color.Gray
            )
        }
    }
}