package com.c3dev.bagueton.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AccountViewModel : ViewModel(){

    // États pour stocker le nom d'utilisateur et le mot de passe entrés par l'utilisateur
    val username =   mutableStateOf("")
    val password =   mutableStateOf("")
    // État pour gérer l'affichage des messages d'erreur
    val errorMessage =   mutableStateOf("")

    val choosenUsername = mutableStateOf("")
    val choosenPassword = mutableStateOf("")
    val choosenPasswordAgain = mutableStateOf("")

}
