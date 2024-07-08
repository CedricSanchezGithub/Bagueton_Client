package com.c3dev.bagueton.ui.exceptions

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CustomSnackBarViewModel : ViewModel(){

    val errorServer = mutableStateOf(false)
    val errorServerMessage = mutableStateOf("")

}
