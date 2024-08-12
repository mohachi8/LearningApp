package com.example.learningapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningapp.data.local.AppDatabase
import com.example.learningapp.data.local.entities.KeywordEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class KeywordViewModel(application: Application) : AndroidViewModel(application) {
    private val keywordDao = AppDatabase.getDatabase(application).keywordDao()

    private val _meaning = MutableStateFlow("")
    private val _reference = MutableStateFlow("")

    val meaning: StateFlow<String> = _meaning
    val reference: StateFlow<String> = _reference

    fun saveKeyword(title: String, meaning: String, reference: String) {
        viewModelScope.launch {
            keywordDao.insertKeyword(
                KeywordEntity(title = title, meaning = meaning, reference = reference)
            )
        }
    }

    fun updateMeaningAndReference(meaning: String, reference: String) {
        _meaning.value = meaning
        _reference.value = reference
    }
}