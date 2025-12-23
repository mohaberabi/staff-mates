package com.erabigroupstaffmate.uihub.components.sheets

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    dismissOnBack: Boolean = true,
    onDismissRequest: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    ModalBottomSheet(
        properties = ModalBottomSheetProperties(shouldDismissOnBackPress = dismissOnBack),
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        content = content,
        containerColor = MaterialTheme.colorScheme.background,
        sheetState = sheetState,
    )
}