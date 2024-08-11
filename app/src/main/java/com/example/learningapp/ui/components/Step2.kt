package com.example.learningapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Step2() {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Step2",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 6.dp)
        )
        Text(text = "次回の授業で出てくるキーワードを調べてみましょう！")
        Text(text = "参考にした教科書のページやWebサイトのURLも記載しておきましょう。")
        Spacer(modifier = Modifier.height(20.dp))

        KeywordContainer("プロトコル")
        KeywordContainer("TCP/IP")
        KeywordContainer("OSI参照モデル")
        Spacer(modifier = Modifier.height(80.dp))
    }
}

@Composable
fun KeywordContainer(
    title: String
) {
    Column(
        modifier = Modifier
            .padding(vertical = 10.dp)
//            .background(color = MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        HorizontalDivider(modifier = Modifier.fillMaxWidth())
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            modifier = Modifier.padding(vertical = 10.dp)
        )
        val textState = remember { mutableStateOf("") }
        OutlinedTextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            label = { Text("キーワードの意味") }, // ラベルを追加
            modifier = Modifier.fillMaxWidth() // 横幅を最大に設定
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            label = { Text("参考文献") }, // ラベルを追加
            placeholder = { Text("例：教科書p○○、WebサイトのURL") },
            modifier = Modifier.fillMaxWidth() // 横幅を最大に設定
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}