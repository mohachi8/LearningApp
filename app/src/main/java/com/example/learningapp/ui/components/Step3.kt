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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learningapp.viewmodel.Step3ViewModel

@Composable
fun Step3(
    textState: MutableState<String> = remember { mutableStateOf("") },
    viewModel: Step3ViewModel
) {
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
            value = textState.value,
            onValueChange = {
                textState.value = it
                viewModel.saveStep3Data(it) // ここで内容が更新されるたびに保存を試みる
            },
            label = { Text("分からなかったことや質問したいことを書き込もう！") },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Spacer(modifier = Modifier.height(80.dp))
    }
}

// saveData 関数を外部に作成
fun saveData(viewModel: Step3ViewModel, textState: String) {
    viewModel.saveStep3Data(textState)
}