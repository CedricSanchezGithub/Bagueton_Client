package com.example.bagueton_v1.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bagueton_v1.R
import com.example.bagueton_v1.ui.model.RecipeAPI
import com.example.bagueton_v1.ui.model.RecipeBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import kotlin.reflect.KProperty

// Définition de la classe ViewModel pour gérer les données liées aux recettes.
class BaguetonViewModel : ViewModel() {

    // Déclaration de listes et variables observables.
    // Ces variables sont observées par l'interface utilisateur (UI) et toute modification déclenchera une mise à jour de l'UI.
    var recipeList = mutableStateListOf<RecipeBean>()
    var searchText =   mutableStateOf("")
    var titleRecipe =  mutableStateOf("")
    var stepsRecipe =  mutableStateOf("")
    var ingredientsRecipe =  mutableStateOf("")
    var snackBarValue = mutableStateOf(false)


    // Fonction pour créer une nouvelle recette. Gère l'opération de manière asynchrone à l'aide de coroutines.
    fun createRecipe(title: String, ingredient: String, steps: String){
        viewModelScope.launch(Dispatchers.Default) {
            // Lance une coroutine dans le contexte du ViewModelScope sur le dispatcher par défaut pour des opérations non bloquantes.
            try {
                RecipeAPI.createRecipe(title = title, steps = steps, ingredients = ingredient)
                val newRecipe = RecipeBean(title = title, steps = steps, ingredients = ingredient)
                launch(Dispatchers.Main) { // Bascule vers le thread principal pour effectuer des modifications de l'UI.
                    recipeList.add(newRecipe) // Ajoute la nouvelle recette à la liste observable, déclenchant une mise à jour de l'UI.
                    snackBarValue.value = true
                }
            }
            catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun loadRecipes(){
        viewModelScope.launch(Dispatchers.Default) {
            recipeList.clear()
            try {
                val newRecipes = RecipeAPI.readRecipes()
                launch(Dispatchers.Main) {
                    recipeList.addAll(newRecipes)
                    println("Chargement des données de recette loadRecipe dans le ViewModel")
                }
            }

            catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}

