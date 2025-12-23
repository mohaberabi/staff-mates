package com.erabigroupstaffmate.uihub.components.textfields

import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.material3.OutlinedTextFieldDefaults.Container
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.erabigroupstaffmate.uihub.designsystem.ThinGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrimaryTextField(
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    error: Boolean = false,
    value: String = "",
    onChanged: (String) -> Unit = {},
    options: KeyboardOptions = KeyboardOptions.Default,
    actions: KeyboardActions = KeyboardActions.Default,
    suffix: @Composable () -> Unit = {},
    label: String = "",
    placeHolder: String = "",
    isReadOnly: Boolean = false,
    visualTransformations: VisualTransformation = VisualTransformation.None
) {

    val focus = remember { FocusRequester() }
    val interactionSource = remember { MutableInteractionSource() }
    val colors = TextFieldDefaults.colors(
        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
        unfocusedIndicatorColor = ThinGray,
        unfocusedContainerColor = ThinGray,
        focusedContainerColor = MaterialTheme.colorScheme.background
    )
    var isFocused by remember {
        mutableStateOf(false)
    }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        if (label.isNotEmpty())
            Text(
                text = label,
                modifier.padding(
                    horizontal = 8.dp,
                    vertical = 8.dp,
                ),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
            )
        BasicTextField(
            singleLine = singleLine,
            readOnly = isReadOnly,
            keyboardOptions = options,
            visualTransformation = visualTransformations,
            textStyle = MaterialTheme.typography.bodyLarge,
            decorationBox = { inner ->
                OutlinedTextFieldDefaults.DecorationBox(
                    value = value,
                    innerTextField = {
                        Box {
                            if (value.isEmpty())
                                Text(
                                    placeHolder,
                                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray)
                                )
                            inner()
                        }
                    },
                    enabled = true,
                    singleLine = singleLine,
                    visualTransformation = visualTransformations,
                    trailingIcon = suffix,
                    interactionSource = interactionSource,
                    colors = colors,
                    contentPadding = OutlinedTextFieldDefaults.contentPadding(),
                    container = {
                        Container(
                            enabled = true,
                            isError = error,
                            interactionSource = interactionSource,
                            colors = colors,
                            shape = RoundedCornerShape(6.dp),
                            modifier = Modifier.border(
                                width = if (isFocused) 2.dp else 1.dp,
                                color = if (isFocused) MaterialTheme.colorScheme.primary else Color.Transparent,
                                shape = RoundedCornerShape(6.dp),
                            )
                        )
                    },
                )

            },
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp)
                .focusable()
                .focusRequester(focus)
                .onFocusChanged { isFocused = it.isFocused },
            value = value,
            onValueChange = onChanged,
            keyboardActions = actions
        )
    }

}

