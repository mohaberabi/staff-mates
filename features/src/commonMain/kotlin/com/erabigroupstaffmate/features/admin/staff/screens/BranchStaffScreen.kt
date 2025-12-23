package com.erabigroupstaffmate.features.admin.staff.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.erabigroupstaffmate.modelhub.StaffModel
import com.erabigroupstaffmate.uihub.components.body.AppPlaceHolder
import com.erabigroupstaffmate.uihub.components.body.AppScaffold
import com.erabigroupstaffmate.uihub.components.body.FocuseAwareCompose
import com.erabigroupstaffmate.uihub.components.design.MainAppBar
import com.erabigroupstaffmate.uihub.components.textfields.PrimaryTextField
import com.erabigroupstaffmate.uihub.components.common.StaffCard
import com.erabigroupstaffmate.uihub.components.common.StaffClickActions
import com.erabigroupstaffmate.features.admin.staff.viewmodel.BranchStaffViewModel
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.branch_staff
import com.erabigroupstaffmate.uihub.resources.no_staff_found
import com.erabigroupstaffmate.uihub.resources.search_staff
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BranchStaffScreen(
    onStaffAction: (StaffClickActions) -> Unit,
    viewModel: BranchStaffViewModel = koinViewModel(),
    onBackClick: () -> Unit
) {

    val query by viewModel.queryState.collectAsStateWithLifecycle()
    val staff by viewModel.staff.collectAsStateWithLifecycle()

    FocuseAwareCompose {
        AppScaffold(
            topAppBar = {
                MainAppBar(
                    title = stringResource(Res.string.branch_staff),
                    onBackClick = onBackClick,
                    showBackButton = true
                )
            }
        ) { padding ->
            StaffScreenBody(
                modifier = Modifier.fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                staff = staff,
                query = query,
                onSearch = viewModel::queryChanged,
                onStaffAction = onStaffAction
            )
        }
    }


}


@Composable
private fun StaffScreenBody(
    modifier: Modifier = Modifier,
    query: String,
    onSearch: (String) -> Unit,
    staff: List<StaffModel>,
    onStaffAction: (StaffClickActions) -> Unit,
) {
    Box(
        modifier = modifier
    ) {
        LazyColumn {
            item {
                PrimaryTextField(
                    label = stringResource(Res.string.search_staff),
                    onChanged = onSearch,
                    value = query,
                    placeHolder = "search by staff name or id "
                )
            }
            if (staff.isEmpty()) {
                item {
                    AppPlaceHolder(title = stringResource(Res.string.no_staff_found))
                }
            } else {
                items(
                    staff
                ) {
                    StaffCard(
                        onActions = onStaffAction,
                        modifier = Modifier.padding(vertical = 8.dp),
                        staff = it
                    )
                }
            }

        }

    }


}