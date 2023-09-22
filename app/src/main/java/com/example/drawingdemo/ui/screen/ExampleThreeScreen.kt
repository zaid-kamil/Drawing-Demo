package com.example.drawingdemo.ui.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ExampleThreeScreen() {
    val viewModel: MyViewModel = viewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            val seconds by viewModel.times.collectAsState()
            Chronos(seconds = seconds)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Start(onStart = { viewModel.start() })
            Stop(onStop = { viewModel.stop() })
            Pause(onReset = { viewModel.pause() })
        }
    }

}


val purpleDark = Color(0xFF4800CF)
val purpleLight = Color(0xFF9A71E9)
val purple = Color(0xFF5E35B1)
val greenDark = Color(0xFF052513)

@Composable
fun Start(onStart: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onStart
    ) {
        Text(
            text = "START",
            style = MaterialTheme.typography.titleMedium,
            color = greenDark,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun Stop(onStop: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onStop
    ) {
        Text(
            text = "STOP",
            style = MaterialTheme.typography.titleMedium,
            color = greenDark,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun Pause(onReset: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onReset
    ) {
        Text(
            text = "Pause",
            style = MaterialTheme.typography.titleMedium,
            color = greenDark,
            modifier = Modifier.padding(16.dp)
        )
    }
}


@Composable
fun CircleProgress(
    angle: Float,
    modifier: Modifier = Modifier,
) {


    Box(modifier = modifier
        .fillMaxSize()
        .drawBehind {
            drawArc(
                color = Color.White,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = 50f)
            )
            drawArc(
                brush = Brush.verticalGradient(
                    listOf(purpleDark, purple, purpleLight),
                ),
                startAngle = -90f,
                sweepAngle = angle,
                useCenter = false,
                style = Stroke(width = 30f, cap = StrokeCap.Round)
            )
        })
}

@Composable
fun Chronos(
    seconds: Int,
    modifier: Modifier = Modifier
) {
    val progressAngle by animateFloatAsState(
        targetValue = 360f / 30f * seconds, animationSpec = tween(500), label = "progressAngle"
    )
    Box(
        modifier
            .fillMaxSize()
            .aspectRatio(1f)
    ) {
        ChronosProgress {
            Text(
                text = "${seconds}s",
                style = MaterialTheme.typography.headlineLarge,
                color = purpleDark,
                modifier = Modifier.align(alignment = Alignment.Center)
            )
        }
        CircleProgress(
            angle = progressAngle
        )

    }

}


@Composable
fun ChronosProgress(
    modifier: Modifier = Modifier, content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
            .shadow(elevation = 1.dp, shape = CircleShape)
            .background(color = purpleLight)
            .clip(CircleShape),
        content = content
    )
}