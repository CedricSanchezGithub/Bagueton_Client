package com.c3dev.bagueton.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ErrorViewModel : ViewModel() {

    var errorMessage = mutableStateOf<String?>(null)

}
