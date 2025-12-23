package com.erabigroupstaffmate.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.erabigroupstaffmate.core.domain.AuthEventController
import com.erabigroupstaffmate.uihub.components.webview.WebViewScreen
import com.erabigroupstaffmate.features.shared.auth.viewmodel.AuthBottomSheet
import com.erabigroupstaffmate.modelhub.AppMode
import com.erabigroupstaffmate.parser.Parser
import com.erabigroupstaffmate.uihub.components.compose.EventCollector
import com.erabigroupstaffmate.navigation.AuthRoute
import com.erabigroupstaffmate.navigation.compose.accountInfo
import com.erabigroupstaffmate.navigation.compose.appLang
import com.erabigroupstaffmate.navigation.compose.businessSettings
import com.erabigroupstaffmate.navigation.compose.chooseAppMode
import com.erabigroupstaffmate.navigation.compose.confirmStaff
import com.erabigroupstaffmate.navigation.compose.emptyRoute
import com.erabigroupstaffmate.navigation.compose.goAfterAppMode
import com.erabigroupstaffmate.navigation.compose.goChooseAppMode
import com.erabigroupstaffmate.navigation.compose.goLogShift
import com.erabigroupstaffmate.navigation.compose.logHistoryRoute
import com.erabigroupstaffmate.navigation.compose.logResult
import com.erabigroupstaffmate.navigation.compose.logShift
import com.erabigroupstaffmate.navigation.compose.login
import com.erabigroupstaffmate.navigation.compose.sharedSettings
import com.erabigroupstaffmate.navigation.compose.syncData
import com.erabigroupstaffmate.navigation.compose.syncFromServer
import com.erabigroupstaffmate.navigation.compose.syncToServer
import com.erabigroupstaffmate.navigation.routes.WebviewRoute
import com.erabigroupstaffmate.navigation.utils.toRoute
import com.erabigroupstaffmate.utility.constant.APP_WB_SITE
import kotlinx.coroutines.delay
import org.koin.compose.koinInject


internal val defaultDialogProperties = DialogProperties(
    dismissOnBackPress = false,
    dismissOnClickOutside = false
)

@Composable
fun MainNavGraph(
    startDestination: Any,
) {

    val navController = rememberNavController()
    val parser = koinInject<Parser>()

    val authController = koinInject<AuthEventController>()

    fun pop() = navController.popBackStack()

    EventCollector(
        authController.collectRequests(),
    ) { authKeys ->

        navController.navigate(AuthRoute(authKeys.type))
    }


    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        sharedSettings(
            onBackClick = ::pop,
            onActions = { navController.navigate(it.toRoute()) },
        )

        logShift(navController = navController)
        syncData(navController = navController)
        syncFromServer(onBack = ::pop)
        syncToServer(onBack = ::pop)
        accountInfo(onBackClick = ::pop)
        logResult(onBack = ::pop)
        appLang(onGoBack = ::pop)
        logHistoryRoute(onBack = ::pop)
        emptyRoute()
        confirmStaff(
            onBack = ::pop,
            onConfirm = {
                val staffJson = parser.toJson(it)
                navController.goLogShift(staffJson = staffJson)
            }
        )
        businessSettings(onGoBack = ::pop)

        adminNavigation(
            parser = parser,
            navController = navController,
        )


        kioskNavigation(
            navController = navController,
        )

        dialog<AuthRoute> {
            AuthBottomSheet(
                onDismiss = ::pop
            )
        }
        composable<WebviewRoute>() {
            WebViewScreen(
                initialUrl = it.toRoute<WebviewRoute>().url,
                onBack = ::pop
            )
        }
        login(
            onLoggedIn = navController::goChooseAppMode
        )
        chooseAppMode(
            onSynced = { mode ->
                navController.goAfterAppMode(mode = mode)
            }
        )

    }
}


