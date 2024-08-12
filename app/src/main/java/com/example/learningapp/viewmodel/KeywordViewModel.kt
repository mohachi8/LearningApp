package com.example.learningapp.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class KeywordViewModel : ViewModel() {

    private val _meaning = MutableStateFlow("")
    private val _reference = MutableStateFlow("")

    val meaning: StateFlow<String> = _meaning
    val reference: StateFlow<String> = _reference

    fun saveKeyword(meaning: String, reference: String) {
        _meaning.value = meaning
        _reference.value = reference
    }
}