package com.erabigroupstaffmate.features.shared.appmode.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.erabigroupstaffmate.modelhub.AppMode
import com.erabigroupstaffmate.uihub.designsystem.ThinGray
import com.erabigroupstaffmate.uihub.uimodel.extensions.uiInfo
import org.jetbrains.compose.resources.stringResource


@Composable
fun SelectAppModeCard(
    mode: AppMode,
    selected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val (title, subtitle) = remember(mode) { mode.uiInfo() }
    Box(modifier = Modifier.padding(bottom = 12.dp)) {
        Box(
            modifier.fillMaxWidth()
                .background(
                    if (selected) MaterialTheme.colorScheme.primary else ThinGray,
                    RoundedCornerShape(4.dp)
                ).clickable { onSelect() }
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .padding(bottom = 6.dp)
                    .animateContentSize(),
            ) {
                Text(
                    stringResource(title),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = if (selected) Color.White else MaterialTheme.typography.bodyMedium.color
                )
                Text(
                    stringResource(subtitle),
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (selected) Color.White else Color.Gray
                )
            }
        }
    }

}