package com.example.learningapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learningapp.viewmodel.Step3ViewModel

@Composable
fun Step3(
    step3ViewModel: Step3ViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val textState by step3ViewModel.step3Content.collectAsState()

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "Step3",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 6.dp)
        )
        Text(text = "予習の段階で分からなかったことや、授業で聞きたいことなど自由に書きましょう。")
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = textState,
            onValueChange = { step3ViewModel.updateContent(it) },
            label = { Text("分からなかったことや質問したいことを書き込もう！") },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Spacer(modifier = Modifier.height(80.dp))
    }
}