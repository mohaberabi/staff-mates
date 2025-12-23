package com.erabigroupstaffmate.features.admin.deductions.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.erabigroupstaffmate.modelhub.StaffModel
import com.erabigroupstaffmate.uihub.components.buttons.AppButton
import com.erabigroupstaffmate.uihub.components.common.StaffCard
import com.erabigroupstaffmate.uihub.components.textfields.PrimaryTextField
import com.erabigroupstaffmate.features.admin.adddeduct.componetns.DeductAmountFlowRow
import com.erabigroupstaffmate.features.admin.adddeduct.componetns.DeductReasonFlowRow
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.amount
import com.erabigroupstaffmate.uihub.resources.amount_based_on_salary
import com.erabigroupstaffmate.uihub.resources.reason
import com.erabigroupstaffmate.uihub.resources.select_amount
import com.erabigroupstaffmate.uihub.resources.select_reason
import com.erabigroupstaffmate.utility.validator.AcceptInputIfDecimal
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AddDeductScreenBody(
    modifier: Modifier = Modifier,
    amounts: List<Double>,
    buttonLabel: String,
    canSave: Boolean,
    isSaving: Boolean,
    reasons: List<StringResource>,
    staff: StaffModel,
    earnPerDay: Double,
    selectedAmount: String,
    selectedReason: String,
    onAmountChanged: (String) -> Unit,
    onReasonChanged: (String) -> Unit,
    onConfirm: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column {
            StaffCard(staff = staff)
            Spacer(Modifier.height(8.dp))
            Text(
                stringResource(Res.string.select_amount),
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                stringResource(Res.string.amount_based_on_salary),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(Modifier.height(8.dp))

            DeductAmountFlowRow(
                amounts = amounts,
                transform = { mltplr -> mltplr * earnPerDay },
                onAmountChanged = { onAmountChanged(it) },
                isSelected = { amount -> amount == selectedAmount }
            )
            Spacer(Modifier.height(8.dp))
            PrimaryTextField(
                onChanged = AcceptInputIfDecimal {
                    onAmountChanged(it)
                },
                label = stringResource(Res.string.amount),
                value = selectedAmount,
                options = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                placeHolder = "50.00"
            )

            Spacer(Modifier.height(8.dp))

            Text(
                stringResource(Res.string.select_reason),
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(Modifier.height(8.dp))
            DeductReasonFlowRow(
                reasons = reasons,
                onReasonChanged = { onReasonChanged(it) },
                isSelected = { it == selectedReason }
            )

            PrimaryTextField(
                placeHolder = reasons.firstOrNull()?.let { stringResource(it) } ?: "",
                singleLine = false,
                onChanged = onReasonChanged,
                label = stringResource(Res.string.reason),
                value = selectedReason
            )

            Spacer(Modifier.height(8.dp))

            AppButton(
                enabled = canSave,
                onClick = onConfirm,
                label = buttonLabel,
                loading = isSaving,
            )
        }
    }


}