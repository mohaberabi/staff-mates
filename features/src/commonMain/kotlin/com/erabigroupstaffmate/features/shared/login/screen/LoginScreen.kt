package com.erabigroupstaffmate.features.shared.login.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.erabigroupstaffmate.features.shared.login.viewmodel.LoginEvents
import com.erabigroupstaffmate.features.shared.login.viewmodel.LoginViewModel
import com.erabigroupstaffmate.uihub.components.body.AppScaffold
import com.erabigroupstaffmate.uihub.components.body.FocuseAwareCompose
import com.erabigroupstaffmate.uihub.components.buttons.AppButton
import com.erabigroupstaffmate.uihub.components.compose.EventCollector
import com.erabigroupstaffmate.uihub.components.snackbar.SnackbarMessage
import com.erabigroupstaffmate.uihub.components.textfields.PasswordTextField
import com.erabigroupstaffmate.uihub.components.textfields.PrimaryTextField
import com.erabigroupstaffmate.uihub.designsystem.LocalSnackBarController
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.branch
import com.erabigroupstaffmate.uihub.resources.chain
import com.erabigroupstaffmate.uihub.resources.logo
import com.erabigroupstaffmate.uihub.resources.pro_logo
import com.erabigroupstaffmate.uihub.utils.extensions.compose.modifier.imeInsetsPadding
import org.jetbrains.compose.resources.imageResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    onLoggedIn: () -> Unit,
) {


    val snackBarController = LocalSnackBarController.current

    val state by viewModel.state.collectAsStateWithLifecycle()
    EventCollector(
        viewModel.events,
    ) { event ->

        when (event) {
            is LoginEvents.Error -> snackBarController.send(SnackbarMessage.Error(stringResource = event.error))
            LoginEvents.LoggedIn -> onLoggedIn()
        }
    }
    FocuseAwareCompose {
        AppScaffold { padding ->
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(padding)
                    .padding(12.dp)
                    .imeInsetsPadding()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    imageResource(Res.drawable.logo),
                    "",
                    modifier = Modifier.size(136.dp)
                )
                Text(
                    "Login to your erabigroup account",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
                )

                Text(
                    "please use your erabigroup registration info to be able to use the app",
                    style = MaterialTheme.typography.bodyMedium
                )


                PrimaryTextField(
                    value = state.email,
                    onChanged = { viewModel.emailChanged(it) },
                    label = "E-mail"
                )

                PasswordTextField(
                    value = state.password,
                    onChange = { viewModel.passwordChanged(it) }
                )

                PrimaryTextField(
                    value = state.chain,
                    onChanged = { viewModel.chainChanged(it) },
                    label = stringResource(Res.string.chain)
                )
                PrimaryTextField(
                    value = state.branch,
                    onChanged = { viewModel.branchChanged(it) },
                    label = stringResource(Res.string.branch)
                )

                AppButton(
                    onClick = { viewModel.login() },
                    label = "Login",
                    enabled = state.canLogin,
                    loading = state.loading
                )
            }
        }
    }

}