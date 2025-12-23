package com.erabigroupstaffmate.features.shared.auth.viewmodel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.erabigroupstaffmate.core.domain.usecase.authkeys.AuthStatus
import com.erabigroupstaffmate.uihub.components.common.numpad.PasswordNumPad
import com.erabigroupstaffmate.uihub.components.sheets.AppBottomSheet
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.please_auth

import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthBottomSheet(
    viewmodel: AuthViewModel = koinViewModel(),
    onDismiss: () -> Unit,
) {
    val state by viewmodel.state.collectAsStateWithLifecycle()
    LaunchedEffect(
        state.status,
    ) {
        if (state.status == AuthStatus.Authed) {
            onDismiss()
        }
    }
    AppBottomSheet(
        onDismissRequest = onDismiss,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                stringResource(Res.string.please_auth),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(20.dp))
            PasswordNumPad(
                size = 5,
                onSubmit = {
                    viewmodel.codeChanged(it)
                    viewmodel.authorize()
                },
                isError = state.status == AuthStatus.NonAuthed,
                modifier = Modifier.wrapContentHeight()
            )
        }
    }
}