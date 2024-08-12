package com.example.learningapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningapp.data.local.dao.Step3Dao
import com.example.learningapp.data.local.entities.Step3Entity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class Step3ViewModel(application: Application) : AndroidViewModel(application) {
    // MutableStateFlowで現在のテキスト内容を管理
    private val _step3Content = MutableStateFlow<String>("")
    val step3Content: StateFlow<String> = _step3Content

    // テキスト内容の更新
    fun updateContent(content: String) {
        _step3Content.value = content
    }

    // データベースに保存する関数（この部分はデータベースを利用する場合に実装）
    fun saveContentToDatabase(step3Dao: Step3Dao) {
        viewModelScope.launch {
            Log.d("Step3ViewModel", "saveContentToDatabase called")
            val content = _step3Content.value
            Log.d("Step3ViewModel", "Content to save: $content")
            step3Dao.insertStep3Data(Step3Entity(content = content))
            Log.d("Step3ViewModel", "Data insertion completed")
        }
    }
}