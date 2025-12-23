package com.erabigroupstaffmate.features.shared.settings.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.erabigroupstaffmate.uihub.components.body.AppScaffold
import com.erabigroupstaffmate.uihub.components.body.FocuseAwareCompose
import com.erabigroupstaffmate.uihub.components.buttons.AppButton
import com.erabigroupstaffmate.uihub.components.design.MainAppBar
import com.erabigroupstaffmate.uihub.components.textfields.PrimaryTextField
import com.erabigroupstaffmate.uihub.designsystem.LocalSnackBarController
import com.erabigroupstaffmate.features.shared.settings.viewmodel.account.AccountInfoState
import com.erabigroupstaffmate.features.shared.settings.viewmodel.account.AccountInfoViewModel
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.account_info
import com.erabigroupstaffmate.uihub.resources.branch
import com.erabigroupstaffmate.uihub.resources.chain
import com.erabigroupstaffmate.uihub.resources.email_address
import com.erabigroupstaffmate.uihub.resources.save_settings
import com.erabigroupstaffmate.uihub.resources.settings
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountInfoScreen(
    viewModel: AccountInfoViewModel = koinViewModel(),
    onBackClick: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()


    val scrollState = rememberScrollState()
    FocuseAwareCompose {
        AppScaffold(
            modifier = Modifier.imePadding(),
            topAppBar = {
                MainAppBar(
                    showBackButton = true,
                    onBackClick = onBackClick,
                    title = stringResource(Res.string.account_info)
                )
            }
        ) {
            SettingsScreenBody(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(it)
                    .padding(16.dp),
                state = state,
            )

        }
    }


}


@Composable
private fun SettingsScreenBody(
    modifier: Modifier = Modifier,
    state: AccountInfoState,
) {
    Column(
        modifier = modifier
    ) {
        PrimaryTextField(
            value = state.email,
            isReadOnly = true,
            label = stringResource(Res.string.email_address)
        )
        PrimaryTextField(
            value = state.chain,
            isReadOnly = true,
            label = stringResource(Res.string.chain)
        )

        PrimaryTextField(
            value = state.branch,
            isReadOnly = true,
            label = stringResource(Res.string.branch)
        )
        PrimaryTextField(
            value = state.mode.name,
            isReadOnly = true,
            label = stringResource(Res.string.branch)
        )
    }
}

