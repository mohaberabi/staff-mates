package com.erabigroupstaffmate.papergen.data

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.style.TextOverflow
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.locale.ReadAppLanguageUseCase
import com.erabigroupstaffmate.modelhub.StaffDeductType
import com.erabigroupstaffmate.modelhub.StaffModel
import com.erabigroupstaffmate.modelhub.uidmodel.DeductUiModel
import com.erabigroupstaffmate.papergen.data.utils.PayrollStyles
import com.erabigroupstaffmate.papergen.data.utils.drawHorizontalLine
import com.erabigroupstaffmate.papergen.data.utils.providePayroll
import com.erabigroupstaffmate.papergen.domain.DeductionsFileGenerator
import com.erabigroupstaffmate.papergen.domain.FileAbsolutePath
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.borrowings
import com.erabigroupstaffmate.uihub.resources.deductions
import com.erabigroupstaffmate.utility.localizations.AppLang
import com.erabigroupstaffmate.utility.localizations.layoutDirection
import com.erabigroupstaffmate.utility.math.format
import com.erabigroupstaffmate.utility.pdfgenerator.PdfGenerator
import com.erabigroupstaffmate.utility.platformdrawer.PlatformDrawer
import com.erabigroupstaffmate.utility.resources.string.ResourceStringProvider
import kotlinx.coroutines.flow.firstOrNull
import androidx.compose.ui.unit.LayoutDirection

class DefaultDeductionsFileGenerator(
    private val platformDrawer: PlatformDrawer,
    private val pdfGenerator: PdfGenerator,
    private val textMeasurer: TextMeasurer,
    private val resourceStringProvider: ResourceStringProvider,
    private val readAppLanguageUseCase: ReadAppLanguageUseCase,
) : DeductionsFileGenerator {

    private val style by lazy { PayrollStyles() }
    private val rowHeight = 40f
    private val leftMargin = 40f
    private val tableWidth = 540f

    override suspend fun generateDeductions(
        deductType: StaffDeductType,
        deductions: List<DeductUiModel>,
        month: String,
        year: String,
        staff: StaffModel,
    ): FileAbsolutePath {

        val title = when (deductType) {
            StaffDeductType.Deduct -> Res.string.deductions
            StaffDeductType.Borrow -> Res.string.borrowings
        }.let { resourceStringProvider.provideString(it) }

        val strings = resourceStringProvider.providePayroll()
        val lang = readAppLanguageUseCase().firstOrNull() ?: AppLang.English
        val direction = lang.layoutDirection()

        val bitmap = platformDrawer.draw(
            width = style.pageWidth,
            height = style.pageHeight,
            direction = direction
        ) {
            var yPos = 60f
            var xPos = 40f

            // Title
            drawSimpleText(title, leftMargin, yPos, bold = true)
            yPos += rowHeight
            drawSimpleText("$month - $year", leftMargin, yPos)
            yPos += rowHeight
            drawHorizontalLine(yPos = yPos)
            yPos += rowHeight

            // Staff Info
            drawSimpleText("Employee : ${staff.fullName}", leftMargin, yPos)
            yPos += rowHeight
            drawSimpleText("Title : ${staff.title}", leftMargin, yPos)
            yPos += rowHeight
            drawSimpleText(
                "${strings.chain} - ${strings.branch} : ${staff.chainId}__${staff.branchId}",
                leftMargin,
                yPos
            )
            yPos += rowHeight
            drawHorizontalLine(yPos = yPos)
            yPos += rowHeight
            deductions.forEach { deduct ->
                val text = buildString {
                    append(deduct.reason)
                    append(" ---$$${deduct.amount}-- ")
                    append(deduct.deductFormattedDate)
                }
                drawSimpleText(
                    text = text,
                    x = xPos,
                    y = yPos
                )
                yPos += textMeasurer.measure(text).size.height

            }

        }

        val fileName = "${deductType.name}_${staff.fullName}_${year}_${month}"
        return pdfGenerator.generate(
            bitmap = bitmap,
            pdfFileName = fileName,
        )
    }


    private fun DrawScope.drawSimpleText(
        text: String,
        x: Float,
        y: Float,
        bold: Boolean = false,
        width: Float = Size.Unspecified.width,
    ) {
        drawText(
            size = Size(width = width, height = Size.Unspecified.height),
            textMeasurer = textMeasurer,
            text = text,
            style = if (bold) style.boldStyle else style.regularStyle,
            topLeft = Offset(x, y)
        )
    }


}

