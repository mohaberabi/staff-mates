package com.erabigroupstaffmate.utility.pdfgenerator

import androidx.compose.ui.graphics.ImageBitmap

typealias FileAbsolutePath = String
typealias FileNameNoExtensions = String

interface PdfGenerator {
    suspend fun generate(
        bitmap: ImageBitmap,
        pdfFileName: FileNameNoExtensions,
        pageNumber: Int = 1
    ): FileAbsolutePath

    suspend fun generateDocument(
        bitmaps: List<ImageBitmap>,
        pdfFileName: FileNameNoExtensions,
    ): FileAbsolutePath

}