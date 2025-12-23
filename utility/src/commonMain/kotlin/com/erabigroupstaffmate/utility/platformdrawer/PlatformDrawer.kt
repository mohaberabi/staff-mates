package com.erabigroupstaffmate.utility.platformdrawer

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import com.erabigroupstaffmate.utility.disptachersprovider.DispatchersProvider
import kotlinx.coroutines.withContext

class PlatformDrawer(
    private val dispatchers: DispatchersProvider
) {

    suspend fun draw(
        width: Int,
        height: Int,
        direction: LayoutDirection = LayoutDirection.Ltr,
        drawBlock: DrawScope. () -> Unit,
    ): ImageBitmap {
        val imageBitmap = ImageBitmap(width = width, height = height)
        val canvas = Canvas(image = imageBitmap)
        val scope = CanvasDrawScope()
        val size = Size(width = width.toFloat(), height = height.toFloat())
        val density = Density(1f)
        withContext(dispatchers.default) {
            scope.draw(
                density = density,
                layoutDirection = direction,
                canvas = canvas,
                size = size,
                block = drawBlock
            )
        }

        return imageBitmap
    }
}