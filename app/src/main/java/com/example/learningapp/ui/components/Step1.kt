package com.example.learningapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Step1(){
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Step1",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 6.dp)
        )
        Text(text = "次回の授業の目標と背景を確認しましょう！")
        Text(text = "確認ができたら、右下のボタンを押して次のステップに進みましょう。")
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
            Text(text = "授業の背景", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = "インターネットで世界中の人と通信するためには何が必要でしょうか？そう、共通のルールが必要です。この授業ではそのルールがどのように定められているのかを学習します。")
        }
    }
}