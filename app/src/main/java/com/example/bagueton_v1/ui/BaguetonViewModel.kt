package com.example.bagueton_v1.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bagueton_v1.ui.model.Ingredient
import com.example.bagueton_v1.ui.model.RecipeAPI
import com.example.bagueton_v1.ui.model.RecipeBean
import com.example.bagueton_v1.ui.model.Step
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

// Définition de la classe ViewModel pour gérer les données liées aux recettes.
class BaguetonViewModel : ViewModel() {

    // Déclaration de listes et variables observables.
    // Ces variables sont observées par l'interface utilisateur (UI) et toute modification déclenchera une mise à jour de l'UI.
    var recipeList = mutableStateListOf<RecipeBean>()

    var searchText =   mutableStateOf("")

    var imageRecipe =  mutableStateOf("")
    var titleRecipe =  mutableStateOf("")
    var stepsRecipe =  mutableStateOf("")
    var ingredientsRecipe =  mutableStateOf("")

    var snackBarValue = mutableStateOf(false)

    var newTitleRecipe =  mutableStateOf("")
    var newStepsRecipe =  mutableStateOf("")
    var newIngredientsRecipe =  mutableStateOf("")




    // Fonction pour créer une nouvelle recette. Gère l'opération de manière asynchrone à l'aide de coroutines.
    fun createRecipe(title: String?, ingredients: List<Ingredient>, steps: List<Step>?){
        viewModelScope.launch(Dispatchers.Default) {
            // Lance une coroutine dans le contexte du ViewModelScope sur le dispatcher par défaut pour des opérations non bloquantes.
            try {
                RecipeAPI.createRecipe(title = title, steps = steps, ingredients = ingredients)
                val newRecipe = title?.let { RecipeBean(title = it, steps = steps, ingredients = ingredients) }
                launch(Dispatchers.Main) { // Bascule vers le thread principal pour effectuer des modifications de l'UI.
                    if (newRecipe != null) {
                        recipeList.add(newRecipe)
                    } // Ajoute la nouvelle recette à la liste observable, déclenchant une mise à jour de l'UI.
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

    fun updateRecipe(id: String, title: String?, ingredients: List<Ingredient>, steps: List<Step>?){
        viewModelScope.launch(Dispatchers.Default) {
            // Lance une c
            // oroutine dans le contexte du ViewModelScope sur le dispatcher par défaut pour des opérations non bloquantes.
            try {
                RecipeAPI.updateRecipe(id, title = title, stepList = steps, ingredientList = ingredients)
                val newRecipe = title?.let { RecipeBean(title = it, steps = steps, ingredients = ingredients) }
                launch(Dispatchers.Main) { // Bascule vers le thread principal pour effectuer des modifications de l'UI.
                    if (newRecipe != null) {
                        recipeList.add(newRecipe)
                    } // Ajoute la nouvelle recette à la liste observable, déclenchant une mise à jour de l'UI.
                    println("Recette $title correctement modifiée")
                    snackBarValue.value = true
                }
            }
            catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}

