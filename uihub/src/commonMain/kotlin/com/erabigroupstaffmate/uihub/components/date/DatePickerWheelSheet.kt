package com.erabigroupstaffmate.uihub.components.date

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.erabigroupstaffmate.erabitime.domain.ErabiTime
import com.erabigroupstaffmate.erabitime.domain.constants.arabicMonthNames
import com.erabigroupstaffmate.uihub.components.buttons.AppButton
import com.erabigroupstaffmate.uihub.components.sheets.AppBottomSheet
import com.erabigroupstaffmate.uihub.designsystem.LocalAppLanguage
import com.erabigroupstaffmate.uihub.designsystem.ThinGray
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.confirm
import com.erabigroupstaffmate.utility.localizations.AppLang
import kotlinx.datetime.format.MonthNames
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

private fun createMonthNames(lang: AppLang): List<String> {
    return if (lang == AppLang.Arabic)
        arabicMonthNames.names
    else MonthNames.ENGLISH_FULL.names
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerWheelSheet(
    yearRange: IntRange = 2025..2030,
    onMonthChanged: (WheelPickerMonthState) -> Unit = {},
    onYearChanged: (Int) -> Unit = {},
    maxHeight: Dp = 165.dp,
    maxElementHeight: Dp = 65.dp,
    onConfirm: (year: Int, state: WheelPickerMonthState) -> Unit,
    onDismiss: () -> Unit = {},
) {
    val erabiTime = koinInject<ErabiTime>()


    val years = remember(
        yearRange
    ) {
        yearRange.toList()
    }
    val lang = LocalAppLanguage.current

    val months = remember(
        lang
    ) {
        createMonthNames(lang)
    }

    var selectedYear by remember {
        mutableIntStateOf(yearRange.first)
    }

    var selectedMonth by remember {
        mutableStateOf(WheelPickerMonthState())
    }

    val yearState = rememberLazyListState()

    val monthState = rememberLazyListState()
    LaunchedEffect(Unit) {
        monthState.scrollToItem(
            erabiTime.getNowDateTimeInErabiZone().monthNumber - 1
        )
    }
    LaunchedEffect(
        monthState.firstVisibleItemIndex,
    ) {
        val index = monthState.firstVisibleItemIndex.coerceIn(0, 11)
        val monthNumber = index + 1
        selectedMonth = WheelPickerMonthState(
            monthNumber = monthNumber,
            monthNameAr = arabicMonthNames.names[index],
            monthNameEn = MonthNames.ENGLISH_FULL.names[index]
        )
        onMonthChanged(selectedMonth)

    }

    LaunchedEffect(
        yearState.firstVisibleItemIndex,
    ) {
        val index = yearState.firstVisibleItemIndex.coerceIn(0, years.size - 1)
        selectedYear = years[index]
        onYearChanged(selectedYear)
    }

    AppBottomSheet(
        onDismissRequest = onDismiss
    ) {


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {


            LazyColumn(
                state = monthState,
                modifier = Modifier.height(maxHeight)
            ) {
                stickyHeader {
                    Text(
                        "Month",
                        style = MaterialTheme.typography.bodyMedium.copy(Color.LightGray)
                    )
                }
                itemsIndexed(months) { index, month ->
                    Text(
                        month,
                        modifier = Modifier.wrapContentWidth()
                            .height(maxElementHeight),
                        style = monthState.wheelTextStyle(index)
                    )
                }
                item {
                    Box(Modifier.height(maxElementHeight))
                }
            }
            Spacer(Modifier.width(50.dp))

            LazyColumn(
                state = yearState,
                modifier = Modifier.height(maxHeight)
            ) {
                stickyHeader {
                    Text("Year", style = MaterialTheme.typography.bodyMedium.copy(ThinGray))
                }
                itemsIndexed(yearRange.toList()) { index, year ->
                    Text(
                        "$year",
                        modifier = Modifier.wrapContentWidth()
                            .height(maxElementHeight),
                        style = yearState.wheelTextStyle(index)
                    )
                }
                item {
                    Box(Modifier.height(maxElementHeight))
                }
            }
        }
        AppButton(
            onClick = {
                onConfirm(
                    selectedYear,
                    selectedMonth
                )
                onDismiss()
            },
            label = stringResource(Res.string.confirm)
        )
    }
}

private fun LazyListState.wheelTextStyle(
    index: Int,
) = if (index == firstVisibleItemIndex)
    TextStyle(
        color = Color.Black,
        fontWeight = FontWeight.Bold
    ) else TextStyle(color = Color.LightGray, fontWeight = FontWeight.Normal)