package com.erabigroupstaffmate.features.admin.staffdoc.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.erabigroupstaffmate.features.admin.staffdoc.viewmodel.StaffDocActions
import com.erabigroupstaffmate.features.admin.staffdoc.viewmodel.StaffDocViewModel
import com.erabigroupstaffmate.papergen.domain.DeductionsFileGenerator
import com.erabigroupstaffmate.papergen.domain.PayrollFileGenerator
import com.erabigroupstaffmate.papergen.domain.rememberDeductionsGenerator
import com.erabigroupstaffmate.papergen.domain.rememberPayrollGenerator
import com.erabigroupstaffmate.uihub.components.common.StaffClickActions
import com.erabigroupstaffmate.uihub.components.design.AppListItem
import com.erabigroupstaffmate.uihub.components.dialogs.FullScreenLoaderDialog
import com.erabigroupstaffmate.uihub.components.sheets.AppBottomSheet
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.add_borrow
import com.erabigroupstaffmate.uihub.resources.add_deduct
import com.erabigroupstaffmate.uihub.resources.borrow
import com.erabigroupstaffmate.uihub.resources.borrowings
import com.erabigroupstaffmate.uihub.resources.deduct
import com.erabigroupstaffmate.uihub.resources.payroll
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StaffDocSheet(
    deductionsFileGenerator: DeductionsFileGenerator = rememberDeductionsGenerator(),
    payrollGenerator: PayrollFileGenerator = rememberPayrollGenerator(),
    viewModel: StaffDocViewModel = koinViewModel(
        parameters = {
            parametersOf(
                payrollGenerator,
                deductionsFileGenerator
            )
        }
    ),
    onDismiss: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    AppBottomSheet(
        onDismissRequest = onDismiss,
    ) {
        AppListItem(
            icon = Res.drawable.deduct,
            leading = Res.string.add_deduct,
            onClick = {
                viewModel.onAction(StaffDocActions.GenerateDeductions)

            },
        )
        AppListItem(
            leading = Res.string.borrowings,
            icon = Res.drawable.borrow,
            onClick = {
                viewModel.onAction(StaffDocActions.GenerateBorrowing)
            },
        )
        AppListItem(
            leading = Res.string.payroll,
            icon = Res.drawable.payroll,
            onClick = {
                viewModel.onAction(StaffDocActions.GeneratePayroll)
            },
        )
    }

    if (state.isGenerating) {
        FullScreenLoaderDialog()
    }
}