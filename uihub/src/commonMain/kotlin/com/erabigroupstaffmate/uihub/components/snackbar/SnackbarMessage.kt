package com.erabigroupstaffmate.uihub.components.snackbar

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.erabigroupstaffmate.uihub.designsystem.ErrorRed
import com.erabigroupstaffmate.uihub.designsystem.SuccessGreen
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

sealed class SnackbarMessage(
    open val stringResource: StringResource? = null,
    open val action: (() -> Unit)? = null,
    override val duration: SnackbarDuration = action?.let { SnackbarDuration.Long }
        ?: SnackbarDuration.Short,
    override val message: String,
    override val actionLabel: String? = null,
    val containerColor: Color,
    override val withDismissAction: Boolean = false
) : SnackbarVisuals {

    class Default(
        override val action: (() -> Unit)? = null,
        override val message: String,
        override val stringResource: StringResource? = null,
    ) : SnackbarMessage(
        containerColor = Color.Gray,
        action = action,
        message = message,
        stringResource = stringResource,
    )

    class Done(
        override val actionLabel: String? = null,
        override val action: (() -> Unit)? = null,
        override val message: String = "Operation was done",
        override val stringResource: StringResource? = null,
        vararg val additionalString: Any
    ) : SnackbarMessage(
        containerColor = SuccessGreen,
        action = action,
        message = message,
        stringResource = stringResource,
        actionLabel = actionLabel
    )

    class Error(
        override val action: (() -> Unit)? = null,
        override val actionLabel: String? = null,
        override val message: String = "Something went wrong  , try again ",
        override val stringResource: StringResource? = null,
        vararg val additionalString: Any
    ) : SnackbarMessage(
        containerColor = ErrorRed,
        action = action,
        message = message,
        stringResource = stringResource,
        actionLabel = actionLabel
    )
}

@Composable
internal fun SnackbarMessage.getString(): String {
    return stringResource?.let {
        when (this) {
            is SnackbarMessage.Error -> stringResource(it, *additionalString)
            is SnackbarMessage.Done -> if (additionalString.isNotEmpty()) stringResource(
                it,
                *additionalString
            ) else {
                stringResource(it)
            }

            else -> stringResource(it)
        }
    } ?: message
}