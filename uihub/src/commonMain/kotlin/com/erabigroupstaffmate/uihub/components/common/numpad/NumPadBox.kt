package com.erabigroupstaffmate.uihub.components.common.numpad

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.erabigroupstaffmate.uihub.designsystem.ErrorRed

private val numPadMembers = listOf(
    NumPadMember.Digit('1'),
    NumPadMember.Digit('2'),
    NumPadMember.Digit('3'),
    NumPadMember.Digit('4'),
    NumPadMember.Digit('5'),
    NumPadMember.Digit('6'),
    NumPadMember.Digit('7'),
    NumPadMember.Digit('8'),
    NumPadMember.Digit('9'),
    NumPadMember.ClearAll,
    NumPadMember.Digit('0'),
    NumPadMember.ClearLast,
)

@Composable
fun SimpleNumPad(
    modifier: Modifier = Modifier,
    size: Int = 5,
    onSubmit: (String) -> Unit = {},
) {
    NumPadBox(
        size = size,
        onSubmit = onSubmit,
        modifier = modifier,
        valueContent = { value ->
            Text(
                value,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 10.dp)
            )
        },
    )
}

@Composable
fun PasswordNumPad(
    modifier: Modifier = Modifier,
    size: Int = 5,
    isError: Boolean = false,
    onSubmit: (String) -> Unit = {},
) {

    NumPadBox(
        size = size,
        onSubmit = onSubmit,
        modifier = modifier,
        valueContent = { value ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(size) { index ->
                    val entered = value.getOrNull(index) != null
                    val color = when {
                        isError -> ErrorRed.copy(alpha = 0.4f)
                        else -> Color.LightGray
                    }
                    val (border, borderWidth) = when {
                        entered -> MaterialTheme.colorScheme.primary to 2.dp
                        else -> Color.Transparent to 0.dp
                    }
                    Box(
                        Modifier.padding(6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .background(color, RoundedCornerShape(4.dp))
                                .border(borderWidth, border, RoundedCornerShape(4.dp))
                        )
                    }

                }
            }

        },
    )
}

@Composable
fun NumPadBox(
    modifier: Modifier = Modifier,
    size: Int = 5,
    valueContent: @Composable (String) -> Unit,
    valueContentSpan: Int = 3,
    onSubmit: (String) -> Unit = {},
) {

    var value by remember {
        mutableStateOf("")
    }
    LaunchedEffect(value) {
        if (value.length == size) {
            onSubmit(value)
        }
    }


    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Ltr
    ) {
        LazyVerticalGrid(
            contentPadding = PaddingValues(8.dp),
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.Center,
            modifier = modifier
        ) {

            item(
                span = { GridItemSpan(valueContentSpan) },
            ) {
                valueContent(value)
            }
            items(numPadMembers) { member ->
                NumPadMemberButton(
                    member = member,
                    onClearAll = { value = "" },
                    onClickDigit = {
                        if (value.length == size) {
                            value = ""
                            value += it
                        } else {
                            value += it
                        }

                    },
                    onClearLast = {
                        if (value.isNotEmpty()) {
                            value = value.substring(0, value.length - 1)
                        }
                    }
                )
            }

        }

    }

}