package com.erabigroupstaffmate.features.admin.deductions.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.erabigroupstaffmate.modelhub.StaffDeductType
import com.erabigroupstaffmate.uihub.components.body.AppScaffold
import com.erabigroupstaffmate.uihub.components.body.FocuseAwareCompose
import com.erabigroupstaffmate.uihub.components.design.MainAppBar
import com.erabigroupstaffmate.features.admin.deductions.components.DeductionsScreenBody
import com.erabigroupstaffmate.features.admin.deductions.viewmodel.DeductionsViewModel
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.borrowings
import com.erabigroupstaffmate.uihub.resources.deductions
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeductionsScreen(
    type: StaffDeductType,
    viewModel: DeductionsViewModel = koinViewModel(parameters = {
        parametersOf(type)
    }),
    onBackClick: () -> Unit
) {

    val deductions by viewModel.deductions.collectAsStateWithLifecycle()
    val selectedDate by viewModel.selectedDate.collectAsStateWithLifecycle()
    val staffMap by viewModel.staff.collectAsStateWithLifecycle()
    val showDatePicker by viewModel.showDatePicker.collectAsStateWithLifecycle()
    val query by viewModel.searchQuery.collectAsStateWithLifecycle()
    val selectedDeduct by viewModel.selectedDeduct.collectAsStateWithLifecycle()
    FocuseAwareCompose {
        AppScaffold(
            topAppBar = {
                MainAppBar(
                    title = stringResource(type.label()),
                    onBackClick = onBackClick,
                    showBackButton = true
                )
            }
        ) { padding ->

            DeductionsScreenBody(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                deductions = deductions,
                staff = staffMap,
                selectedDate = "${selectedDate.monthNumber} - ${selectedDate.year}",
                showDatePicker = showDatePicker,
                onToggleDate = { viewModel.toggleDatePicker() },
                onSearchChanged = { viewModel.queryChanged(it) },
                searchQuery = query,
                onDateChanged = { viewModel.dateChanged(it) },
                onDeductSelected = { viewModel.toggleDeduct(it) },
                selectedDeduct = selectedDeduct,
                deductType = type
            )
        }
    }

}


private fun StaffDeductType.label() = when (this) {
    StaffDeductType.Deduct -> Res.string.deductions
    StaffDeductType.Borrow -> Res.string.borrowings
}

