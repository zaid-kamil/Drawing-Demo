package com.example.drawingdemo.ui.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.drawingdemo.ui.components.AnimatedPieChart
import com.example.drawingdemo.ui.models.PieData

@Composable
fun ExampleOneScreen() {
    val pieDataPoints = listOf(
        PieData("Clothes", 25f, Color.Green),
        PieData("Food", 15f, Color.Blue),
        PieData("Transportation", 35f, Color.Red),
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        AnimatedPieChart(
            pieDataPoints = pieDataPoints
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                pieDataPoints.map { pieData ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .border(1.dp, Color.LightGray, MaterialTheme.shapes.extraLarge)
                            .padding(8.dp)
                            .size(30.dp)
                    ) {
                        Text(
                            text = pieData.label.slice(0..2).uppercase(),
                            style = MaterialTheme.typography.labelSmall
                        )
                        Text(
                            text = pieData.value.toInt().toString(),
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    // add a space of 4 dp between each column
                    Spacer(modifier = Modifier.padding(2.dp))
                }
            }
        }

    }

}