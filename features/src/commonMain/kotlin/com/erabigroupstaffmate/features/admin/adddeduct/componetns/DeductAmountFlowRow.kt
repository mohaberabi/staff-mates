package com.erabigroupstaffmate.features.admin.adddeduct.componetns

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import com.erabigroupstaffmate.uihub.components.buttons.AppAssistedChip
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun DeductAmountFlowRow(
    amounts: List<Double>,
    transform: (Double) -> Double,
    onAmountChanged: (String) -> Unit,
    isSelected: (String) -> Boolean,
) {
    FlowRow {
        amounts.forEach { mltplr ->
            val earnPerDayRatio = transform(mltplr).toInt().toString()
            Box(
                modifier = Modifier.padding(8.dp)
            ) {
                AppAssistedChip(
                    onClick = { onAmountChanged(earnPerDayRatio) },
                    label = {
                        Text(
                            earnPerDayRatio,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(6.dp)
                        )
                    },
                    isSelected = isSelected(earnPerDayRatio)
                )
            }
        }
    }
}

@Composable
fun DeductReasonFlowRow(
    reasons: List<StringResource>,
    onReasonChanged: (String) -> Unit,
    isSelected: (String) -> Boolean,
) {
    FlowRow {
        reasons.fastForEach { res ->
            val string = stringResource(res)
            Box(
                modifier = Modifier.padding(8.dp)
            ) {
                AppAssistedChip(
                    onClick = { onReasonChanged(string) },
                    label = {
                        Text(
                            stringResource(res),
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(8.dp)
                        )
                    },
                    isSelected = isSelected(string)
                )
            }
        }
    }
}