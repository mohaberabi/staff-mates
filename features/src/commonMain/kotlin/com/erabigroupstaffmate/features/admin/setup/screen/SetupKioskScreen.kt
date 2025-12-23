package com.erabigroupstaffmate.features.admin.setup.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.erabigroupstaffmate.uihub.components.buttons.AppButton
import com.erabigroupstaffmate.uihub.components.body.AppScaffold
import com.erabigroupstaffmate.uihub.components.design.MainAppBar
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.read_card
import com.erabigroupstaffmate.uihub.resources.setup
import com.erabigroupstaffmate.uihub.resources.write_card
import org.jetbrains.compose.resources.stringResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupKioskScreen(
    onGoToRead: () -> Unit,
    onGoToWrite: () -> Unit,
    onBackClick: () -> Unit,
) {

    AppScaffold(
        topAppBar = {
            MainAppBar(
                showBackButton = true,
                onBackClick = onBackClick,
                title = stringResource(Res.string.setup),
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AppButton(onClick = onGoToRead, label = stringResource(Res.string.read_card))
            AppButton(onClick = onGoToWrite, label = stringResource(Res.string.write_card))

        }

    }


}