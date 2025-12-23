package com.erabigroupstaffmate.features.admin.deductions.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.erabigroupstaffmate.modelhub.uidmodel.ShiftLogUiModel
import com.erabigroupstaffmate.uihub.components.common.SimpleStaffCard
import com.erabigroupstaffmate.uihub.components.design.SimpleListItem
import com.erabigroupstaffmate.uihub.components.dropdown.ExpansionTile
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.checkin_time
import com.erabigroupstaffmate.uihub.resources.checkout_time
import com.erabigroupstaffmate.uihub.resources.total_worked_hours
import org.jetbrains.compose.resources.stringResource


@Composable
fun LogHistoryListItem(
    log: ShiftLogUiModel,
) {

    ExpansionTile(
        modifier = Modifier.padding(bottom = 8.dp),
        title = {
            SimpleStaffCard(
                modifier = Modifier.weight(1f),
                staffName = log.staffFullName,
                subtitle = "${stringResource(Res.string.total_worked_hours)} ${log.totalHours}"
            )
        },
        expandedContent = {
            Column {
                SimpleListItem(
                    modifier = Modifier.padding(bottom = 8.dp),
                    leading = stringResource(Res.string.checkin_time),
                    trailing = log.checkInTime
                )
                if (log.checkOutTime.isNotEmpty()) {
                    Spacer(Modifier.height(8.dp))
                    SimpleListItem(
                        modifier = Modifier.padding(bottom = 8.dp),
                        leading = stringResource(Res.string.checkout_time),
                        trailing = log.checkOutTime
                    )
                    Spacer(Modifier.height(8.dp))
                    SimpleListItem(
                        modifier = Modifier.padding(bottom = 8.dp),
                        leading = stringResource(Res.string.total_worked_hours),
                        trailing = log.totalHours
                    )
                }

            }

        }
    )
}