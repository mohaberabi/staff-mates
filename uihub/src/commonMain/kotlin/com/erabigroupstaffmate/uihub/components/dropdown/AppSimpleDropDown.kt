package com.erabigroupstaffmate.uihub.components.dropdown

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun <T> AppSimpleDropDown(
    modifier: Modifier = Modifier,
    items: List<T> = listOf(),
    onToggle: () -> Unit,
    expanded: Boolean,
    title: String,
    itemText: @Composable (T) -> String,
    subtitle: String? = null,
    onChanged: (T) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                title,
                style = MaterialTheme.typography.titleLarge
            )
            subtitle?.let {
                Text(
                    it,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onToggle() },
        ) {
            items.forEach {
                DropdownMenuItem(
                    text = { Text(itemText(it)) },
                    onClick = {
                        onChanged(it)
                        onToggle()
                    },
                )
            }
        }
        IconButton(
            onClick = { onToggle() },
        ) {
//            Icon(
//                Icons.Default.MoreVert,
//                contentDescription = "More"
//            )
        }
    }
}