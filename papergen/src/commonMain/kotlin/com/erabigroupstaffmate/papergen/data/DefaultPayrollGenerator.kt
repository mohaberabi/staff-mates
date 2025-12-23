package com.erabigroupstaffmate.papergen.data

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.drawText
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.locale.ReadAppLanguageUseCase
import com.erabigroupstaffmate.modelhub.PayrollSummaryModel
import com.erabigroupstaffmate.papergen.data.utils.PayrollStyles
import com.erabigroupstaffmate.papergen.data.utils.drawHorizontalLine
import com.erabigroupstaffmate.papergen.data.utils.providePayroll
import com.erabigroupstaffmate.papergen.domain.FileAbsolutePath
import com.erabigroupstaffmate.papergen.domain.PayrollFileGenerator
import com.erabigroupstaffmate.utility.localizations.AppLang
import com.erabigroupstaffmate.utility.localizations.layoutDirection
import com.erabigroupstaffmate.utility.math.format
import com.erabigroupstaffmate.utility.pdfgenerator.PdfGenerator
import com.erabigroupstaffmate.utility.platformdrawer.PlatformDrawer
import com.erabigroupstaffmate.utility.resources.string.ResourceStringProvider
import kotlinx.coroutines.flow.firstOrNull

class DefaultPayrollGenerator(
    private val platformDrawer: PlatformDrawer,
    private val pdfGenerator: PdfGenerator,
    private val textMeasurer: TextMeasurer,
    private val resourceStringProvider: ResourceStringProvider,
    private val readAppLanguageUseCase: ReadAppLanguageUseCase,
) : PayrollFileGenerator {

    private val style by lazy { PayrollStyles() }

    override suspend fun generateForAllStaff(
        payroll: List<PayrollSummaryModel>,
        month: String,
        year: String
    ): FileAbsolutePath {
        val bitmaps = payroll.map { pay ->
            drawTemplate(
                month = month,
                year = year,
                summary = pay
            )
        }

        val fileName = "payroll_${year}_${month}.pdf"
        return pdfGenerator.generateDocument(
            bitmaps = bitmaps,
            pdfFileName = fileName,
        )
    }

    override suspend fun generateForStaff(
        payroll: PayrollSummaryModel,
        month: String,
        year: String
    ): FileAbsolutePath {

        val bitmap = drawTemplate(
            month = month,
            year = year,
            summary = payroll
        )

        val fileName = buildString {
            append(payroll.staff.fullName)
            append("_")
            append(payroll.staff.id)
            append("_")
            append("payroll_${year}_${month}")

        }
        return pdfGenerator.generate(
            bitmap = bitmap,
            pdfFileName = fileName
        )

    }


    private suspend fun drawTemplate(
        month: String,
        year: String,
        summary: PayrollSummaryModel
    ): ImageBitmap {
        val strings = resourceStringProvider.providePayroll()
        val lang = readAppLanguageUseCase().firstOrNull() ?: AppLang.English
        val direction = lang.layoutDirection()
        var yPos = 60f

        var xPos = 40f


        val smSpace = 36f

        val mdSpace = 50f


        val bitmap = platformDrawer.draw(
            width = style.pageWidth,
            height = style.pageHeight,
            direction = direction
        ) {
            val titleText = "Erabi Group Monthly Payroll"
            val textX = style.pageWidth / 3f
            drawPayText(
                text = titleText,
                topLeft = Offset(textX, yPos)
            )
            yPos += mdSpace
            val dateText = "$month - $year"
            drawPayText(
                text = dateText,
                topLeft = Offset(center.x, yPos)
            )
            yPos += smSpace
            drawHorizontalLine(yPos = yPos)
            yPos += smSpace
            drawPayText(
                text = "Employee Name : ${summary.staff.fullName}",
                topLeft = Offset(xPos, yPos)
            )
            yPos += smSpace
            drawPayText(
                text = "Title : ${summary.staff.title}",
                topLeft = Offset(xPos, yPos)
            )
            yPos += smSpace
            drawPayText(
                text = "Join Date : ${summary.staff.joinDate}",
                topLeft = Offset(xPos, yPos)
            )
            yPos += smSpace
            val chainTitle = buildString {
                append("${strings.chain} - ${strings.branch} : ")
                append("${summary.staff.chainName}__${summary.staff.branchName}")
            }
            drawPayText(
                text = chainTitle,
                topLeft = Offset(xPos, yPos)
            )
            yPos += smSpace
            drawPayText(
                text = "${strings.baseSalary} : ${summary.staff.baseSalary.format()}",
                topLeft = Offset(xPos, yPos)
            )
            yPos += smSpace
            drawPayText(
                text = "${strings.offDays} : ${summary.staff.vacationDays}",
                topLeft = Offset(xPos, yPos)
            )
            yPos += smSpace
            drawPayText(
                text = "${strings.hrsPerShift} : ${summary.staff.shiftHrs}",
                topLeft = Offset(xPos, yPos)
            )
            yPos += smSpace
            drawHorizontalLine(yPos = yPos)
            yPos += smSpace
            drawPayText(
                text = "${strings.borrowing} : ${summary.totalBorrowings.format()}",
                topLeft = Offset(xPos, yPos)
            )
            yPos += smSpace
            drawPayText(
                text = "${strings.deductions} : ${summary.totalDeductions.format()}",
                topLeft = Offset(xPos, yPos)
            )
            yPos += smSpace
            drawHorizontalLine(yPos = yPos)
            yPos += smSpace
            drawPayText(
                text = "${strings.earnPerHr} : ${summary.earnPerHr.format()}",
                topLeft = Offset(xPos, yPos)
            )
            yPos += smSpace
            drawPayText(
                text = "TWH : ${summary.ttlWorkHrs.format()}",
                topLeft = Offset(xPos, yPos)
            )
            yPos += smSpace
            drawPayText(
                text = "TWH+  : ${summary.ttlWorkHrsWithAllowance.format()}",
                topLeft = Offset(xPos, yPos)
            )
            yPos += smSpace
            drawHorizontalLine(yPos = yPos)
            yPos += smSpace
            drawPayText(
                text = "${strings.growthEarn} : ${summary.growthSalary.format()}",
                topLeft = Offset(xPos, yPos)
            )
            yPos += smSpace
            drawPayText(
                text = "${strings.netEarn} : ${summary.netSalary.format()}",
                topLeft = Offset(xPos, yPos)
            )
            yPos += smSpace
        }
        return bitmap
    }


    private fun DrawScope.drawPayText(
        text: String,
        topLeft: Offset,
    ) {
        drawText(
            textMeasurer = textMeasurer,
            text = text,
            style = style.boldStyle,
            topLeft = topLeft
        )

    }

}