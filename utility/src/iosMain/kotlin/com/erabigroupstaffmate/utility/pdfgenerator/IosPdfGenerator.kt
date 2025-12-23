package com.erabigroupstaffmate.utility.pdfgenerator

import androidx.compose.ui.graphics.ImageBitmap
import com.erabigroupstaffmate.utility.disptachersprovider.DispatchersProvider
import com.erabigroupstaffmate.utility.utils.extensions.uiimage.toUIImage
import com.erabigroupstaffmate.utility.utils.file.getDocumentDirectory
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import platform.CoreGraphics.CGRectMake
import platform.Foundation.NSURL
import platform.UIKit.UIGraphicsPDFRenderer
import platform.UIKit.UIGraphicsPDFRendererFormat
import kotlin.coroutines.resume

@OptIn(ExperimentalForeignApi::class)
class IosPdfGenerator(
    private val dispatchers: DispatchersProvider
) : PdfGenerator {
    override suspend fun generate(
        bitmap: ImageBitmap,
        pdfFileName: FileNameNoExtensions,
        pageNumber: Int
    ): FileAbsolutePath {

        val filePath = getPdfFilePath(pdfFileName = pdfFileName)
            ?: error("error getting file path ")

        val image = bitmap.toUIImage()

        val pageRect = CGRectMake(
            x = 0.0,
            y = 0.0,
            width = bitmap.width.toDouble(),
            height = bitmap.height.toDouble()
        )
        val format = UIGraphicsPDFRendererFormat()
        val renderer = UIGraphicsPDFRenderer(bounds = pageRect, format = format)
        withContext(dispatchers.default) {
            suspendCancellableCoroutine { continuation ->
                renderer.writePDFToURL(
                    url = filePath,
                    withActions = { context ->
                        context?.beginPage()
                        image?.drawInRect(pageRect)
                        continuation.resume(Unit)
                    },
                    error = null
                )
            }
        }

        return filePath.path ?: error("error getting the pdf file path")
    }

    override suspend fun generateDocument(
        bitmaps: List<ImageBitmap>,
        pdfFileName: FileNameNoExtensions
    ): FileAbsolutePath {
        val filePath = getPdfFilePath(pdfFileName = pdfFileName)
            ?: error("error getting file path ")

        val firstBitmap = bitmaps.first()
        val pageRect = CGRectMake(
            0.0,
            0.0,
            firstBitmap.width.toDouble(),
            firstBitmap.height.toDouble()
        )
        val format = UIGraphicsPDFRendererFormat()
        val renderer = UIGraphicsPDFRenderer(bounds = pageRect, format = format)

        withContext(dispatchers.default) {
            suspendCancellableCoroutine { continuation ->
                renderer.writePDFToURL(
                    filePath,
                    error = null,
                    withActions = { context ->
                        bitmaps.forEach { bitmap ->
                            val uiImage = bitmap.toUIImage()
                            context?.beginPage()
                            uiImage?.drawInRect(
                                CGRectMake(
                                    0.0,
                                    0.0,
                                    firstBitmap.width.toDouble(),
                                    firstBitmap.height.toDouble()
                                )
                            )
                        }
                    }
                )
                continuation.resume(Unit)
            }
        }

        return filePath.path ?: error("error getting the pdf file path")

    }

    private fun getPdfFilePath(pdfFileName: FileNameNoExtensions): NSURL? {
        val dir = getDocumentDirectory()
        return dir?.URLByAppendingPathComponent("$pdfFileName.pdf")
    }
}