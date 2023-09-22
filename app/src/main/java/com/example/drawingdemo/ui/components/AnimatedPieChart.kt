package com.example.drawingdemo.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.drawingdemo.ui.models.PieData
import com.example.drawingdemo.ui.theme.DrawingDemoTheme
import kotlinx.coroutines.launch

data class ArcData(
    val animation: Animatable<Float, AnimationVector1D>,
    val sweepAngle: Float,
    val color: Color
)

@Composable
fun AnimatedPieChart(
    modifier: Modifier = Modifier,
    pieDataPoints: List<PieData>,
    strokeWidth: Float = 25f,
    content: @Composable (modifier: Modifier) -> Unit,
) {
    val localModifier = modifier.size(300.dp)
    val total = pieDataPoints.fold(0f) { acc, pieData ->
        acc + pieData.value
    }.div(360f)
    var currentSum = 0f
    val arcs = pieDataPoints.map {
        currentSum += it.value
        ArcData(
            animation = Animatable(0f),
            sweepAngle = currentSum.div(total),
            color = it.color
        )
    }

    LaunchedEffect(key1 = arcs) {
        arcs.map {
            launch {
                it.animation.animateTo(
                    targetValue = it.sweepAngle,
                    animationSpec = tween(
                        durationMillis = 2000,
                        easing = FastOutSlowInEasing
                    )
                )
            }
        }
    }

    Canvas(modifier = localModifier) {
        arcs.reversed().map {

            val style = Stroke(
                width = strokeWidth,
                cap = StrokeCap.Round
            )
            drawArc(
                startAngle = -90f,
                sweepAngle = it.animation.value,
                color = it.color.copy(alpha = 0.6f),
                useCenter = false,
                style = style,
            )
        }
    }
    content(localModifier)
}


@Preview
@Composable
fun PieChartPreview() {
    val pieDataPoints = listOf(
        PieData("Win", 50f, color = Color.Green),
        PieData("Loss", 20f, color = Color.Red),
        PieData("Tie", 10f, color = Color.Blue)
    )

    DrawingDemoTheme {
        Surface {
            AnimatedPieChart(
                modifier = Modifier.padding(32.dp),
                pieDataPoints = pieDataPoints,
                50f,
            ) {

            }
        }
    }
}

