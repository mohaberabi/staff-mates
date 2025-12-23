package com.erabigroupstaffmate.features.shared.settings.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import com.erabigroupstaffmate.uihub.components.body.AppScaffold
import com.erabigroupstaffmate.uihub.components.design.MainAppBar
import com.erabigroupstaffmate.uihub.designsystem.LocalAppLanguage
import com.erabigroupstaffmate.features.shared.settings.viewmodel.language.AppLanguageViewModel
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.app_language
import com.erabigroupstaffmate.utility.localizations.AppLang
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppLanguageScreen(
    viewModel: AppLanguageViewModel = koinViewModel(),
    onGoBack: () -> Unit,
) {
    val currentLanguage = LocalAppLanguage.current
    AppScaffold(
        topAppBar = {
            MainAppBar(
                title = stringResource(Res.string.app_language),
                onBackClick = onGoBack,
                showBackButton = true
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            AppLang.entries.fastForEach { lang ->
                ListItem(
                    headlineContent = {
                        Text(lang.label, style = MaterialTheme.typography.titleLarge)
                    },
                    trailingContent = {
                        RadioButton(
                            selected = currentLanguage == lang,
                            onClick = { viewModel.changeAppLanguage(lang) }
                        )
                    },
                )
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}