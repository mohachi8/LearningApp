package com.example.learningapp.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class StepViewModel : ViewModel() {
    // 現在のステップを保持するMutableLiveData
    private val _currentStep = MutableStateFlow(1)
    val currentStep: StateFlow<Int> = _currentStep.asStateFlow()

    fun updateStep(step: Int) {
        _currentStep.value = step
    }
}