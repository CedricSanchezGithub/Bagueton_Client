package com.c3dev.bagueton.ui.ui.screens.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel(){

    var username = mutableStateOf("")
    var password = mutableStateOf("")
    var confirmPassword = mutableStateOf("")
    var message =  mutableStateOf("")
    var isRegisterScreen =  mutableStateOf(false)

}