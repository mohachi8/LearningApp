package com.example.learningapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StepProgressBar(
    currentStep: Int,
    totalSteps: Int,
    onStepClicked: (Int) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        for (step in 1..totalSteps) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .background(
                        color = if (step <= currentStep) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(
                            alpha = 0.3f
                        )
                    )
                    .clickable { onStepClicked(step) }
            )
            if (step < totalSteps) {
                Spacer(modifier = Modifier.width(4.dp))
            }
        }
    }
}