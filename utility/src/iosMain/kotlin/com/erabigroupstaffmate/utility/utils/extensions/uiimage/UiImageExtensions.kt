package com.erabigroupstaffmate.utility.utils.extensions.uiimage

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asSkiaBitmap
import com.erabigroupstaffmate.utility.utils.extensions.bytearray.toNSData
import kotlinx.cinterop.ExperimentalForeignApi
import org.jetbrains.skia.Image
import platform.UIKit.UIImage


@OptIn(ExperimentalForeignApi::class)
fun ImageBitmap.toUIImage(): UIImage? {
    val skiaBitmap = this.asSkiaBitmap()
    val skiaImage = Image.makeFromBitmap(skiaBitmap)
    val encodedImage = skiaImage.encodeToData() ?: return null
    val bytes = encodedImage.bytes
    val nsData = bytes.toNSData()
    return UIImage(nsData)
}
