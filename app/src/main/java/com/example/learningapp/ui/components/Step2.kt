package com.example.learningapp.ui.components

import android.app.Application
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.learningapp.viewmodel.KeywordViewModel

@Composable
fun Step2(
    keywordViewModel: KeywordViewModel = viewModel(
        factory = KeywordViewModelFactory(LocalContext.current.applicationContext as Application)
    )
) {


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

        KeywordContainer("プロトコル", keywordViewModel)
        KeywordContainer("TCP/IP", keywordViewModel)
        KeywordContainer("OSI参照モデル", keywordViewModel)
        Spacer(modifier = Modifier.height(80.dp))
    }
}

@Composable
fun KeywordContainer(
    title: String,
    viewModel: KeywordViewModel = viewModel(
        factory = KeywordViewModelFactory(LocalContext.current.applicationContext as Application)
    )
) {
    val keyword by viewModel.keywords.collectAsState()
    val currentData = keyword[title] ?: Pair("", "")
    val meaning = currentData.first
    val reference = currentData.second

    Column(
        modifier = Modifier
            .padding(vertical = 10.dp)
    ) {
        HorizontalDivider(modifier = Modifier.fillMaxWidth())
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            modifier = Modifier.padding(vertical = 10.dp)
        )
        OutlinedTextField(
            value = meaning,
            onValueChange = { newValue -> viewModel.updateKeyword(title, newValue, reference) },
            label = { Text("キーワードの意味") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = reference,
            onValueChange = { newValue -> viewModel.updateKeyword(title, meaning, newValue) },
            label = { Text("参考文献") },
            placeholder = { Text("例：教科書p○○、WebサイトのURL") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}


class KeywordViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KeywordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return KeywordViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}