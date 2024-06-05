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

class BaguetonViewModel : ViewModel() {

    var recipeList = mutableStateListOf<RecipeBean>()

    var searchText = mutableStateOf("")
    var imageRecipe = mutableStateOf("")
    var titleRecipe = mutableStateOf("")
    var stepsRecipe = mutableStateOf("")
    var ingredientsRecipe = mutableStateOf("")
    var ingredientsList = mutableStateListOf<Ingredient>()
    var stepsList = mutableStateListOf<Step>()

    var snackBarValue = mutableStateOf(false)

    var newTitleRecipe = mutableStateOf("")
    var newStepsRecipe = mutableStateOf("")
    var newIngredientsRecipe = mutableStateOf("")


    fun createRecipe(title: String, ingredients: List<Ingredient>, steps: List<Step>){
        viewModelScope.launch(Dispatchers.Default) {
            try {
                if (RecipeAPI.isTitleUsed(title)) {
                    println("Le nom de recette $title est déjà utilisé.")
                    titleRecipe.value = "$title est déjà utilisé, choisissez un autre nom :)"
                    return@launch
                }
                ingredientsList.clear()
                ingredientsList.addAll(ingredients)
                stepsList.clear()
                stepsList.addAll(steps)

                RecipeAPI.createRecipe(title = title, steps = stepsList, ingredients = ingredientsList)
                val newRecipe = RecipeBean(title = title, steps = stepsList, ingredients = ingredientsList)
                launch(Dispatchers.Main) {
                    recipeList.add(newRecipe)
                    snackBarValue.value = true
                }
            } catch (e: IOException) {
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
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun updateRecipe(id: String, title: String?, ingredients: List<Ingredient>, steps: List<Step>?){
        viewModelScope.launch(Dispatchers.Default) {
            try {
                RecipeAPI.updateRecipe(id, title = title, stepList = steps, ingredientList = ingredients)
                val newRecipe = title?.let { RecipeBean(title = it, steps = steps, ingredients = ingredients) }
                launch(Dispatchers.Main) {
                    if (newRecipe != null) {
                        recipeList.add(newRecipe)
                    }
                    println("Recette $title correctement modifiée")
                    snackBarValue.value = true
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun addStep() {
        stepsList.add(Step(description = ""))
    }

    fun updateStep(index: Int, newDescription: String) {
        stepsList[index] = Step(description = newDescription)
    }

    fun addIngredient() {
        ingredientsList.add(Ingredient(ingredient = "", quantity = ""))
    }

    fun updateIngredient(index: Int, newIngredient: String, newQuantity: String) {
        ingredientsList[index] = Ingredient(ingredient = newIngredient, quantity = newQuantity)
    }
}
