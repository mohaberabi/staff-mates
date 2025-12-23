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
import com.erabigroupstaffmate.uihub.resources.already_done_day
import com.erabigroupstaffmate.uihub.resources.checkin_time
import com.erabigroupstaffmate.uihub.resources.checkout_time
import com.erabigroupstaffmate.uihub.resources.confirm
import com.erabigroupstaffmate.uihub.resources.total_worked_hours

import org.jetbrains.compose.resources.stringResource

@Composable
fun AlreadyCheckedOutBody(
    shift: ShiftLogModel? = null,
    onConfirm: () -> Unit,
) {
    val dateFormatter = rememberDateFormatter()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(Res.string.already_done_day),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(20.dp))
        Text(
            shift?.businessDate ?: "",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(20.dp))
        shift?.let {
            SimpleListItem(
                leading = stringResource(Res.string.checkin_time),
                trailing = dateFormatter.formatFromMillis(
                    millis = it.logInMillis,
                    format = AppDateFormats.TimeOnlyAmPm("en")
                ),
            )
            Spacer(Modifier.height(12.dp))
            it.logOutMillis?.let { logout ->
                SimpleListItem(
                    leading = stringResource(Res.string.checkout_time),
                    trailing = dateFormatter.formatFromMillis(
                        millis = logout,
                        format = AppDateFormats.TimeOnlyAmPm("en")
                    ),
                )
            }
            Spacer(Modifier.height(12.dp))
            SimpleListItem(
                leading = stringResource(Res.string.total_worked_hours),
                trailing = it.totalWorkedHours.toString(),
            )
        }
        AppButton(
            onClick = onConfirm,
            label = stringResource(Res.string.confirm),
        )


    }
}