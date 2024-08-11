package com.example.learningapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

const val lessonTitleText = "第1回"
val itemsList = listOf("授業の目標", "授業の意義")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreparationScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = lessonTitleText)
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                actions = {},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Next Step")
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            val currentStep = remember { mutableStateOf(2) }
            StepProgressBar(
                currentStep = 2,
                totalSteps = 3,
                onStepClicked = { step -> currentStep.value = step })
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
//                    .background(color = MaterialTheme.colorScheme.primary)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(text = "次回の授業の目標を確認しましょう！")
                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier
                ) {
                    Text(text = "授業の目標", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = "インターネットのプロトコルについて学ぶ")
                }
                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier
                ) {
                    Text(text = "授業の意義", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = "インターネットで世界中の人と通信するためには何が必要でしょうか？そう、共通のルールが必要です。この授業ではそのルールがどのように定められているのかを学習します。")
                }
            }
        }
    }
}

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
                    .height(14.dp)
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