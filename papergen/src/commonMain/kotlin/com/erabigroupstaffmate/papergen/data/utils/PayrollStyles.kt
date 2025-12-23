package com.erabigroupstaffmate.papergen.data.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class PayrollStyles(
    val pageWidth: Int = 595,
    val pageHeight: Int = 843,
    val boldStyle: TextStyle = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    ),
    val regularStyle: TextStyle = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal
    )
)

internal fun DrawScope.drawHorizontalLine(
    color: Color = Color.Companion.Black,
    yPos: Float,
    stroke: Float = 2f
) {
    drawLine(
        color = color,
        start = Offset(0f, yPos),
        end = Offset(size.width, yPos),
        strokeWidth = stroke,
    )
}
