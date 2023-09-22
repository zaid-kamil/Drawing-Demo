package com.example.drawingdemo.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.drawingdemo.ui.theme.DrawingDemoTheme

@Composable
fun SmileyFace(
    modifier: Modifier = Modifier,
    content: @Composable (modifier: Modifier) -> Unit,
) {
    val greenLight700 = Color(0xFF173A01)
    val green700 = Color(0xFF00F091)
    val red700 = Color(0xFFD50000)
    val dark = Color(0xFF212121)
    Canvas(modifier = modifier.size(300.dp)) {
        // Head
        drawCircle(
            Brush.linearGradient(
                colors = listOf(greenLight700, green700)
            ),
            radius = size.width / 2,
            center = center,
            style = Stroke(width = size.width * .05f)
        )

        // Smile
        val smilePadding = size.width * 0.15f
        drawArc(
            brush = Brush.verticalGradient(
                colors = listOf(red700, Color.White)
            ),
            startAngle = 0f,
            sweepAngle = 180f,
            useCenter = true,
            topLeft = Offset(smilePadding, smilePadding),
            size = Size(size.width - smilePadding * 2, size.height - smilePadding * 2)
        )

        // left eye
        drawRect(
            brush = Brush.radialGradient(
                colors = listOf(dark, Color.White, dark),
                tileMode = TileMode.Repeated,
                radius = 15f
            ),
            topLeft = Offset((size.width * 0.25f), size.height / 4),
            size = Size(smilePadding, smilePadding)
        )

        // Right eye
        drawRect(
            brush = Brush.radialGradient(
                colors = listOf(dark, Color.White, dark),
                tileMode = TileMode.Repeated,
                radius = 15f
            ),
            topLeft = Offset((size.width * 0.75f) - smilePadding, size.height / 4),
            size = Size(smilePadding, smilePadding)
        )

        // draw points
        drawPoints(
            pointMode = PointMode.Points,
            points = listOf(
                Offset(
                    (size.width * 0.25f + smilePadding / 2),
                    size.height / 4 + smilePadding / 2
                ),
                Offset(
                    (size.width * 0.75f + smilePadding / 2) - smilePadding,
                    size.height / 4 + smilePadding / 2
                )
            ),
            strokeWidth = 75f,
            color = Color.Green,
            cap = StrokeCap.Round
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AnimatedWavePreview() {
    DrawingDemoTheme {
        Surface {
            SmileyFace {}
        }
    }
}