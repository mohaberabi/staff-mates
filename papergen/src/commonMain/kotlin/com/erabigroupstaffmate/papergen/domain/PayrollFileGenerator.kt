package com.erabigroupstaffmate.papergen.domain

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.rememberTextMeasurer
import com.erabigroupstaffmate.modelhub.PayrollSummaryModel
import com.erabigroupstaffmate.papergen.data.DefaultPayrollGenerator
import org.koin.compose.currentKoinScope


typealias FileAbsolutePath = String

interface PayrollFileGenerator {

    suspend fun generateForStaff(
        payroll: PayrollSummaryModel,
        month: String,
        year: String,
    ): FileAbsolutePath

    suspend fun generateForAllStaff(
        payroll: List<PayrollSummaryModel>,
        month: String,
        year: String,
    ): FileAbsolutePath
}

@Composable
fun rememberPayrollGenerator(): PayrollFileGenerator {
    val scope = currentKoinScope()
    val measurer = rememberTextMeasurer()
    return remember {
        DefaultPayrollGenerator(
            platformDrawer = scope.get(),
            pdfGenerator = scope.get(),
            textMeasurer = measurer,
            resourceStringProvider = scope.get(),
            readAppLanguageUseCase = scope.get()
        )
    }
}