package com.erabigroupstaffmate.features.admin.home.screen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.erabigroupstaffmate.uihub.components.body.AppScaffold
import com.erabigroupstaffmate.uihub.components.common.AppPrimaryCard
import com.erabigroupstaffmate.uihub.components.compose.EventCollector
import com.erabigroupstaffmate.features.admin.home.viewmdoel.AdminHomeViewModel
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.app_name
import com.erabigroupstaffmate.uihub.resources.what_to_do
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun AdminHomeScreen(
    viewmodel: AdminHomeViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
    onNavigate: (AdminHomeNavActions) -> Unit = {},
) {
    EventCollector(
        viewmodel.events,
    ) { navAction ->
        onNavigate(navAction)
    }

    AppScaffold { padding ->
        LazyVerticalGrid(
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.Center,
            columns = GridCells.Fixed(2),
            modifier = modifier.fillMaxSize()
                .padding(padding)
                .padding(12.dp)
        ) {
            item(
                span = {
                    GridItemSpan(2)
                },
            ) {
                Text(
                    stringResource(Res.string.app_name),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )
            }

            item(
                span = {
                    GridItemSpan(2)
                },
            ) {
                Text(
                    stringResource(Res.string.what_to_do),
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
                    modifier = Modifier.padding(vertical = 4.dp),
                )
            }
            items(
                adminHomeActions,
            ) { action ->

                AppPrimaryCard(
                    innerModifier = Modifier.fillMaxSize(),
                    onClick = {
                        action.authRole?.let {
                            viewmodel.requestAuth(it)
                        } ?: run {
                            onNavigate(action.navAction)
                        }
                    },
                    icon = action.icon,
                    title = action.title,
                )
            }
        }
    }
}

