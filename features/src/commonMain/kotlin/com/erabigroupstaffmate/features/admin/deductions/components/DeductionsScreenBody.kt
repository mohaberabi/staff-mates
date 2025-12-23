package com.erabigroupstaffmate.features.admin.deductions.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.erabigroupstaffmate.modelhub.StaffDeductType
import com.erabigroupstaffmate.modelhub.StaffModel
import com.erabigroupstaffmate.modelhub.uidmodel.DeductUiModel
import com.erabigroupstaffmate.uihub.components.common.DateChangerRow
import com.erabigroupstaffmate.uihub.components.sheets.AppBottomSheet
import com.erabigroupstaffmate.uihub.components.sheets.AppDatePicker
import com.erabigroupstaffmate.uihub.components.textfields.PrimaryTextField
import com.erabigroupstaffmate.uihub.components.design.SimpleListItem
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.deduct_amount
import com.erabigroupstaffmate.uihub.resources.deduct_time
import com.erabigroupstaffmate.uihub.resources.search_staff
import org.jetbrains.compose.resources.stringResource

private typealias StaffId = String

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeductionsScreenBody(
    modifier: Modifier = Modifier,
    deductType: StaffDeductType,
    deductions: Map<StaffId, List<DeductUiModel>>,
    staff: Map<StaffId, StaffModel>,
    selectedDate: String,
    showDatePicker: Boolean,
    onToggleDate: () -> Unit,
    onSearchChanged: (String) -> Unit,
    onDateChanged: (Long) -> Unit,
    searchQuery: String,
    selectedDeduct: DeductUiModel? = null,
    onDeductSelected: (DeductUiModel?) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        stickyHeader {
            DateChangerRow(
                selectedDate = selectedDate,
                onToggleDate = onToggleDate
            )
        }

        stickyHeader {
            Box(
                Modifier.background(MaterialTheme.colorScheme.background)
                    .fillMaxWidth()
            ) {
                PrimaryTextField(
                    label = stringResource(Res.string.search_staff),
                    onChanged = onSearchChanged,
                    value = searchQuery,
                )
            }
        }
        items(
            items = deductions.keys.toList(),
        ) { staffId ->
            val staff = staff[staffId] ?: return@items
            val deductions = deductions[staffId].orEmpty()

            DeductCard(
                deductType = deductType,
                staff = staff,
                deductions = deductions,
                onDeductClick = {
                    onDeductSelected(it)
                },
            )

        }
    }
    if (showDatePicker) {
        AppDatePicker(
            onDismiss = { onToggleDate() },
            onSelected = { onDateChanged(it) }
        )
    }
    selectedDeduct?.let {
        AppBottomSheet(
            onDismissRequest = { onDeductSelected(null) },
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                val fullName = staff[it.staffId]?.fullName ?: ""
                Text("Deduct for $fullName", style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(8.dp))
                SimpleListItem(
                    leading = stringResource(Res.string.deduct_amount),
                    trailing = "${it.amount}"
                )
                SimpleListItem(
                    leading = stringResource(Res.string.deduct_time),
                    trailing = it.deductFormattedDate
                )
                Spacer(Modifier.height(8.dp))
                Text(it.reason, style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}
