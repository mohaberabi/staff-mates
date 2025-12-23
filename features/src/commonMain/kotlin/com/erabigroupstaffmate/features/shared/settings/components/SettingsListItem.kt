package com.erabigroupstaffmate.features.shared.settings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.ic_forward

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource


@Composable
fun SettingsLitItem(
    icon: DrawableResource? = null,
    leading: StringResource,
    showArrow: Boolean = true,
    onClick: () -> Unit,
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
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        headlineContent = {
            Text(
                stringResource(leading),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
        },
        trailingContent = {
            if (showArrow) {
                Icon(
                    vectorResource(Res.drawable.ic_forward),
                    "",
                    tint = Color.Gray
                )
            }
        },
    )

}
