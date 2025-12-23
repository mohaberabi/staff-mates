package com.erabigroupstaffmate.uihub.components.textfields

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.erabigroupstaffmate.utility.constant.PHONE_LENGTH
import com.erabigroupstaffmate.utility.validator.AcceptInputIfNumber


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun StaffLoggerTextField(
    value: String = "",
    onValueChanged: (String) -> Unit,
    onDone: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold)
    val focusRequester = remember {
        FocusRequester()
    }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    val isValid = remember(
        value,
    ) {
        value.length == PHONE_LENGTH
    }

    LaunchedEffect(isValid) {
        if (isValid) {
            onDone()
        }
    }
    TextField(
        modifier = Modifier
            .heightIn(min = 65.dp)
            .focusRequester(focusRequester),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Phone
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                if (isValid) {
                    onDone()
                }
            },
        ),
        value = value,
        onValueChange = AcceptInputIfNumber { number ->
            if (number.length <= PHONE_LENGTH) {
                onValueChanged(number)
            }

        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(
                "Staff Id : 01111786667",
                style = style.copy(color = Color.LightGray)
            )
        },
        textStyle = style,
    )
}