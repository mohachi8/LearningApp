package com.example.learningapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningapp.data.local.AppDatabase
import com.example.learningapp.data.local.entities.Step3Entity
import kotlinx.coroutines.launch

class Step3ViewModel(application: Application) : AndroidViewModel(application) {
    private val step3Dao = AppDatabase.getDatabase(application).step3Dao()

    fun saveStep3Data(content: String) {
        Log.d("KeywordViewModel", "saveStep3Data called with content: $content") // ここでログを出力
        viewModelScope.launch {
            Log.d("KeywordViewModel", "Inserting data into database") // データベース挿入前のログ
            step3Dao.insertStep3Data(Step3Entity(content = content))
            Log.d("KeywordViewModel", "Data insertion completed") // データベース挿入後のログ
        }
    }
}