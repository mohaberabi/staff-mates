package com.erabigroupstaffmate.uihub.components.textfields

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    onChange: (String) -> Unit = {},
) {

    var showPassword by remember {
        mutableStateOf(false)
    }


    var transformations =
        if (showPassword) VisualTransformation.None else PasswordVisualTransformation()

    PrimaryTextField(
        value = value,
        onChanged = onChange,
        modifier = modifier.fillMaxWidth(),
        label = "Password",
        visualTransformations = transformations,
        placeHolder = "********",
        singleLine = true,
        suffix = {
//            Icon(
//                modifier = Modifier
//                    .clickable() {
//                        showPassword = !showPassword
//                    },
//                imageVector = if (showPassword) Icon.Default.Lock else Icons.Default.Lock,
//                contentDescription = "change_password_visible"
//            )
        }

    )
}

