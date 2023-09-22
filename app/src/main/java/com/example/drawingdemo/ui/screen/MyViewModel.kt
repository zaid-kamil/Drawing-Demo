package com.example.drawingdemo.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


class MyViewModel : ViewModel() {
    private var job: Job? = null
    private val _times = MutableStateFlow(0)
    val times = _times.asStateFlow()
    fun start(times: Int = 30) {
        if (_times.value == 0) _times.value = times
        job?.cancel()
        job = viewModelScope.launch {
            while (isActive) {
                if (_times.value <= 0) {
                    job?.cancel()
                    return@launch
                }
                delay(1000)
                _times.value -= 1
            }
        }
    }

    fun pause() {
        job?.cancel()
    }

    fun stop() {
        job?.cancel()
        _times.value = 0
    }
}

