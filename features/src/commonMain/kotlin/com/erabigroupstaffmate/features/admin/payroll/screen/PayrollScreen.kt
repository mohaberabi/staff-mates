package com.erabigroupstaffmate.features.admin.payroll.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.erabigroupstaffmate.erabitime.domain.AppDateFormats
import com.erabigroupstaffmate.modelhub.PayrollSummaryModel
import com.erabigroupstaffmate.uihub.components.sheets.AppDatePicker
import com.erabigroupstaffmate.uihub.components.body.AppScaffold
import com.erabigroupstaffmate.uihub.components.common.DateChangerRow
import com.erabigroupstaffmate.uihub.components.common.PayrollSummaryCard
import com.erabigroupstaffmate.uihub.components.dialogs.FullScreenLoaderDialog
import com.erabigroupstaffmate.uihub.components.design.MainAppBar
import com.erabigroupstaffmate.uihub.components.inject.rememberDateFormatter
import com.erabigroupstaffmate.uihub.designsystem.LocalAppLanguage
import com.erabigroupstaffmate.features.admin.payroll.viewmodel.PayrollViewModel
import com.erabigroupstaffmate.features.admin.staffdoc.navigation.StaffDocRouteArgs
import com.erabigroupstaffmate.papergen.domain.PayrollFileGenerator
import com.erabigroupstaffmate.papergen.domain.rememberPayrollGenerator
import com.erabigroupstaffmate.uihub.components.date.DatePickerWheelSheet
import com.erabigroupstaffmate.uihub.components.date.WheelPickerMonthState
import com.erabigroupstaffmate.uihub.components.date.localizeMonth
import com.erabigroupstaffmate.uihub.components.sheets.AppBottomSheet
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.ic_back
import com.erabigroupstaffmate.uihub.resources.ic_forward
import com.erabigroupstaffmate.uihub.resources.ic_pdf
import com.erabigroupstaffmate.uihub.resources.payroll
import kotlinx.coroutines.launch
import kotlinx.datetime.format.MonthNames
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun PayrollScreen(
    onBackClick: () -> Unit = {},
    onGenerateDocs: (StaffDocRouteArgs) -> Unit,
    payrollGenerator: PayrollFileGenerator = rememberPayrollGenerator(),
    viewmodel: PayrollViewModel = koinViewModel(
        parameters = { parametersOf(payrollGenerator) }
    )
) {
    val locale = LocalAppLanguage.current
    var showDatePicker by remember {
        mutableStateOf(false)
    }

    val state by viewmodel.state.collectAsStateWithLifecycle()
    val payroll by viewmodel.payroll.collectAsStateWithLifecycle()
    PayrollScreenBody(
        onBackClick = onBackClick,
        onDateChanged = viewmodel::dateChanged,
        selectedDate = "${state.selectedMonth.localizeMonth(locale)} - ${state.selectedYear}",
        payroll = payroll,
        onGenerate = { viewmodel.generatePayroll() },
        isGenerating = state.isGenerating,
        onToggleDate = { showDatePicker = !showDatePicker },
        showDatePicker = showDatePicker,
        onGenerateDocs = onGenerateDocs,
        monthNumber = state.selectedMonth.monthNumber,
        year = state.selectedYear
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PayrollScreenBody(
    isGenerating: Boolean,
    onBackClick: () -> Unit = {},
    onDateChanged: (Int, WheelPickerMonthState) -> Unit,
    selectedDate: String,
    monthNumber: Int,
    year: Int,
    payroll: List<PayrollSummaryModel>,
    onGenerate: () -> Unit,
    showDatePicker: Boolean,
    onToggleDate: () -> Unit,
    onGenerateDocs: (StaffDocRouteArgs) -> Unit,
) {
    val scope = rememberCoroutineScope()

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { payroll.size })


    AppScaffold(
        topAppBar = {
            MainAppBar(
                showBackButton = true,
                title = stringResource(Res.string.payroll),
                onBackClick = onBackClick,
                actions = {
                    IconButton(
                        onClick = onGenerate
                    ) {
                        Icon(
                            vectorResource(Res.drawable.ic_pdf),
                            ""
                        )
                    }
                }
            )
        }
    ) {

        if (payroll.isNotEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(it)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                DateChangerRow(
                    selectedDate = selectedDate,
                    onToggleDate = onToggleDate
                )
                Spacer(Modifier.height(8.dp))
                PagerNavigator(
                    currentPageIndex = pagerState.currentPage,
                    totalPages = pagerState.pageCount,
                    onNext = {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    },
                    onPrev = {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                )
                Spacer(Modifier.height(8.dp))

                HorizontalPager(
                    state = pagerState,
                    key = { index -> payroll[index].staff.id }
                ) { index ->
                    val pay = payroll[index]
                    val args = StaffDocRouteArgs(
                        payrollSummaryModel = pay,
                        year = year.toString(),
                        month = monthNumber.toString()
                    )
                    PayrollSummaryCard(
                        payroll = pay,
                        onGenerateDocs = { onGenerateDocs(args) }
                    )
                }
            }

        }


    }

    if (showDatePicker) {
        DatePickerWheelSheet(
            onConfirm = { year, monthState ->
                onDateChanged(year, monthState)
            },
            onDismiss = { onToggleDate() },
        )
    }

    if (isGenerating) {
        FullScreenLoaderDialog()
    }


}


@Composable
fun PagerNavigator(
    currentPageIndex: Int,
    totalPages: Int,
    onPrev: () -> Unit,
    onNext: () -> Unit
) {

    val currentPage = remember(currentPageIndex) { currentPageIndex + 1 }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (currentPage > 1) {
            IconButton(onClick = onPrev) {
                Icon(
                    vectorResource(Res.drawable.ic_back),
                    ""
                )
            }
        }
        Spacer(Modifier.width(8.dp))

        Text(
            "${currentPageIndex + 1} / $totalPages",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(Modifier.width(8.dp))

        if (currentPage < totalPages) {
            IconButton(onClick = onNext) {
                Icon(
                    vectorResource(Res.drawable.ic_forward),
                    ""
                )
            }
        }
    }
}
