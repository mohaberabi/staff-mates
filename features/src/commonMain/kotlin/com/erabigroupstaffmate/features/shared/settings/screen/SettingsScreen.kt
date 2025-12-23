package com.erabigroupstaffmate.features.shared.settings.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.erabigroupstaffmate.uihub.components.body.AppScaffold
import com.erabigroupstaffmate.uihub.components.design.MainAppBar
import com.erabigroupstaffmate.features.shared.settings.components.SettingsLitItem
import com.erabigroupstaffmate.features.shared.settings.viewmodel.SettingsActions
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.account_info
import com.erabigroupstaffmate.uihub.resources.app_language
import com.erabigroupstaffmate.uihub.resources.business_settings
import com.erabigroupstaffmate.uihub.resources.ic_account
import com.erabigroupstaffmate.uihub.resources.ic_business
import com.erabigroupstaffmate.uihub.resources.ic_lang
import com.erabigroupstaffmate.uihub.resources.ic_payment
import com.erabigroupstaffmate.uihub.resources.ic_policy
import com.erabigroupstaffmate.uihub.resources.ic_restore
import com.erabigroupstaffmate.uihub.resources.ic_sync
import com.erabigroupstaffmate.uihub.resources.priv_policy
import com.erabigroupstaffmate.uihub.resources.restore_purchase
import com.erabigroupstaffmate.uihub.resources.settings
import com.erabigroupstaffmate.uihub.resources.subscription_plan
import com.erabigroupstaffmate.uihub.resources.sync_data
import com.erabigroupstaffmate.uihub.resources.terms_condition
import org.jetbrains.compose.resources.stringResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onActions: (SettingsActions) -> Unit,
    onBackClick: () -> Unit,
) {

    AppScaffold(
        topAppBar = {
            MainAppBar(
                title = stringResource(Res.string.settings),
                onBackClick = onBackClick,
                showBackButton = true
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            SettingsLitItem(
                onClick = { onActions(SettingsActions.GoAccountInfo) },
                leading = Res.string.account_info,
                icon = Res.drawable.ic_account
            )

            SettingsLitItem(
                onClick = { onActions(SettingsActions.GoBusinessSettings) },
                leading = Res.string.business_settings,
                icon = Res.drawable.ic_business
            )
            SettingsLitItem(
                onClick = { onActions(SettingsActions.GoSyncData) },
                leading = Res.string.sync_data,
                icon = Res.drawable.ic_sync
            )

            SettingsLitItem(
                onClick = { onActions(SettingsActions.GoAppLanguage) },
                leading = Res.string.app_language,
                icon = Res.drawable.ic_lang
            )


        }
    }

}