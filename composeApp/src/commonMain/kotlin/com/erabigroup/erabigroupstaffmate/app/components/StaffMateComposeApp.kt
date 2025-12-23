package com.erabigroup.erabigroupstaffmate.app.components


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.annotation.ExperimentalCoilApi
import com.erabigroup.erabigroupstaffmate.app.viewmodel.main.MainAppState
import com.erabigroup.erabigroupstaffmate.app.viewmodel.main.MainViewModel
import com.erabigroupstaffmate.uihub.components.dialogs.FullScreenLoader
import com.erabigroupstaffmate.uihub.designsystem.ErabigroupStaffMateTheme
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalCoilApi::class)
@Composable
fun StaffMateComposeApp() {

    val viewmodel = koinViewModel<MainViewModel>()
    val state by viewmodel.state.collectAsStateWithLifecycle()

    val startRoute by viewmodel.startRoute.collectAsStateWithLifecycle()
    when (val state = state) {
        is MainAppState.Initialized -> ErabigroupStaffMateTheme(
            language = state.lang
        ) {
            startRoute?.let {
                StaffMateComposedAppBody(
                    startRoute = it,
                )
            }
        }

        MainAppState.Initializing -> FullScreenLoader()

    }


}

