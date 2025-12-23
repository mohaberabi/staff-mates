package com.erabigroupstaffmate.features.kiosk.logshift.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.erabigroupstaffmate.uihub.components.buttons.AppButton
import com.erabigroupstaffmate.uihub.components.body.AppScaffold
import com.erabigroupstaffmate.uihub.components.design.MainAppBar
import com.erabigroupstaffmate.uihub.designsystem.ErrorRed
import com.erabigroupstaffmate.uihub.designsystem.SuccessGreen
import com.erabigroupstaffmate.features.kiosk.logshift.viewmodel.LogShiftEvents
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.confirm
import com.erabigroupstaffmate.utility.constant.CHECK_IN_TITLE
import com.erabigroupstaffmate.utility.constant.CHECK_OUT_TITLE
import com.erabigroupstaffmate.utility.constant.DEFAULT_ERR_MSG

import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogShiftResultScreen(
    modifier: Modifier = Modifier,
    event: LogShiftEvents,
    onConfirm: () -> Unit,
) {
    val (message, color) = remember { event.info() }
    AppScaffold(
        topAppBar = {
            MainAppBar(
                showBackButton = false,
                color = color
            )
        }
    ) {

        Column(
            modifier = modifier
                .padding(it)
                .fillMaxSize()
                .background(color),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = message,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            AppButton(
                onClick = onConfirm,
                label = stringResource(Res.string.confirm),
                buttonColor = Color.White,
                labelColor = color,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

private fun LogShiftEvents.info() = when (this) {
    LogShiftEvents.CheckedIn -> CHECK_IN_TITLE to SuccessGreen
    LogShiftEvents.CheckedOut -> CHECK_OUT_TITLE to SuccessGreen
    LogShiftEvents.Error -> DEFAULT_ERR_MSG to ErrorRed
}