package com.erabigroupstaffmate.uihub.components.common.numpad

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun NumPadMemberButton(
    member: NumPadMember,
    onClickDigit: (Char) -> Unit = {},
    onClearAll: () -> Unit = {},
    onClearLast: () -> Unit = {},
) {
    val text = remember(
        member
    ) {
        when (member) {
            NumPadMember.ClearAll -> 'C'
            NumPadMember.ClearLast -> 'X'
            is NumPadMember.Digit -> member.value
        }
    }
    Box(
        modifier = Modifier.padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier
                .size(75.dp)
                .border(1.dp, Color.LightGray, CircleShape)
                .clip(CircleShape)
                .clickable {
                    when (member) {
                        NumPadMember.ClearAll -> onClearAll()
                        NumPadMember.ClearLast -> onClearLast()
                        is NumPadMember.Digit -> onClickDigit(member.value)
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                "$text",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
            )
        }
    }

}
