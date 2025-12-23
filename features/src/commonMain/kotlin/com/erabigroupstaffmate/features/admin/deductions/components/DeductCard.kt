package com.erabigroupstaffmate.features.admin.deductions.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.erabigroupstaffmate.modelhub.StaffDeductType
import com.erabigroupstaffmate.modelhub.StaffModel
import com.erabigroupstaffmate.modelhub.uidmodel.DeductUiModel
import com.erabigroupstaffmate.uihub.components.common.SimpleStaffCard
import com.erabigroupstaffmate.uihub.components.dropdown.ExpansionTile
import com.erabigroupstaffmate.uihub.components.design.SimpleListItem
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.borrowings
import com.erabigroupstaffmate.uihub.resources.deductions
import org.jetbrains.compose.resources.stringResource


@Composable
fun DeductCard(
    deductType: StaffDeductType,
    staff: StaffModel,
    deductions: List<DeductUiModel>,
    onDeductClick: (DeductUiModel) -> Unit,
) {

    val total = remember(
        deductions
    ) {
        deductions.sumOf { it.amount }
    }
    val deductString = stringResource(
        when (deductType) {
            StaffDeductType.Deduct -> Res.string.deductions
            StaffDeductType.Borrow -> Res.string.borrowings
        }
    )
    ExpansionTile(
        modifier = Modifier.padding(bottom = 8.dp),
        title = {
            SimpleStaffCard(
                modifier = Modifier.weight(1f),
                staffName = staff.fullName,
                image = staff.profilePicUrl,
                subtitle = "$deductString $total"
            )
        },
        expandedContent = {
            Column {
                deductions.forEach { deduct ->
                    SimpleListItem(
                        onClick = { onDeductClick(deduct) },
                        modifier = Modifier.padding(bottom = 8.dp),
                        leading = deduct.amount.toString(),
                        trailing = deduct.deductFormattedDate
                    )
                }
            }

        }
    )
}