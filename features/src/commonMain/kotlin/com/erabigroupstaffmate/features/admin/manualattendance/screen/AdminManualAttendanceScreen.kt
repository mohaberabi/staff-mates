package com.erabigroupstaffmate.features.admin.manualattendance.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.erabigroupstaffmate.uihub.components.body.AppScaffold
import com.erabigroupstaffmate.uihub.components.common.numpad.SimpleNumPad
import com.erabigroupstaffmate.uihub.components.design.MainAppBar
import com.erabigroupstaffmate.uihub.designsystem.ErabigroupStaffMateTheme
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.manual_attendance
import org.jetbrains.compose.resources.stringResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminManualAttendanceScreen(
    onDone: (staffId: String) -> Unit,
    onBackClick: () -> Unit,
) {
    ErabigroupStaffMateTheme {
        AppScaffold(
            topAppBar = {
                MainAppBar(
                    title = stringResource(Res.string.manual_attendance),
                    showBackButton = true,
                    onBackClick = onBackClick,
                )
            }
        ) { padding ->

            SimpleNumPad(
                size = 11,
                onSubmit = { onDone(it) },
                modifier = Modifier.fillMaxSize()
            )
        }
    }

}
