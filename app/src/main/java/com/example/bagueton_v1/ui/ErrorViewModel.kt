package com.example.bagueton_v1.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ErrorViewModel : ViewModel() {

    var errorMessage = mutableStateOf<String?>(null)

}
