package com.erabigroupstaffmate.uihub.utils.extensions.compose.modifier

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed


fun Modifier.imeInsetsPadding() = composed {
    padding(WindowInsets.ime.asPaddingValues())
}