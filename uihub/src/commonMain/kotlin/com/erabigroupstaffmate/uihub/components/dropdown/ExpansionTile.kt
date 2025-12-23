package com.erabigroupstaffmate.uihub.components.dropdown

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.ic_arrow_down
import com.erabigroupstaffmate.uihub.resources.ic_arrow_up
import org.jetbrains.compose.resources.vectorResource


@Composable
fun ExpansionTile(
    modifier: Modifier = Modifier,
    title: @Composable RowScope.() -> Unit,
    expandedContent: @Composable () -> Unit
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            title()
            IconButton(
                onClick = {
                    isExpanded = !isExpanded
                },
            ) {
                Icon(
                    vectorResource(
                        if (isExpanded) Res.drawable.ic_arrow_up else Res.drawable.ic_arrow_down
                    ),
                    "",
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        }
        AnimatedVisibility(
            visible = isExpanded,
        ) {
            expandedContent()
        }
    }

}