package com.erabigroupstaffmate.uihub.components.design

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource


@Composable
fun AppListItem(
    icon: DrawableResource? = null,
    leading: StringResource,
    trailing: String? = null,
    onClick: () -> Unit = {},
) {
    ListItem(
        colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.background),
        modifier = Modifier.clickable {
            onClick()
        },
        leadingContent = {
            icon?.let {
                Icon(
                    imageVector = vectorResource(icon),
                    "",
                    modifier = Modifier.size(36.dp)
                )
            }
        },
        headlineContent = {
            Text(
                stringResource(leading),
            )
        },
        trailingContent = {
            trailing?.let {
                Text(it)
            }
        },
    )

}

@Composable
fun SimpleListItem(
    modifier: Modifier = Modifier,
    leading: String,
    trailing: String = "",
    onClick: () -> Unit = {},
) {
    ListItem(
        modifier = modifier.clickable(onClick = onClick).padding(8.dp),
        headlineContent = {
            Text(
                leading,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
        },
        trailingContent = {
            Text(
                trailing,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
            )
        },
    )
}

@Composable
fun SimpleListItem(
    leading: @Composable () -> Unit,
    trailing: String = "",
    onClick: () -> Unit = {},
) {
    ListItem(
        colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.background),
        modifier = Modifier.clickable(onClick = onClick),
        headlineContent = {
            leading()
        },
        trailingContent = {
            Text(
                trailing,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
            )
        },
    )
}