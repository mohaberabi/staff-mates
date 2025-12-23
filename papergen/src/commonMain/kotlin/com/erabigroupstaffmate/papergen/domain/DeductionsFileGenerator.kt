package com.erabigroupstaffmate.papergen.domain

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.rememberTextMeasurer
import com.erabigroupstaffmate.modelhub.StaffDeductModel
import com.erabigroupstaffmate.modelhub.StaffDeductType
import com.erabigroupstaffmate.modelhub.StaffModel
import com.erabigroupstaffmate.modelhub.uidmodel.DeductUiModel
import com.erabigroupstaffmate.papergen.data.DefaultDeductionsFileGenerator
import com.erabigroupstaffmate.papergen.data.DefaultPayrollGenerator
import org.koin.compose.currentKoinScope

interface DeductionsFileGenerator {


    suspend fun generateDeductions(
        deductType: StaffDeductType,
        deductions: List<DeductUiModel>,
        month: String,
        year: String,
        staff: StaffModel,
    ): FileAbsolutePath


}

@Composable
fun rememberDeductionsGenerator(): DeductionsFileGenerator {
    val scope = currentKoinScope()
    val measurer = rememberTextMeasurer()
    return remember {
        DefaultDeductionsFileGenerator(
            platformDrawer = scope.get(),
            pdfGenerator = scope.get(),
            textMeasurer = measurer,
            resourceStringProvider = scope.get(),
            readAppLanguageUseCase = scope.get()
        )
    }
}