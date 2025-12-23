package com.erabigroupstaffmate.uihub.components.sheets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import com.erabigroupstaffmate.uihub.components.buttons.AppButton


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDatePicker(
    onDismiss: () -> Unit,
    onSelected: (Long) -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = { onDismiss() },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        },
        confirmButton = {
            TextButton(onClick = {
                datePickerState.selectedDateMillis?.let { onSelected(it) }
                onDismiss()
            }) { Text("Choose") }

        }
    ) {
        DatePicker(
            state = datePickerState,
            showModeToggle = false
        )
    }
}