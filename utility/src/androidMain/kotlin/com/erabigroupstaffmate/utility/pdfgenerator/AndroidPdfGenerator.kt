package com.erabigroupstaffmate.utility.pdfgenerator

import android.content.Context
import android.graphics.pdf.PdfDocument
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import com.erabigroupstaffmate.utility.disptachersprovider.DispatchersProvider
import kotlinx.coroutines.withContext
import java.io.File

class AndroidPdfGenerator(
    private val context: Context,
    private val dispatchers: DispatchersProvider
) : PdfGenerator {
    override suspend fun generate(
        bitmap: ImageBitmap,
        pdfFileName: FileNameNoExtensions,
        pageNumber: Int,
    ): FileAbsolutePath {
        val width = bitmap.width
        val height = bitmap.height
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(
            width,
            height,
            pageNumber
        ).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas = page.canvas
        val androidBitmap = bitmap.asAndroidBitmap()
        withContext(dispatchers.default) {
            canvas.drawBitmap(androidBitmap, 0f, 0f, null)
        }
        pdfDocument.finishPage(page)
        val dir = context.getExternalFilesDir(null)
        val file = File(dir, "${pdfFileName}.pdf")
        withContext(dispatchers.io) {
            pdfDocument.writeTo(file.outputStream())
        }
        pdfDocument.close()
        return file.absolutePath
    }

    override suspend fun generateDocument(
        bitmaps: List<ImageBitmap>,
        pdfFileName: FileNameNoExtensions
    ): FileAbsolutePath {
        val pdfDocument = PdfDocument()

        bitmaps.forEachIndexed { index, bitmap ->
            val width = bitmap.width
            val height = bitmap.height
            val pageInfo = PdfDocument.PageInfo.Builder(
                width,
                height,
                index + 1,
            ).create()
            val page = pdfDocument.startPage(pageInfo)
            val canvas = page.canvas
            val androidBitmap = bitmap.asAndroidBitmap()
            withContext(dispatchers.default) {
                canvas.drawBitmap(androidBitmap, 0f, 0f, null)
            }
            pdfDocument.finishPage(page)
        }
        val dir = context.getExternalFilesDir(null)
        val file = File(dir, "${pdfFileName}.pdf")
        withContext(dispatchers.io) {
            pdfDocument.writeTo(file.outputStream())
        }
        pdfDocument.close()
        return file.absolutePath
    }


}