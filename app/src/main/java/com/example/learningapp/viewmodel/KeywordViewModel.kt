package com.example.learningapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningapp.data.local.AppDatabase
import com.example.learningapp.data.local.entities.KeywordEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class KeywordViewModel(application: Application) : AndroidViewModel(application) {
//    private val keywordDao = AppDatabase.getDatabase(application).keywordDao()

    // 各キーワードに対応するmeaningとreferenceを保持
    private val _keywords = MutableStateFlow<Map<String, Pair<String, String>>>(emptyMap())
    val keywords: StateFlow<Map<String, Pair<String, String>>> = _keywords

    fun updateKeyword(title: String, meaning: String, reference: String) {
        _keywords.value = _keywords.value.toMutableMap().apply {
            put(title, Pair(meaning, reference))
        }
//        Log.d("KeywordViewModel", "Updated keyword: $title, meaning: $meaning, reference: $reference")
    }

//    fun saveAllKeywords() {
//        Log.d("KeywordViewModel", "saveAllKeywords called")
//        viewModelScope.launch {
//            _keywords.value.forEach { (title, pair) ->
//                val (meaning, reference) = pair
//                Log.d("KeywordViewModel", "Saving: $title, $meaning, $reference")
//                keywordDao.insertKeyword(
//                    KeywordEntity(title = title, meaning = meaning, reference = reference)
//                )
//            }
//        }
//    }
}