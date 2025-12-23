package com.erabigroupstaffmate.uihub.components.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.erabigroupstaffmate.modelhub.PayrollSummaryModel
import com.erabigroupstaffmate.uihub.components.design.PrimaryDivider
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.base_salary
import com.erabigroupstaffmate.uihub.resources.borrowings
import com.erabigroupstaffmate.uihub.resources.deductions
import com.erabigroupstaffmate.uihub.resources.earn_per_hr
import com.erabigroupstaffmate.uihub.resources.growth_earn
import com.erabigroupstaffmate.uihub.resources.hrs_per_shift
import com.erabigroupstaffmate.uihub.resources.net_earn
import com.erabigroupstaffmate.uihub.resources.net_hr
import com.erabigroupstaffmate.uihub.resources.off_days
import com.erabigroupstaffmate.uihub.resources.work_hrs
import com.erabigroupstaffmate.utility.math.format
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun PayrollSummaryCard(
    payroll: PayrollSummaryModel,
    onGenerateDocs: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        StaffCard(
            staff = payroll.staff,
            isClickable = false
        )
        StaffListItem(
            title = Res.string.net_earn,
            value = payroll.netSalary.format(),
            titleStyle = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            valueStyle = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
        )
        StaffListItem(
            title = Res.string.growth_earn,
            value = payroll.growthSalary.format(),
            titleStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
            valueStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            paddingValues = PaddingValues(horizontal = 6.dp)
        )
        PrimaryDivider()
        StaffListItem(
            title = Res.string.base_salary,
            value = payroll.staff.baseSalary.format(),
        )

        StaffListItem(
            title = Res.string.hrs_per_shift,
            value = payroll.staff.shiftHrs.toString()
        )

        StaffListItem(
            title = Res.string.off_days,
            value = payroll.staff.vacationDays.toString()
        )

        PrimaryDivider()

        StaffListItem(
            title = Res.string.deductions,
            value = payroll.totalDeductions.format()
        )
        StaffListItem(
            title = Res.string.borrowings,
            value = payroll.totalBorrowings.format(),
        )

        PrimaryDivider()

        StaffListItem(
            title = Res.string.work_hrs,
            value = payroll.ttlWorkHrs.format(),
        )

        StaffListItem(
            title = Res.string.net_hr,
            value = payroll.ttlWorkHrsWithAllowance.format(),
        )
        StaffListItem(
            title = Res.string.earn_per_hr,
            value = payroll.earnPerHr.format()
        )
        TextButton(
            onClick = onGenerateDocs,
        ) {
            Text("Generate documents")
        }
    }
}

@Composable
fun StaffListItem(
    title: StringResource,
    value: String,
    titleStyle: TextStyle = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
    valueStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.SpaceBetween,
    paddingValues: PaddingValues = PaddingValues(horizontal = 6.dp, vertical = 8.dp)
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = horizontalArrangement,
    ) {

        Text(
            stringResource(title),
            style = titleStyle,
        )
        Text(
            value,
            style = valueStyle,
        )
    }

}