package com.erabigroupstaffmate.features.kiosk.logshift.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.erabigroupstaffmate.erabitime.domain.AppDateFormats
import com.erabigroupstaffmate.modelhub.ShiftLogModel
import com.erabigroupstaffmate.uihub.components.buttons.AppButton
import com.erabigroupstaffmate.uihub.components.design.SimpleListItem
import com.erabigroupstaffmate.uihub.components.inject.rememberDateFormatter
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.checkin_time
import com.erabigroupstaffmate.uihub.resources.end_working_day
import com.erabigroupstaffmate.uihub.resources.ready_to_end_day

import org.jetbrains.compose.resources.stringResource


@Composable
fun NeedsToLogoutBody(
    shift: ShiftLogModel? = null,
    onCheckOut: (ShiftLogModel) -> Unit,
    logging: Boolean,
) {
    val dateFormatter = rememberDateFormatter()
    shift?.let {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                stringResource(Res.string.ready_to_end_day),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(12.dp))
            Text(
                it.businessDate,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

            SimpleListItem(
                leading = stringResource(Res.string.checkin_time),
                trailing = dateFormatter.formatFromMillis(
                    millis = it.logInMillis,
                    format = AppDateFormats.TimeOnlyAmPm("en")
                ),
            )

            AppButton(
                loading = logging,
                onClick = { onCheckOut(it) },
                label = stringResource(Res.string.end_working_day),
            )
        }

    }
}